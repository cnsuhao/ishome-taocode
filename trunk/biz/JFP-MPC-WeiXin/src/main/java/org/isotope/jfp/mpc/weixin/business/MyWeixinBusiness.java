package org.isotope.jfp.mpc.weixin.business;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyDeptReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyTagReceverBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;

/**
 * 腾讯微信对接服务
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
@Service("MyWeixinBusiness")
public class MyWeixinBusiness implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	WeiXinCompanyTokenService weixinCompanyTokenService;// 企业Token

	public void init() {
	}

	/**
	 * 消息发送
	 * 
	 * @param messageValue
	 * @param comany
	 * @param dept
	 *            接收用户部门，与User、Tag是三选一关系
	 * @param tag
	 *            接收用户标签，与User、Dept是三选一关系
	 * @param user
	 *            接收用户，与Dept、Tag是三选一关系
	 */

	public String sendText(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);
			WxCpMessage cpMmessage = WxCpMessage.TEXT().agentId(comany.getAgentId()).toUser(users).toParty(depts)
					.toTag(tags).content(messageValue.getMessage()).build();
			try {
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendImage(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_IMAGE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.IMAGE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(depts).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendVoice(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_VOICE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.VOICE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(depts).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendVideo(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_VIDEO,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.VIDEO().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(depts).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendFile(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_FILE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.FILE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(depts).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}
	
	public String sendThumb(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyDeptReceverBean> deptRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String depts = getDeptReceivers(deptRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);
			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_THUMB,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.NEWS().agentId(comany.getAgentId())
						.toUser(users).toParty(depts).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ZERO;
	}



	private String getTagReceivers(List<WeiXinCompanyTagReceverBean> recevers) {

		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinCompanyTagReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getId());
		}
		return receiverBuffer.toString();
	}

	private String getDeptReceivers(List<WeiXinCompanyDeptReceverBean> recevers) {

		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinCompanyDeptReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getId());
		}
		return receiverBuffer.toString();
	}

	private String getUserReceivers(List<WeiXinUserReceverBean> recevers) {

		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinUserReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getId());
		}
		return receiverBuffer.toString();
	}
}
