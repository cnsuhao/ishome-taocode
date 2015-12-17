package org.isotope.jfp.framework.beans.pub;

import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件对象内容
 * @author fucy
 * @version 1.4.3 2014/11/29
 * @since 1.4.3
 *
 */
public class MailBean extends SimpleMailMessage {
	private static final long serialVersionUID = 1877281708994283256L;

	public MailBean() {
		super();
	}

	public MailBean(MailBean original) {
		super(original);
	}

}
