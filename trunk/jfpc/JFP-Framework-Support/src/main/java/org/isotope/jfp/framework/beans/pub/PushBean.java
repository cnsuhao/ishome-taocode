package org.isotope.jfp.framework.beans.pub;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;

/**
 * 短信接口Bean
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * 
 */
public class PushBean extends FrameworkDataBean {
	/**
	 * 医院ID
	 */
	protected String companyId;
	/**
	 * 用户ID
	 */
	protected String userId;
	/**
	 * 手机号码
	 */
	protected String phoneNum;
	/**
	 * 手机类型(A:安卓，P:苹果)
	 */
	protected String phoneType;
	/**
	 * 标题
	 */
	protected String title;
	/**
	 * 信息内容
	 */
	protected String message;
	/**
	 * 请求来源（1系统2企业）
	 */
	protected String sourceCmp;

	/**
	 * 用户令牌
	 */
	protected String token;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
