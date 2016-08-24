package org.isotope.jfp.framework.common.sms;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.isotope.jfp.framework.beans.pub.SMSBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISSMSConstants;
import org.isotope.jfp.framework.support.common.ISMSSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * 
 */
public class UserSMSSendServiceImpl extends CommonChannelConfig implements ISMSSupport, ISSMSConstants, ISFrameworkConstants {
	public UserSMSSendServiceImpl() {
		this(ISMSSupport.CONFIG_KEY);
	}

	public UserSMSSendServiceImpl(String key) {
		this.channelKey = key;
	}

	@Override
	public boolean send(SMSBean sms) {
		logger.debug(sms.toString());
		// 压入队列
		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.selectDB(defaultIndex);
			catchService.offerObjectInList(channelKey, sms, false);
			catchService.init();
		}
		// 直接保存到短信队列
		return true;

	}

	@Override
	public boolean send(String schoolId, String phoneNum, String message) {
		if (EmptyHelper.isEmpty(schoolId) || EmptyHelper.isEmpty(phoneNum) || EmptyHelper.isEmpty(message))
			return false;
		// 直接保存到短信队列
		return send(schoolId, phoneNum, message, EMPTY);
	}

	@Override
	public boolean send(String schoolId, String phoneNum, String message, String userId) {
		logger.debug(schoolId + "..." + phoneNum + "..." + message + "..." + userId);
		SMSBean sms = new SMSBean();
		sms.setCompanyId(schoolId);
		sms.setPhoneNum(phoneNum);
		sms.setMessage(message);
		if (SYSTEM_NAME.equals(schoolId))
			sms.setSourceCmp(ONE);
		else
			sms.setSourceCmp(TWO);
		// 压入队列
		if (catchService == null){
			
//			Jedis jedis = new Jedis("", 6379);
//			jedis.auth("");
//			jedis.select(2);
//			jedis.rpush("SMSList", JSON.toJSONString(sms));			
//			System.out.println(JSON.toJSONString(sms));
//			jedis.close();
			
			logger.error(">>>>>缓存服务没有定义...xxx"+JSON.toJSONString(sms));
		}
		else {
			catchService.selectDB(defaultIndex);
			catchService.offerObjectInList(channelKey, JSON.toJSONString(sms), false);
			catchService.init();
		}
		// 直接保存到短信队列
		return true;
	}


	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		UserSMSSendServiceImpl sms = new UserSMSSendServiceImpl();
		sms.send("123456789", "15057177411", "【千校云】尊敬的用户，您的验证码为123456，本次验证码30分钟内有效，感谢您的使用。");
	}
}
