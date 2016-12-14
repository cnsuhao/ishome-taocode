package org.isotope.jfp.common.mail.server;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.MailBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISMailSendSupport;

/**
 * 邮件发送工厂
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class MailSendFactory implements ISMailSendSupport, ISFrameworkConstants {
	/**
	 * 默认推送服务
	 */
	private ISMailSendSupport defaultMailSend;

	public ISMailSendSupport getDefaultMailSend() {
		return defaultMailSend;
	}

	public void setDefaultMailSend(ISMailSendSupport defaultMailSend) {
		this.defaultMailSend = defaultMailSend;
	}

	// 这里仅仅为了保持Common下面代码的风格一致性，不习惯的可以修改
	/**
	 * 消息推送
	 */
	@Override
	public RESTResultBean doSend(MailBean message) {
		return defaultMailSend.doSend(message);
	}

}
