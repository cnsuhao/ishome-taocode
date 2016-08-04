package com.mcookies.qxy.common.PagePersonal;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 个人权限页面表 */
public class PagePersonalDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long personalId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 教工id
	 */
	private Long tid = null;

	/**
	 * 页面权限id
	 */
	private Long jurisdictionId = null;

	/**
	 * 权限json
	 */
	private String tJson = null;

	/**
	 * 获取自增id
	 *
	 * @return Personal_id 自增id
	 */
	public Long getPersonalId() {
		return this.personalId;
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
	 * 获取教工id
	 *
	 * @return Tid 教工id
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取页面权限id
	 *
	 * @return Jurisdiction_id 页面权限id
	 */
	public Long getJurisdictionId() {
		return this.jurisdictionId;
	}

	/**
	 * 获取权限json
	 *
	 * @return T_json 权限json
	 */
	public String getTJson() {
		return this.tJson;
	}

	/**
	 * 设置自增id
	 *
	 * @param Personal_id
	 *            自增id
	 */
	public void setPersonalId(Long personalid) {
		this.personalId = personalid;
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
	 * 设置教工id
	 *
	 * @param Tid
	 *            教工id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置页面权限id
	 *
	 * @param Jurisdiction_id
	 *            页面权限id
	 */
	public void setJurisdictionId(Long jurisdictionid) {
		this.jurisdictionId = jurisdictionid;
	}

	/**
	 * 设置权限json
	 *
	 * @param T_json
	 *            权限json
	 */
	public void setTJson(String tjson) {
		this.tJson = tjson;
	}

}
