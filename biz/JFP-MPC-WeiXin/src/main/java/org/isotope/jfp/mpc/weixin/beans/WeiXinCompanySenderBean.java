package org.isotope.jfp.mpc.weixin.beans;

import org.isotope.jfp.framework.beans.message.info.CustomerSenderBean;

/**
 * 微信企业号信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanySenderBean extends CustomerSenderBean {
	/**
	 * 企业ID
	 */
	private String companyId;
	
	/**
	 * 企业名称
	 */	
	private String companyName;

	/**
	 * 应用ID
	 */
	private String appId = null;

	/**
	 * 密钥
	 */
	private String corpSecret = null;

	/**
	 * 应用识别Token
	 */
	private String token = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
