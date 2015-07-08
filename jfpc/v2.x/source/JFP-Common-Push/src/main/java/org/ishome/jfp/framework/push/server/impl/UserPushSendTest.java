package org.ishome.jfp.framework.push.server.impl;

import org.ishome.jfp.framework.beands.PushBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISPushMessageConstant;
import org.ishome.jfp.framework.push.PushChannelConfig;
import org.ishome.jfp.framework.support.ISPhonePushSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送SDK
 * 
 * @author Spook
 * @version 2.0.1 2015/6/15
 * @since 2.0.1 2015/7/7
 * 
 */
public class UserPushSendTest extends PushChannelConfig implements ISFrameworkConstants, ISPushMessageConstant, ISPhonePushSupport {

	private static final Logger logger = LoggerFactory.getLogger(UserPushSendTest.class);

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
