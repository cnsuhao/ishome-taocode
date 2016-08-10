package org.isotope.jfp.framework.common.sms;

import org.isotope.jfp.framework.beans.pub.SMSBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISSMSConstants;
import org.isotope.jfp.framework.support.common.ISMSSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

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
		sms.setCompanyId(hosId);
		sms.setPhoneNum(phoneNum);
		sms.setMessage(message);
		if (SYSTEM_NAME.equals(hosId))
			sms.setSourceCmp(ONE);
		else
			sms.setSourceCmp(TWO);
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

}
