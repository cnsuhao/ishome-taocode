package org.jfpc.base.bean;

import javax.inject.Named;

import org.jfpc.base.helper.DateHelper;
import org.jfpc.base.helper.TokenHelper;

/**
 * 数据交换关键信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/3/24
 */
@Named
public class TokenBean extends ObjectBean {
	
	public static TokenBean buildTokenBean(String token){
		return TokenHelper.getTokenBean(token);
	}	
	
	/**
	 * 用户ID
	 */
	private String userId = "";
	/**
	 * 企业ID
	 */
	private String companyId = "";
	/**
	 * 登录时间
	 */
	private String loginDateTime = DateHelper.currentTimestamp();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String productId) {
		this.companyId = productId;
	}

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

}
