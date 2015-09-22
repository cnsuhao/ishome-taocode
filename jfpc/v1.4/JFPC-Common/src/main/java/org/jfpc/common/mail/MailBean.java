package org.jfpc.common.mail;

import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件对象内容
 * @author Administrator
 *
 */
public class MailBean extends SimpleMailMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1877281708994283256L;
	public MailBean() {
		super();
	}
	public MailBean(MailBean original) {
		super(original);
	}

}
