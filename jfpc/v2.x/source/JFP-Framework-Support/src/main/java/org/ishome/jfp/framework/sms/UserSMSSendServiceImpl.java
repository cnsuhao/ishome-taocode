package org.ishome.jfp.framework.sms;

import org.ishome.jfp.framework.beands.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.support.ISSMSSupport;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送SDK
 * @author Spook
 * @version 2.0.0 2015/4/23
 * @since 2.0.0
 * 
 */
public class UserSMSSendServiceImpl extends SMSChannelConfig implements ISSMSSupport, ISFrameworkConstants {
	private static final Logger logger = LoggerFactory.getLogger(UserSMSSendServiceImpl.class);

	public UserSMSSendServiceImpl(){
		this("YZM");
	}
	public UserSMSSendServiceImpl(String keyList){
		this.setKeyList(keyList);
	}
	
	@Override
	public boolean send(String hosId, String phoneNum, String message) {
		if(EmptyHelper.isEmpty(hosId)||EmptyHelper.isEmpty(phoneNum)||EmptyHelper.isEmpty(message))
			return false;
		// 直接保存到短信队列
		return send(hosId, phoneNum, message, EMPTY, EMPTY);
	}

	@Override
	public boolean send(String hosId, String phoneNum, String message, String userId, String patientID) {
		logger.debug(hosId + "..." + phoneNum + "..." + message + "..." + userId + "..." + patientID);
		SMSBean sms = new SMSBean();
		sms.setHosId(hosId);
		sms.setPhoneNum(phoneNum);
		sms.setMessage(message);
		if (SYSTEM_NAME.equals(hosId))
			sms.setSourceCmp(ONE);
		else
			sms.setSourceCmp(TWO);
		
		JedisUtil.listAdd(keyList, JSON.toJSONString(sms));
		// 直接保存到短信队列
		return true;
	}

}
