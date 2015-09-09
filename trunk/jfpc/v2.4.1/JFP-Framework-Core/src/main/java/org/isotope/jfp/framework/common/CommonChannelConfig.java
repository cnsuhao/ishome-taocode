package org.isotope.jfp.framework.common;

import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.utils.redis.JedisUtil;
import org.isotope.jfp.framework.cache.utils.redis.RedisPoolUtil;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.mq.redis.MyRedis;

/**
 * 通用Redis通道队列设置
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @since 2.4.1
 */
public class CommonChannelConfig implements ISFrameworkConstants, ISInit {
	/**
	 * Redis服务器定义
	 */
	protected RedisPoolUtil jedisPool;

	public void setJedisPool(RedisPoolUtil jedisPool) {
		this.jedisPool = jedisPool;
	}

	public RedisPoolUtil getJedisPool() {
		return jedisPool;
	}

	/**
	 * 缓存定义
	 */
	protected ICacheService catchService;

	public void setCatchService(ICacheService catchService) {
		this.catchService = catchService;
	}

	public ICacheService getCatchService() {
		return catchService;
	}

	/**
	 * Redis通道定义
	 */
	protected String channelKey;

	public String getChannelKey() {
		return channelKey;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}

	public boolean doInit() {
		JedisUtil jedisUtil = new JedisUtil(jedisPool);
		catchService = new MyRedis(jedisUtil);
		return true;
	}
}
