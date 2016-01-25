package org.ishome.jfp.framework.beands;

/**
 * 短信接口Bean
 * 
 * @author Spook
 * @version 2.0.1 2015/7/7
 * @since 2.0.1 2015/7/7
 * 
 */
public class PushBean extends FrameworkDataBean {
	/**
	 * 医院ID
	 */
	String hosId;
	/**
	 * 用户ID
	 */
	String userId;
	/**
	 * 手机号码
	 */
	String phoneNum;
	/**
	 * 手机类型
	 */
	String phoneType;
	/**
	 * 标题
	 */
	String title;
	/**
	 * 信息内容
	 */
	String message;
	/**
	 * 请求来源（1系统2医院）
	 */
	String sourceCmp;

	/**
	 * 用户令牌
	 */
	String token;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSourceCmp() {
		return sourceCmp;
	}

	public void setSourceCmp(String sourceCmp) {
		this.sourceCmp = sourceCmp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
