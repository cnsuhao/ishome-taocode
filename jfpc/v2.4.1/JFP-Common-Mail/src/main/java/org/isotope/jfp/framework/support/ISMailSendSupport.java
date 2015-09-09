package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.MailBean;

/**
 * 邮件发送接口 ，
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 *
 */
public interface ISMailSendSupport {

	/**
	 * 短信发送
	 * 
	 * @param message
	 * @return
	 */
	public RESTResultBean doSend(MailBean message);
}
