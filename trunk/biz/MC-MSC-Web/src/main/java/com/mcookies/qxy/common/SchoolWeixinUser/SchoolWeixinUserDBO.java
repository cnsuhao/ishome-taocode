package com.mcookies.qxy.common.SchoolWeixinUser;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学校微信企业号关注用户表 */
public class SchoolWeixinUserDBO extends MyDataBaseObjectSupport {
	/**
	 * 数据id
	 */
	private Long pid = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 用户id
	 */
	private Long uid = null;

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
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取数据id
	 *
	 * @return Pid 数据id
	 */
	public Long getPid() {
		return this.pid;
	}

	/**
	 * 获取学校id
	 *
	 * @return Sid 学校id
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取用户id
	 *
	 * @return Uid 用户id
	 */
	public Long getUid() {
		return this.uid;
	}

	/**
	 * 获取用户类别
	 *
	 * @return Uert_type 用户类别
	 */
	public Integer getUertType() {
		return this.uertType;
	}

	/**
	 * 获取用户昵称
	 *
	 * @return User_name 用户昵称
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 获取openId
	 *
	 * @return Openid openId
	 */
	public String getOpenid() {
		return this.openid;
	}

	/**
	 * 获取关注学校企业号ID
	 *
	 * @return School_app_id 关注学校企业号ID
	 */
	public String getSchoolAppId() {
		return this.schoolAppId;
	}

	/**
	 * 获取是否启用
	 *
	 * @return Is_use 是否启用
	 */
	public Integer getIsUse() {
		return this.isUse;
	}

	/**
	 * 设置数据id
	 *
	 * @param Pid
	 *            数据id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * 设置学校id
	 *
	 * @param Sid
	 *            学校id
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置用户id
	 *
	 * @param Uid
	 *            用户id
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 设置用户类别
	 *
	 * @param Uert_type
	 *            用户类别
	 */
	public void setUertType(Integer uerttype) {
		this.uertType = uerttype;
	}

	/**
	 * 设置用户昵称
	 *
	 * @param User_name
	 *            用户昵称
	 */
	public void setUserName(String username) {
		this.userName = username;
	}

	/**
	 * 设置openId
	 *
	 * @param Openid
	 *            openId
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 设置关注学校企业号ID
	 *
	 * @param School_app_id
	 *            关注学校企业号ID
	 */
	public void setSchoolAppId(String schoolappid) {
		this.schoolAppId = schoolappid;
	}

	/**
	 * 设置是否启用
	 *
	 * @param Is_use
	 *            是否启用
	 */
	public void setIsUse(Integer isuse) {
		this.isUse = isuse;
	}

}
