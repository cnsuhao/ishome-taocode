package org.isotope.jfp.mpc.weixin.server;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.AMessagePushGatewaySupport;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyDeptReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyTagReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.business.MyWeixinBusiness;

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

	/**
	 * 消息推送
	 */
	@SuppressWarnings("null")
	@Override
	public RESTResultBean push(MessageInfoBean messageInfo) {
		RESTResultBean result = new RESTResultBean();
		if (MessageType.WeiXin.equals(messageInfo.getMessgeType())) {
			// 参数类型转换
			WeiXinMessageValueBean messageValue = (WeiXinMessageValueBean) messageInfo.getMessage();
			WeiXinCompanySenderBean sender = (WeiXinCompanySenderBean) messageInfo.getSender();
			// 推送对象
			List<WeiXinCompanyDeptReceverBean> deptRecevers = null;
			List<WeiXinCompanyTagReceverBean> tagRecevers = null;
			List<WeiXinUserReceverBean> userRecevers = null;

			boolean push = true;
			if (messageInfo.getRecever() instanceof WeiXinUserReceverBean)
				userRecevers.add((WeiXinUserReceverBean) messageInfo.getRecever());
			else if (messageInfo.getRecever() instanceof WeiXinCompanyDeptReceverBean)
				deptRecevers.add((WeiXinCompanyDeptReceverBean) messageInfo.getRecever());
			else if (messageInfo.getRecever() instanceof WeiXinCompanyTagReceverBean)
				tagRecevers.add((WeiXinCompanyTagReceverBean) messageInfo.getRecever());
			else {
				push = false;
				result.setCode(THREE);
				result.setMessage("用户类型不对，不支持当前用户类别getRecever(" + messageInfo.getRecever().getClass() + ")");
			}
			if (push) {
				// 进行数据推送
				if (MEDIA_TEXT.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendText(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else if (MEDIA_IMAGE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendImage(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else if (MEDIA_VOICE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendVoice(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else if (MEDIA_VIDEO.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendVideo(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else if (MEDIA_THUMB.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendThumb(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else if (MEDIA_FILE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendFile(messageValue, sender, deptRecevers, tagRecevers, userRecevers));
				} else {
					result.setCode(TWO);
					result.setMessage("消息类型不对，不支持当前消息内容类别getMediaType(" + messageValue.getMediaType() + ")");
				}
			}
		} else {
			result.setCode(ONE);
			result.setMessage("消息类型不对，不支持当前类别(" + messageInfo.getMessage().getClass() + ")");
		}

		return result;
	}

}
