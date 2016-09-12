package org.isotope.jfp.mpc.weixin.server;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.AMessagePushGatewaySupport;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.business.MyWeixinBusiness;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyGroupTokenService;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.isotope.jfp.mpc.weixin.token.WeiXinUserTokenService;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyGroupTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinUserTokenBean;

/**
 * 微信消息发送实现类
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @see <MessagePushCenterMonitorServer><MyWeixinMessageBusiness>
 */
public class WeiXinMessagePushGatewayServerThread extends AMessagePushGatewaySupport implements ISWeixinConstants {

	public WeiXinMessagePushGatewayServerThread() {
		setMessageType(MessageType.WeiXin);
	}

	@Resource
	MyWeixinBusiness weixinService;// 微信接口通信
	@Resource
	WeiXinCompanyTokenService weixinCompanyTokenService;// 企业Token
	@Resource
	WeiXinCompanyGroupTokenService weixinCompanyGroupTokenService;
	@Resource
	WeiXinUserTokenService weixinUserTokenService;// 用户Token

	/**
	 * 消息推送
	 */
	@Override
	public RESTResultBean push(MessageInfoBean messageInfo) {
		RESTResultBean result = new RESTResultBean();
		if (MessageType.WeiXin.equals(messageInfo.getMessgeType())) {
			// 参数类型转换
			WeiXinMessageValueBean message = (WeiXinMessageValueBean) messageInfo.getMessage();
			WeiXinCompanySenderBean sender = (WeiXinCompanySenderBean) messageInfo.getSender();
			// 推送对象
			WeiXinCompanyGroupReceverBean groupRecever = null;
			WeiXinUserReceverBean userRecever = null;
			boolean push = true;
			if (messageInfo.getRecever() instanceof WeiXinCompanyGroupReceverBean)
				groupRecever = (WeiXinCompanyGroupReceverBean) messageInfo.getRecever();
			else if (messageInfo.getRecever() instanceof WeiXinCompanyGroupReceverBean)
				groupRecever = (WeiXinCompanyGroupReceverBean) messageInfo.getRecever();
			else {
				push = false;
				result.setCode(THREE);
				result.setMessage("用户类型不对，不支持当前用户类别getRecever(" + messageInfo.getRecever().getClass() + ")");
			}
			if (push) {
				// 获得微信Token信息
				WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyConfig(sender);
				WeiXinCompanyGroupTokenBean group = weixinCompanyGroupTokenService.loadCompanyConfig(groupRecever);
				WeiXinUserTokenBean user = weixinUserTokenService.loadWeiXinUserToken(userRecever);
				// 进行数据推送
				if (MEDIA_TEXT.equals(message.getMediaType())) {
					weixinService.sendText(message, comany, group, user);
				} else if (MEDIA_IMAGE.equals(message.getMediaType())) {
					weixinService.sendImage(message, comany, group, user);
				} else if (MEDIA_VOICE.equals(message.getMediaType())) {
					weixinService.sendVoice(message, comany, group, user);
				} else if (MEDIA_VIDEO.equals(message.getMediaType())) {
					weixinService.sendVideo(message, comany, group, user);
				} else if (MEDIA_THUMB.equals(message.getMediaType())) {
					weixinService.sendThumb(message, comany, group, user);
				} else if (MEDIA_FILE.equals(message.getMediaType())) {
					weixinService.sendFile(message, comany, group, user);
				} else {
					result.setCode(TWO);
					result.setMessage("消息类型不对，不支持当前消息内容类别getMediaType(" + message.getMediaType() + ")");
				}
			}
		} else {
			result.setCode(ONE);
			result.setMessage("消息类型不对，不支持当前类别(" + messageInfo.getMessage().getClass() + ")");
		}

		return result;
	}

}
