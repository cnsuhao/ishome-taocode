package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信企业号关注用户表 */
@Named
public class WeixinUserDBO extends MyDataBaseObjectSupport {


	/**
	 * 用户ID
	 */
	protected String userId;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 用户类别
	 */
	private Integer uertType = null;
	
	/**
	 * 用户昵称
	 */
	private String userName = null;

	/**
	 * openId
	 */
	private String openid = null;

	/**
	 * 关注学校企业号ID
	 */
	private String schoolAppId = null;

	/**
	 * 用户部门ID
	 */
	private Integer uertDeptId = null;

	/**
	 * 用户标签ID
	 */
	private Integer uertTagId = null;
	
	/**
	 * 用户职位信息
	 */
	private String userPosition = null;
	
	/**
	 * 用户手机号码
	 */
	private String userMobile = null;
	
	/**
	 * 用户性别
	 */
	private String userGender = null;

	/**
	 * 用户email
	 */
	private String userEmail = null;

	/**
	 * 用户微信号
	 */
	private String userWeixinId = null;
	
	/**
	 * 用户成员头像的mediaid
	 */
	private String userAvatarMediaId = null;

	/**
	 * 用户扩展属性
	 */
	private String userExtattr = null;
	
	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getUertType() {
		return uertType;
	}

	public void setUertType(Integer uertType) {
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

	public String getSchoolAppId() {
		return schoolAppId;
	}

	public void setSchoolAppId(String schoolAppId) {
		this.schoolAppId = schoolAppId;
	}

	public Integer getUertDeptId() {
		return uertDeptId;
	}

	public void setUertDeptId(Integer uertDeptId) {
		this.uertDeptId = uertDeptId;
	}

	public Integer getUertTagId() {
		return uertTagId;
	}

	public void setUertTagId(Integer uertTagId) {
		this.uertTagId = uertTagId;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserWeixinId() {
		return userWeixinId;
	}

	public void setUserWeixinId(String userWeixinId) {
		this.userWeixinId = userWeixinId;
	}

	public String getUserAvatarMediaId() {
		return userAvatarMediaId;
	}

	public void setUserAvatarMediaId(String userAvatarMediaId) {
		this.userAvatarMediaId = userAvatarMediaId;
	}

	public String getUserExtattr() {
		return userExtattr;
	}

	public void setUserExtattr(String userExtattr) {
		this.userExtattr = userExtattr;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}


}
