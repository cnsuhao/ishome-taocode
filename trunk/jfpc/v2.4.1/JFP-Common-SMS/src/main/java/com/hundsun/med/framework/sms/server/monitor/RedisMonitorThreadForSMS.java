package com.hundsun.med.framework.sms.server.monitor;

import redis.clients.jedis.Jedis;

import org.ishome.jfp.framework.beands.common.RedisChannelConfigBean;
import org.ishome.jfp.framework.sms.SMSChannelConfig;
import org.ishome.jfp.framework.utils.EmptyHelper;

import com.hundsun.med.framework.sms.server.SMSGatewayFactory;

/**
 * 短信队列监听（控制器）
 * 
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
 */
public class RedisMonitorThreadForSMS extends SMSChannelConfig {

	Jedis jedis;
	// 缓存中心定义
	RedisChannelConfigBean redisChannelConfig;
	SMSGatewayFactory smsGatewayFactory;

	public SMSGatewayFactory getSmsGatewayFactory() {
		return smsGatewayFactory;
	}

	public void setSmsGatewayFactory(SMSGatewayFactory smsGatewayFactory) {
		this.smsGatewayFactory = smsGatewayFactory;
	}

	public RedisChannelConfigBean getRedisChannelConfig() {
		return redisChannelConfig;
	}

	public void setRedisChannelConfig(RedisChannelConfigBean redisChannelConfig) {
		this.redisChannelConfig = redisChannelConfig;
	}

	public String channels = EMPTY;

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public void run() {
		// 单通道
		if (EmptyHelper.isEmpty(channels)) {
			SMSChannelThread st = new SMSChannelThread(smsGatewayFactory, redisChannelConfig, getKeyList());
			st.start();
		}
		// 多通道
		else {
			for (String key : channels.split(SEMICOLON)) {
				if (EmptyHelper.isNotEmpty(key)){
					this.setKeyList(key);
				}
				SMSChannelThread st = new SMSChannelThread(smsGatewayFactory, redisChannelConfig, getKeyList());
				st.start();
			}
		}
	}
}
