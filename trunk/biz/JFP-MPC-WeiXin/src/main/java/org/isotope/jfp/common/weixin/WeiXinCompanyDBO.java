package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信企业号表 */
@Named
public class WeiXinCompanyDBO extends MyDataBaseObjectSupport {

	/**
	 * 企业ID
	 */
	protected String companyId;

	/**
	 * 应用ID
	 */
	private String appId = null;

	/**
	 * 用用密钥
	 */
	private String appSecret = null;

	/**
	 * 应用识别Token
	 */
	private String token = null;

	/**
	 * 加密key
	 */
	private String aesKey = null;

	/**
	 * 支付商户号
	 */
	private String partnerId = null;

	/**
	 * 商户支付密钥
	 */
	private String partnerKey = null;

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

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}



}
