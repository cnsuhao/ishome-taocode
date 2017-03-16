package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信企业号表 */
@Named
public class WeiXinCompanyDBO extends MyDataBaseObjectSupport {
	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 应用密钥
	 */
	private String appSecret;

	/**
	 * 应用识别Token
	 */
	private String token;

	/**
	 * 微信ID
	 */
	private String wxId;

	/**
	 * 加密key
	 */
	private String aesKey;

	/**
	 * 支付商户号
	 */
	private String partnerId;

	/**
	 * 商户支付密钥
	 */
	private String partnerKey;

	/**
	 * 是否启用
	 */
	private String isUse;

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

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
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

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
