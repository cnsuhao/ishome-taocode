package org.isotope.jfp.mpc.weixin.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.AMessagePushGatewaySupport;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyTagReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recevers.WeiXinCompanyGroupReceverListBean;
import org.isotope.jfp.mpc.weixin.beans.recevers.WeiXinCompanyGroupUserReceverListBean;
import org.isotope.jfp.mpc.weixin.beans.recevers.WeiXinCompanyTagReceverListBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
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
	@Override
	public RESTResultBean push(MessageInfoBean messageInfo) {
		RESTResultBean result = new RESTResultBean();
		if (MessageType.WeiXin.equals(messageInfo.getMessgeType())) {
			// 参数类型转换
			WeiXinMessageValueBean messageValue = (WeiXinMessageValueBean) messageInfo.getMessage();
			WeiXinCompanySenderBean sender = (WeiXinCompanySenderBean) messageInfo.getSender();
			// 推送对象
			List<WeiXinCompanyGroupReceverBean> deptGroupRecevers = null;
			List<WeiXinCompanyTagReceverBean> tagRecevers = null;
			List<WeiXinCompanyGroupUserReceverBean> userGroupRecevers = null;

			boolean push = true;
			if(messageInfo.getRecever() == null){
				//全体发送
				userGroupRecevers = new ArrayList<WeiXinCompanyGroupUserReceverBean>();
				WeiXinCompanyGroupUserReceverBean allUser = new WeiXinCompanyGroupUserReceverBean();
				allUser.setWxId("@all");
				userGroupRecevers.add(allUser);
			}
			//发送给某个用户
			else if (messageInfo.getRecever() instanceof WeiXinCompanyGroupUserReceverBean){
				userGroupRecevers = new ArrayList<WeiXinCompanyGroupUserReceverBean>();
				userGroupRecevers.add((WeiXinCompanyGroupUserReceverBean) messageInfo.getRecever());
			}
			//发送给某个用户组
			else if (messageInfo.getRecever() instanceof WeiXinCompanyGroupReceverBean){
				deptGroupRecevers = new ArrayList<WeiXinCompanyGroupReceverBean>();
				deptGroupRecevers.add((WeiXinCompanyGroupReceverBean) messageInfo.getRecever());
			}
			//发送给某个企业
			else if (messageInfo.getRecever() instanceof WeiXinCompanyTagReceverBean){
				tagRecevers = new ArrayList<WeiXinCompanyTagReceverBean>();
				tagRecevers.add((WeiXinCompanyTagReceverBean) messageInfo.getRecever());
			}
			//////////////////////////////////////////////////////////////////////////////
			//发送给某些用户
			else if (messageInfo.getRecever() instanceof WeiXinCompanyGroupUserReceverListBean){
				userGroupRecevers = ((WeiXinCompanyGroupUserReceverListBean) messageInfo.getRecever()).getRecevers();
			}
			//发送给某些用户组
			else if (messageInfo.getRecever() instanceof WeiXinCompanyGroupReceverListBean){
				deptGroupRecevers = ((WeiXinCompanyGroupReceverListBean) messageInfo.getRecever()).getRecevers();
			}
			//发送给某些企业
			else if (messageInfo.getRecever() instanceof WeiXinCompanyTagReceverListBean){
				tagRecevers = ((WeiXinCompanyTagReceverListBean) messageInfo.getRecever()).getRecevers();
			}
			//////////////////////////////////////////////////////////////////////////////
			else {
				push = false;
				result.setCode(THREE);
				result.setMessage("用户类型不对，不支持当前用户类别getRecever(" + messageInfo.getRecever().getClass() + ")");
			}
			if (push) {
				// 进行数据推送
				if (MEDIA_TEXT.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendText(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
				} else if (MEDIA_IMAGE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendImage(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
				} else if (MEDIA_VOICE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendVoice(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
				} else if (MEDIA_VIDEO.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendVideo(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
				} else if (MEDIA_THUMB.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendThumb(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
				} else if (MEDIA_FILE.equals(messageValue.getMediaType())) {
					result.setCode(weixinService.sendFile(messageValue, sender, deptGroupRecevers, tagRecevers, userGroupRecevers));
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
