package org.isotope.jfp.mpc.weixin.beans.sender;

import org.isotope.jfp.framework.beans.message.info.CustomerSenderBean;

/**
 * 微信企业号信息 <br>
 * 参考【微信企业号表】company_weixin
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
	private String appSecret = null;

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

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
