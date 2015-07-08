package org.ishome.jfp.framework.sms.utils;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.SMSBean;

/**
 * 短信发送网关
 * @author Spook
 * @version 2.1.3 2015/4/23
 * @since 2.0.0
 *
 */
public interface ISSMSGatewaySupport {

	/**
	 * 短信发送
	 * @param message
	 * @return
	 */
	public RESTResultBean doSend(SMSBean message);
}
