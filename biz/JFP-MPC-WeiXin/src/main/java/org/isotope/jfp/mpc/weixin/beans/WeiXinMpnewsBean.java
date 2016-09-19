package org.isotope.jfp.mpc.weixin.beans;

import java.util.List;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.beans.message.info.MessageValueBean;

public class WeiXinMpnewsBean extends MessageValueBean implements ISWeixinConstants {
	/**
	 * 信息内容
	 */
	private String message;

	/**
	 * 媒体类型
	 */
	private String mediaType = MEDIA_MPNEWS;
	
	/*
	 * 消息内容
	 */
	private List<WeiXinArticlesBean> articles;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public List<WeiXinArticlesBean> getArticles() {
		return articles;
	}

	public void setArticles(List<WeiXinArticlesBean> articles) {
		this.articles = articles;
	}
	
	
}
