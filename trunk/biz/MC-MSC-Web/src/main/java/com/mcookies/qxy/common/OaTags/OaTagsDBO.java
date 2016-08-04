package com.mcookies.qxy.common.OaTags;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** OA标签表 */
public class OaTagsDBO extends MyDataBaseObjectSupport {
	/**
	 * 标签id
	 */
	private Long oatagsId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 标签名称
	 */
	private String oatagsName = null;

	/**
	 * 标签类型
	 */
	private Integer type = null;

	/**
	 * 审核周期
	 */
	private String cycle = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取标签id
	 *
	 * @return Oatags_id 标签id
	 */
	public Long getOatagsId() {
		return this.oatagsId;
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
	 * 获取标签名称
	 *
	 * @return Oatags_name 标签名称
	 */
	public String getOatagsName() {
		return this.oatagsName;
	}

	/**
	 * 获取标签类型
	 *
	 * @return Type 标签类型
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 获取审核周期
	 *
	 * @return Cycle 审核周期
	 */
	public String getCycle() {
		return this.cycle;
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
	 * 设置标签id
	 *
	 * @param Oatags_id
	 *            标签id
	 */
	public void setOatagsId(Long oatagsid) {
		this.oatagsId = oatagsid;
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
	 * 设置标签名称
	 *
	 * @param Oatags_name
	 *            标签名称
	 */
	public void setOatagsName(String oatagsname) {
		this.oatagsName = oatagsname;
	}

	/**
	 * 设置标签类型
	 *
	 * @param Type
	 *            标签类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置审核周期
	 *
	 * @param Cycle
	 *            审核周期
	 */
	public void setCycle(String cycle) {
		this.cycle = cycle;
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
