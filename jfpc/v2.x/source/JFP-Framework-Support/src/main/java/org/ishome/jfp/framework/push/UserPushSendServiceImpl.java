package org.ishome.jfp.framework.push;

import org.ishome.jfp.framework.beands.PushBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISPushMessageConstant;
import org.ishome.jfp.framework.support.ISPhonePushSupport;
import org.ishome.jfp.framework.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送SDK
 * 
 * @author Spook
 * @version 2.0.1 2015/6/15
 * @since 2.0.1 2015/7/7
 * 
 */
public class UserPushSendServiceImpl extends PushChannelConfig implements ISFrameworkConstants, ISPushMessageConstant, ISPhonePushSupport {

	private static final Logger logger = LoggerFactory.getLogger(UserPushSendServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.med.framework.push.ISPush#send(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean push(PushBean push) {
		logger.debug(push.toString());
		
		if (SYSTEM_NAME.equals(push.getHosId()))
			push.setSourceCmp(ONE);
		else
			push.setSourceCmp(TWO);

		JedisUtil.listAdd(keyList, JSON.toJSONString(push));
		
		
//		Jedis jedis = new Jedis("127.0.0.1", 6379);
//		jedis.auth("hundsun-med-redis");
//		jedis.connect();	
//		jedis.rpush(keyList, JSON.toJSONString(push));
//		jedis.close();
		
		return false;
	}
}
