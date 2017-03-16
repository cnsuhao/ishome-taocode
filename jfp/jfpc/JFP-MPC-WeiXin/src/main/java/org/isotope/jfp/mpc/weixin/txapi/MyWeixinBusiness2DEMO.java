package org.isotope.jfp.mpc.weixin.txapi;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.message.WeiXinMessageValueBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessage.WxArticle;

/**
 * 微信对接服务
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
public class MyWeixinBusiness2DEMO implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public Object companyIdPOST(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object loadCompanyUser(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object loadCompanyConfig(String companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMessageToCompanyAll(String companyId, WeiXinMessageValueBean message) {
		/*
		 * WeixinService WeixinService = new WeixinService(); HashMap<String,
		 * String> companyMap = new HashMap<String, String>();
		 * companyMap.put("compayId", companyId); List companyList =
		 * WeixinService.loadCompany(companyMap); companyMap = (HashMap)
		 * companyList.get(0);
		 * 
		 * WeiXinCpConfigStorage config = new WeiXinCpConfigStorage();
		 * config.setCorpId(companyMap.get("corpId")); // 设置微信企业号的appid
		 * config.setCorpSecret(companyMap.get("corpSecret")); // 设置微信企业号的app //
		 * corpSecret config.setAesKey(companyMap.get("aesKey")); //
		 * 设置微信企业号应用的EncodingAESKey
		 * 
		 * WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		 * wxCpService.setWxCpConfigStorage(config);
		 * 
		 * String userId = "@all"; WxCpMessage cpMmessage =
		 * WxCpMessage.TEXT().agentId(companyMap.get("agentId")).toUser(userId)
		 * .content(message.getMessage()).build(); try {
		 * wxCpService.messageSend(cpMmessage); } catch (WxErrorException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } // TODO
		 * Auto-generated method stub
		 */

		/*
		 * 增加微信发送内容，其中WeiXinCpConfigStorage.java为内存存储token，后续需根据实际应用持久化
		 * 其中token部分在发送消息的时候SDK会自动处理，不用再另外处理。
		 * 
		 */
		WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
		config.setCorpId("wxc213dac5f211edf9");
		config.setCorpSecret("cl6aPpCpsaxhQxNhmZ8KSqGNNi-hjGhJQylYDHkeTMnqjzNJI6djqCy8vWZh9nD9");
		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		String userId = "@all";
		WxCpMessage cpMmessage = WxCpMessage.TEXT().agentId("1").toUser(userId).content("test").build();
		try {
			wxCpService.messageSend(cpMmessage);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMessageToCompanyUser(String companyId, String userId, WeiXinMessageValueBean message) {
		WeixinService WeixinService = new WeixinService();
		HashMap<String, String> companyMap = new HashMap<String, String>();
		companyMap.put("compayId", companyId);
		List companyList = WeixinService.loadCompany(companyMap);
		companyMap = (HashMap) companyList.get(0);

		WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
		config.setCorpId(companyMap.get("corpId")); // 设置微信企业号的appid
		config.setCorpSecret(companyMap.get("corpSecret")); // 设置微信企业号的app
															// corpSecret
		config.setAesKey(companyMap.get("aesKey")); // 设置微信企业号应用的EncodingAESKey

		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		WxCpMessage cpMmessage = WxCpMessage.TEXT().agentId(companyMap.get("agentId")).toUser(userId)
				.content(message.getMessage()).build();
		try {
			wxCpService.messageSend(cpMmessage);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

		return null;
	}

	/*
	 * 链接形式发送图文消息方法，数据结构需要重新定义。 并且主页型应用不能发送图文消息 URL可以为空，点击后无法查看大图
	 */
	public void sendNews() {
		// TODO Auto-generated method stub
		WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
		config.setCorpId("wxc213dac5f211edf9");
		config.setCorpSecret("cl6aPpCpsaxhQxNhmZ8KSqGNNi-hjGhJQylYDHkeTMnqjzNJI6djqCy8vWZh9nD9");
		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		String userId = "tangyuan";
		WxArticle article = new WxArticle();
		article.setTitle("test1");
		article.setUrl("www.baidu.com");
		article.setPicUrl("http://img.blog.csdn.net/20151123180109849");
		article.setDescription("this is a test text/image massage");

		WxArticle article2 = new WxArticle();
		article2.setTitle("test2");
		article2.setUrl("www.sina.com");
		article2.setPicUrl("http://img.blog.csdn.net/20160118155105168");
		article2.setDescription("this is a test text/image massage also");
		/* 图文消息为news类型，第一个article图片会放大显示 */
		WxCpMessage cpMmessage = WxCpMessage.NEWS().addArticle(article).addArticle(article2).agentId("0").toUser(userId)
				.build();
		try {
			wxCpService.messageSend(cpMmessage);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/*
	 * 发送图片方法，image、voice、video、file类型的消息区分下消息类型即可，WxConsts.MEDIA_XXXXX
	 * meida类型消息需要先上传素材，获取素材ID，发送素材ID即可。
	 */
	public void sendImage() {
		WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
		config.setCorpId("wxc213dac5f211edf9");
		config.setCorpSecret("cl6aPpCpsaxhQxNhmZ8KSqGNNi-hjGhJQylYDHkeTMnqjzNJI6djqCy8vWZh9nD9");
		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		String userId = "tangyuan";
		WxArticle article = new WxArticle();
		File file = new File("F:/20160507161538.png");
		try {
			WxMediaUploadResult result = wxCpService.mediaUpload(WxConsts.MEDIA_IMAGE, file);
			WxCpMessage cpMmessage = WxCpMessage.IMAGE().mediaId(result.getMediaId()).agentId("0").toUser(userId)
					.build();
			wxCpService.messageSend(cpMmessage);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
