package org.isotope.jfp.framework.common.log;

import org.isotope.jfp.framework.beans.pub.LogBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISLogConstants;
import org.isotope.jfp.framework.support.common.ISLogSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class LogSendServiceImpl extends CommonChannelConfig implements ISLogSupport, ISLogConstants, ISFrameworkConstants {

	private Logger logger = LoggerFactory.getLogger(LogSendServiceImpl.class);

	public LogSendServiceImpl() {
		this(ISLogSupport.CONFIG_KEY);
	}

	public LogSendServiceImpl(String key) {
		this.channelKey = key;
	}

	@Override
	public boolean send(LogBean log) {
		logger.debug(log.toString());

		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.offerObjectInList(channelKey, log, false);
		}
		return true;
	}
}
