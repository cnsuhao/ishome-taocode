package org.isotope.jfp.framework.constants.pub;

/**
 * 推送中心关键字
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public interface ISPushConstant {
	public final static String SEND_SUCCESS = "发送成功";
	public final static String SEND_FAIL = "发送失败";

	/**
	 * 网关类别
	 */
	public enum MessageType {
		MESSAGE// 站内信
		, MAIL// 邮件
		, SMS // 短信
		, PUSH_Android // 安卓推送
		, PUSH_IOS // 苹果推送
		, WeiXin // 微信
		, ZhiFuBao // 支付宝
		, NONE;
	}

	public static final String PUSH_CHANNEL = "PUSH:CHANNEL";
	public static final String PUSH_CHANNEL_ANDROID = "PUSH:CHANNEL:ANDROID";
	public static final String PUSH_CHANNEL_IOS = "PUSH:CHANNEL:IOS";
	public static final String PUSH_CHANNEL_ALIPAY = "PUSH:CHANNEL:ALIPAY";
	public static final String PUSH_CHANNEL_WEIXIN = "PUSH:CHANNEL:WEIXIN";
}
