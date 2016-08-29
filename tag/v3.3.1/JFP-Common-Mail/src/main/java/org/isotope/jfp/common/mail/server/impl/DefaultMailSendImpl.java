package org.isotope.jfp.common.mail.server.impl;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.MailBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISMailConstants;
import org.isotope.jfp.framework.support.ISMailSendSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 默认邮件发送实现
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 * 
 */
public class DefaultMailSendImpl extends JavaMailSenderImpl implements ISMailSendSupport, ISMailConstants, ISFrameworkConstants {

	private Logger log = LoggerFactory.getLogger(DefaultMailSendImpl.class);
	/**
	 * 是否关闭
	 */
	private boolean disabled = false;

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public RESTResultBean doSend(MailBean message) {
		log.debug(message.toString());
		// 发送邮件
		send(message);
		// 返回结果
		RESTResultBean rs = new RESTResultBean();
		rs.setCode(ZERO);
		rs.setMessage("邮件发送成功");
		return rs;
	}

}
