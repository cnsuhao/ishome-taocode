package org.isotope.jfp.framework.utils;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.redis.master.JedisMasterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis 分布式锁
 * 
 * @author lujf
 * 
 */
public class RedisLock {

	private Logger logger = LoggerFactory.getLogger(RedisLock.class);

	private ICacheService cacheService;

	// 锁超时时间(单位s)
	private int lockExpire;

	public void setCacheService(ICacheService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * 获取锁
	 * 
	 * @param key
	 * @param waitTime
	 * @returnt
	 */
	public boolean acquireLock(String key, Long waitTime) {
		boolean flag = false;
		long value = System.currentTimeMillis();
		try {
			do {
				long acquired = cacheService.setnx(key, String.valueOf(System.currentTimeMillis()));
				if (acquired == 1) {
					cacheService.expire(key, lockExpire);
					logger.debug("get lock,key" + key + "expire in" + lockExpire);
					return true;
				} else {
					logger.debug("lock,key"+key+"is bussiness");
				}
				if(waitTime == 0){
					break;
				}
				 Thread.sleep(200);  
			} while (System.currentTimeMillis() < value + waitTime);
		} catch (Exception e) {
			logger.error("缓存锁获取锁异常[" + e.getMessage() + "]");
		}

		return flag;
	}

	/**
	 * 获取锁
	 * @param key
	 * @return
	 */
	public boolean acquireLock(String key) {
		return acquireLock(key, 0L);
	}

	/**
	 * 释放锁
	 * @param key
	 */
	public void releaseLock(String key) {
		long current = System.currentTimeMillis();
		String locktime = (String) cacheService.getObject(key);
		if (locktime != null) {
			if (current < Long.parseLong(locktime) + lockExpire) {
				cacheService.deleteObject(key);
			}
		}

	}

}
