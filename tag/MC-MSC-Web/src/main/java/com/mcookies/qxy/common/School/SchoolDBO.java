package com.mcookies.qxy.common.School;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学校表 */
public class SchoolDBO extends MyDataBaseObjectSupport {
	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 创建人id
	 */
	private Long createUid = null;

	/**
	 * 学校名字
	 */
	private String schoolName = null;

	/**
	 * 联系人
	 */
	private String contacts = null;

	/**
	 * 电话
	 */
	private String phone = null;

	/**
	 * 地址
	 */
	private String address = null;

	/**
	 * 学校识别码(不启用)
	 */
	private String sCode = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取学校id
	 *
	 * @return Sid 学校id
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取创建人id
	 *
	 * @return Create_uid 创建人id
	 */
	public Long getCreateUid() {
		return this.createUid;
	}

	/**
	 * 获取学校名字
	 *
	 * @return School_name 学校名字
	 */
	public String getSchoolName() {
		return this.schoolName;
	}

	/**
	 * 获取联系人
	 *
	 * @return Contacts 联系人
	 */
	public String getContacts() {
		return this.contacts;
	}

	/**
	 * 获取电话
	 *
	 * @return Phone 电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 获取地址
	 *
	 * @return Address 地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 获取学校识别码(不启用)
	 *
	 * @return S_code 学校识别码(不启用)
	 */
	public String getSCode() {
		return this.sCode;
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
	 * 设置学校id
	 *
	 * @param Sid
	 *            学校id
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置创建人id
	 *
	 * @param Create_uid
	 *            创建人id
	 */
	public void setCreateUid(Long createuid) {
		this.createUid = createuid;
	}

	/**
	 * 设置学校名字
	 *
	 * @param School_name
	 *            学校名字
	 */
	public void setSchoolName(String schoolname) {
		this.schoolName = schoolname;
	}

	/**
	 * 设置联系人
	 *
	 * @param Contacts
	 *            联系人
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * 设置电话
	 *
	 * @param Phone
	 *            电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 设置地址
	 *
	 * @param Address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 设置学校识别码(不启用)
	 *
	 * @param S_code
	 *            学校识别码(不启用)
	 */
	public void setSCode(String scode) {
		this.sCode = scode;
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
