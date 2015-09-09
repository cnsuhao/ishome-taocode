package org.isotope.jfp.framework.sms;

import org.isotope.jfp.framework.beands.common.RedisChannelConfigBean;
import org.isotope.jfp.framework.beands.pub.SMSBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISSMSSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * 
 */
public class UserSMSSendServiceImpl extends SMSChannelConfig implements ISSMSSupport, ISFrameworkConstants {
	private Logger logger = LoggerFactory.getLogger(UserSMSSendServiceImpl.class);

	public UserSMSSendServiceImpl() {
		this("YZM");
	}

	public UserSMSSendServiceImpl(String keyList) {
		this.setKeyList(keyList);
	}

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

	@Override
	public boolean send(String hosId, String phoneNum, String message) {
		if (EmptyHelper.isEmpty(hosId) || EmptyHelper.isEmpty(phoneNum) || EmptyHelper.isEmpty(message))
			return false;
		// 直接保存到短信队列
		return send(hosId, phoneNum, message, EMPTY);
	}

	@Override
	public boolean send(String hosId, String phoneNum, String message, String userId) {
		logger.debug(hosId + "..." + phoneNum + "..." + message + "..." + userId);
		SMSBean sms = new SMSBean();
		sms.setHosId(hosId);
		sms.setPhoneNum(phoneNum);
		sms.setMessage(message);
		if (SYSTEM_NAME.equals(hosId))
			sms.setSourceCmp(ONE);
		else
			sms.setSourceCmp(TWO);
		if (redisConfig == null)
			catchService.offerObjectInList(keyList, sms, false);
		else {
			Jedis jedis = redisConfig.getJedis();
			jedis.rpush(keyList, JSON.toJSONString(sms));
			jedis.close();
		}
		// 直接保存到短信队列
		return true;
	}

}
