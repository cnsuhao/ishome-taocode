package org.isotope.jfp.framework.common.push;

import org.isotope.jfp.framework.beans.pub.PushBean;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISPushConstant;
import org.isotope.jfp.framework.support.common.IPhonePushSupport;

/**
 * 短信发送SDK
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class UserPushSendServiceImpl extends CommonChannelConfig implements IPhonePushSupport, ISPushConstant, ISFrameworkConstants {

	public UserPushSendServiceImpl() {
		this(IPhonePushSupport.CONFIG_KEY);
	}

	public UserPushSendServiceImpl(String key) {
		this.channelKey = key;
	}

	/**
	 * 消息推送
	 */
	@Override
	public boolean push(PushBean push) {
		logger.debug(push.toString());

		if (SYSTEM_NAME.equals(push.getCompanyId()))
			push.setSourceCmp(ONE);
		else
			push.setSourceCmp(TWO);

		if (catchService == null)
			logger.error(">>>>>缓存服务没有定义...xxx");
		else {
			catchService.offerObjectInList(channelKey, push, false);
		}
		return true;
	}
}
