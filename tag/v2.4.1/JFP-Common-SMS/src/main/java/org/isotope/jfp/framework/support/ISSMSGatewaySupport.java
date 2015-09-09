package org.isotope.jfp.framework.support;

import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;

/**
 * 短信发送网关
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
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
