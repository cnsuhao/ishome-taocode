package org.isotope.jfp.framework.log.service;

import org.isotope.jfp.framework.beans.pub.LogBean;
import org.isotope.jfp.framework.cache.redis.master.RedisPoolUtil;
import org.isotope.jfp.framework.constants.pub.ISLogConstants;
import org.isotope.jfp.framework.support.common.ILogSupport;
import org.isotope.jfp.framework.utils.DateHelper;

import redis.clients.jedis.Jedis;

/**
 * 日志输出实现类
 * @author 001745
 *
 */
public class ELKByRedisLogService implements ILogSupport, ISLogConstants {

	@Override
	public boolean send(LogBean log) {
		try {
			getJedis().rpush(redisKey, log.toString());
		} catch (Exception e) {
			System.out.println(log.toString());
			closeJedis();
			jedis = null;
		} 
		return false;
	}
	
	/**
	 * Redis服务器定义
	 */
	private RedisPoolUtil redisPool;

	public RedisPoolUtil getRedisPool() {
		return redisPool;
	}

	public void setRedisPool(RedisPoolUtil jedisPool) {
		this.redisPool = jedisPool;
	}
	
	Jedis jedis = null;

	public Jedis getJedis() {
		if (jedis == null) {
			jedis =  redisPool.getJedisOnDB(redisIndex);
		}
		return jedis;
	}

	public void closeJedis() {
		if (jedis != null) {
			jedis.close();
		}
	}

	//redis队列key
	private String redisKey = Default_Redis_Key + DateHelper.currentDate3();
	//索引分片
	private int redisIndex = 6;

	public String getRedisKey() {
		return redisKey;
	}

	public void setRedisKey(String redisKey) {
		this.redisKey = redisKey;
	}

	public int getRedisIndex() {
		return redisIndex;
	}

	public void setRedisIndex(int redisIndex) {
		this.redisIndex = redisIndex;
	}
}
