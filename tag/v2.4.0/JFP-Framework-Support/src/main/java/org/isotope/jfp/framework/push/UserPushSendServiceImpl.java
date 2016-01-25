package org.isotope.jfp.framework.push;

import org.isotope.jfp.framework.beands.common.RedisChannelConfigBean;
import org.isotope.jfp.framework.beands.pub.PushBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushMessageConstant;
import org.isotope.jfp.framework.support.ISPhonePushSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class UserPushSendServiceImpl extends PushChannelConfig implements ISFrameworkConstants, ISPushMessageConstant, ISPhonePushSupport {

	private Logger logger = LoggerFactory.getLogger(UserPushSendServiceImpl.class);

	RedisChannelConfigBean redisConfig;

	public RedisChannelConfigBean getRedisConfig() {
		return redisConfig;
	}

	public void setRedisConfig(RedisChannelConfigBean redisConfig) {
		this.redisConfig = redisConfig;
	}

	ICacheService catchService;

	public ICacheService getCatchService() {
		return catchService;
	}

	public void setCatchService(ICacheService catchService) {
		this.catchService = catchService;
	}

	/**
	 * 消息推送
	 */
	@Override
	public boolean push(PushBean push) {
		logger.debug(push.toString());

		if (SYSTEM_NAME.equals(push.getHosId()))
			push.setSourceCmp(ONE);
		else
			push.setSourceCmp(TWO);

		if (redisConfig == null)
			catchService.offerObjectInList(keyList, push, false);
		else {
			Jedis jedis = redisConfig.getJedis();
			jedis.rpush(keyList, JSON.toJSONString(push));
			jedis.close();
		}
		return false;
	}
}
