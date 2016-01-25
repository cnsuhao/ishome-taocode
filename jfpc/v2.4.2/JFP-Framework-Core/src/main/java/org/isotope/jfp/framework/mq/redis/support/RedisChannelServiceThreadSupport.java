package org.isotope.jfp.framework.mq.redis.support;

import org.isotope.jfp.framework.biz.ISFinished;
import org.isotope.jfp.framework.biz.ISReceive;
import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.redis.MyRedisMaster;
import org.isotope.jfp.framework.cache.redis.master.JedisMasterUtil;
import org.isotope.jfp.framework.cache.redis.master.RedisPoolUtil;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通道服务超类
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @since 2.4.1
 */
public abstract class RedisChannelServiceThreadSupport extends Thread implements ISFrameworkConstants, ISReceive, ISInit, ISCheck, ISProcess, ISFinished {
	private Logger logger = LoggerFactory.getLogger(RedisChannelServiceThreadSupport.class);

	/**
	 * 等待时间（毫秒）
	 */
	private int waitTime = 500;

	/**
	 * Redis通道定义
	 */
	private String channelKey;

	/**
	 * 缓存定义
	 */
	private ICacheService catchService;

	public void doInit(RedisPoolUtil jedisPool, String channelKey) {
		this.channelKey = channelKey;
		JedisMasterUtil jedisUtil = new JedisMasterUtil(jedisPool);
		catchService = new MyRedisMaster(jedisUtil, 5);
	}

	public void doInit(RedisPoolUtil jedisPool, String channelKey, int waitTime) {
		this.channelKey = channelKey;
		JedisMasterUtil jedisUtil = new JedisMasterUtil(jedisPool);
		catchService = new MyRedisMaster(jedisUtil);
	}

	public ICacheService getCatchService() {
		return catchService;
	}

	public void setCatchService(ICacheService catchService) {
		this.catchService = catchService;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}

	public String getChannelKey() {
		return channelKey;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * 基于消息队列获取数据
	 */
	public boolean doReceive() throws Exception {
		message = catchService.peekFirstObjectInList(channelKey, false);
		if (EmptyHelper.isEmpty(message))
			return false;
		return true;
	}

	/**
	 * 缓存数据（JSON格式）
	 */
	protected Object message;

	/**
	 * 
	 */
	public void run() {

		while (true) {
			try {
				Thread.sleep(waitTime);
				if (doReceive()) {
					if (doInit()) {
						if (doCheck()) {
							if (doProcess()) {
								if (doFinished()) {
									logger.info(">>>>>服务处理成功<<<<<");
								} else {
									logger.info(">>>>>服务处理失败.....doFinished");
								}
							} else {
								logger.info(">>>>>服务处理失败.....doProcess");
							}
						} else {
							logger.info(">>>>>服务处理失败.....doCheck");
						}
					} else {
						logger.info(">>>>>服务处理失败.....doInit");
					}
				}
			} catch (Exception e) {
				logger.error(">>>>>服务处理异常....." + e.getMessage());
			}
		}
	}
}
