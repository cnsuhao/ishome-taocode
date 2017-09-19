package org.isotope.jfp.framework.beans.pub;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件对象内容
 * 
 * @author Spook
 * @version 1.4.3 2014/11/29
 * @since 1.4.3
 *
 */
public class MailBean extends SimpleMailMessage implements ISFrameworkConstants {

	private static final long serialVersionUID = -8536273277566490823L;

	public MailBean() {
		super();
	}

	public MailBean(MailBean original) {
		super(original);
	}

}
