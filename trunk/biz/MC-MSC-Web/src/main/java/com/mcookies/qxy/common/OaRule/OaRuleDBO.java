package com.mcookies.qxy.common.OaRule;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** OA规则表 */
public class OaRuleDBO extends MyDataBaseObjectSupport {
	/**
	 * 规则id
	 */
	private Long oaruleId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 标签id
	 */
	private Long oatagsId = null;

	/**
	 * 规则名称
	 */
	private String oaruleName = null;

	/**
	 * 规则序号
	 */
	private Integer serialNumber = null;

	/**
	 * 规则通过方式
	 */
	private String adoptType = null;

	/**
	 * 审核人列表
	 */
	private String tids = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取规则id
	 *
	 * @return Oarule_id 规则id
	 */
	public Long getOaruleId() {
		return this.oaruleId;
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
	 * 获取标签id
	 *
	 * @return Oatags_id 标签id
	 */
	public Long getOatagsId() {
		return this.oatagsId;
	}

	/**
	 * 获取规则名称
	 *
	 * @return Oarule_name 规则名称
	 */
	public String getOaruleName() {
		return this.oaruleName;
	}

	/**
	 * 获取规则序号
	 *
	 * @return Serial_number 规则序号
	 */
	public Integer getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * 获取规则通过方式
	 *
	 * @return Adopt_type 规则通过方式
	 */
	public String getAdoptType() {
		return this.adoptType;
	}

	/**
	 * 获取审核人列表
	 *
	 * @return Tids 审核人列表
	 */
	public String getTids() {
		return this.tids;
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
	 * 设置规则id
	 *
	 * @param Oarule_id
	 *            规则id
	 */
	public void setOaruleId(Long oaruleid) {
		this.oaruleId = oaruleid;
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
	 * 设置标签id
	 *
	 * @param Oatags_id
	 *            标签id
	 */
	public void setOatagsId(Long oatagsid) {
		this.oatagsId = oatagsid;
	}

	/**
	 * 设置规则名称
	 *
	 * @param Oarule_name
	 *            规则名称
	 */
	public void setOaruleName(String oarulename) {
		this.oaruleName = oarulename;
	}

	/**
	 * 设置规则序号
	 *
	 * @param Serial_number
	 *            规则序号
	 */
	public void setSerialNumber(Integer serialnumber) {
		this.serialNumber = serialnumber;
	}

	/**
	 * 设置规则通过方式
	 *
	 * @param Adopt_type
	 *            规则通过方式
	 */
	public void setAdoptType(String adopttype) {
		this.adoptType = adopttype;
	}

	/**
	 * 设置审核人列表
	 *
	 * @param Tids
	 *            审核人列表
	 */
	public void setTids(String tids) {
		this.tids = tids;
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
