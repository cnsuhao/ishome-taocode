package org.isotope.jfp.mpc.weixin.business;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinArticlesBean;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinNewsBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyTagReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.messagebuilder.NewsBuilder;

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
	 *            接收用户标签，与User、Group是三选一关系
	 * @param user
	 *            接收用户，与Group、Tag是三选一关系
	 */

	public String sendText(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);
			WxCpMessage cpMmessage = WxCpMessage.TEXT().agentId(comany.getAgentId()).toUser(users).toParty(groups)
					.toTag(tags).content(messageValue.getMessage()).build();
			try {
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendImage(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_IMAGE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.IMAGE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(groups).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendVoice(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_VOICE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.VOICE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(groups).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendVideo(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_VIDEO,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.VIDEO().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(groups).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendFile(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);

			try {
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_FILE,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.FILE().mediaId(result.getMediaId()).agentId(comany.getAgentId())
						.toUser(users).toParty(groups).toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	// ??thumb是什么消息类型，微信接口好象没有
	public String sendThumb(WeiXinMessageValueBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);
			try {
				// TODO
				WxMediaUploadResult result = txWeixinService.mediaUpload(ISWeixinConstants.MEDIA_THUMB,
						messageValue.getMediaFile());
				WxCpMessage cpMmessage = WxCpMessage.NEWS().agentId(comany.getAgentId()).toUser(users).toParty(groups)
						.toTag(tags).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	public String sendNews(WeiXinNewsBean messageValue, WeiXinCompanySenderBean sender,
			List<WeiXinCompanyGroupReceverBean> groupRecevers, List<WeiXinCompanyTagReceverBean> tagRecevers,
			List<WeiXinCompanyUserReceverBean> userRecevers) {
		// 获得微信Token信息
		WeiXinCompanyTokenBean comany = weixinCompanyTokenService.loadCompanyToken(sender);
		TxWeixinService txWeixinService = new TxWeixinService(comany);
		if (comany != null) {
			String groups = getGroupReceivers(groupRecevers);
			String tags = getTagReceivers(tagRecevers);
			String users = getUserReceivers(userRecevers);
			try {
				NewsBuilder newsBuilder = WxCpMessage.NEWS();
				for (WeiXinArticlesBean article : messageValue.getArticles()) {
					newsBuilder.addArticle(article);
				}
				WxCpMessage cpMmessage = newsBuilder.agentId(comany.getAgentId()).toUser(users).toParty(groups).toTag(tags)
						.addArticle(messageValue.getArticles().get(0)).build();
				txWeixinService.messageSend(cpMmessage);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return ZERO;
	}

	private String getTagReceivers(List<WeiXinCompanyTagReceverBean> recevers) {
		if(EmptyHelper.isEmpty(recevers))
			return EMPTY;
		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinCompanyTagReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getWxId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getWxId());
		}
		return receiverBuffer.toString();
	}

	private String getGroupReceivers(List<WeiXinCompanyGroupReceverBean> recevers) {
		if(EmptyHelper.isEmpty(recevers))
			return EMPTY;
		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinCompanyGroupReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getWxId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getWxId());
		}
		return receiverBuffer.toString();
	}

	private String getUserReceivers(List<WeiXinCompanyUserReceverBean> recevers) {
		if(EmptyHelper.isEmpty(recevers))
			return EMPTY;
		StringBuffer receiverBuffer = new StringBuffer();
		Iterator<WeiXinCompanyUserReceverBean> it = recevers.iterator();

		if (it.hasNext()) {
			receiverBuffer.append(it.next().getWxId());
		}
		while (it.hasNext()) {
			receiverBuffer.append("|");
			receiverBuffer.append(it.next().getWxId());
		}
		return receiverBuffer.toString();
	}
}
