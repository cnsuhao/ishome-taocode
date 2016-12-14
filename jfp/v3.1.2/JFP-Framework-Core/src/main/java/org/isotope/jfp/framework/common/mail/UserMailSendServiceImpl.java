package org.isotope.jfp.framework.common.mail;

import org.isotope.jfp.framework.beans.pub.MailBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISMailConstants;
import org.isotope.jfp.framework.support.common.IMailSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送SDK
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * 
 */
public class UserMailSendServiceImpl extends CommonChannelConfig implements IMailSupport, ISMailConstants, ISFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserMailSendServiceImpl() {
		this(IMailSupport.CONFIG_KEY);
	}

	public UserMailSendServiceImpl(String key) {
		this.channelKey = key;
	}

	@Override
	public boolean send(MailBean mial) {
		logger.debug(mial.toString());

		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.offerObjectInList(channelKey, mial, false);
		}
		return true;
	}

}
