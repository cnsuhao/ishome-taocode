package com.hundsun.med.push.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.framework.beands.PushBean;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.constants.ISPushMessageConstant;
import com.hundsun.med.framework.push.PushChannelConfig;
import com.hundsun.med.framework.support.ISPhonePushSupport;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class UserPushSendTest extends PushChannelConfig implements ISFrameworkConstants, ISPushMessageConstant, ISPhonePushSupport {

	private Logger logger = LoggerFactory.getLogger(UserPushSendTest.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.med.framework.push.ISPush#send(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean push(PushBean push) {
	

		//JedisUtil.listAdd(keyList, JSON.toJSONString(push));
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("hundsun-med-redis");
		jedis.connect();	
		jedis.rpush(keyList, JSON.toJSONString(push));
		jedis.close();
		
		return false;
	}
}
