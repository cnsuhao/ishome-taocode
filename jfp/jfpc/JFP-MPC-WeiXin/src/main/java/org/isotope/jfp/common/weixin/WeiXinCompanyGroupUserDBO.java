package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 微信企业号用户所属用户组表 */
public class WeiXinCompanyGroupUserDBO extends MyDataBaseObjectSupport {

	/**
	 * 学校id
	 */
	private String companyId;

	/**
	 * 用户组id
	 */
	private String groupid;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户类别
	 */
	private String uertType;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * openId
	 */
	private String openid;

	/**
	 * 微信ID
	 */
	private String wxId;
	private String mobile;
	private String email;
	private String weiXinId;
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeiXinId() {
		return weiXinId;
	}

	public void setWeiXinId(String weiXinId) {
		this.weiXinId = weiXinId;
	}

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

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUertType() {
		return uertType;
	}

	public void setUertType(String uertType) {
		this.uertType = uertType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	private String wxStatus;
	private String serviceStatus;
	public String getWxStatus() {
		return wxStatus;
	}

	public void setWxStatus(String wxStatus) {
		this.wxStatus = wxStatus;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
	
}
