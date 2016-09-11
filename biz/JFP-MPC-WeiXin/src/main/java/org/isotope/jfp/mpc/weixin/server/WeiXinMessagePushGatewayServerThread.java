package org.isotope.jfp.mpc.weixin.server;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.AMessagePushGatewaySupport;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyBusiness;

/**
 * 微信消息发送实现类
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @see <MessagePushCenterMonitorServer>
 */
public class WeiXinMessagePushGatewayServerThread extends AMessagePushGatewaySupport {

	public WeiXinMessagePushGatewayServerThread() {
		setMessageType(MessageType.WeiXin);
	}

	MyWeixinCompanyBusiness winxinBusiness;

	@Override
	public RESTResultBean push(MessageInfoBean message) {
		// TODO Auto-generated method stub
		return null;
	}

}
