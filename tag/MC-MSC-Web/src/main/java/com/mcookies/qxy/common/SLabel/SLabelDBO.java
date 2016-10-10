package com.mcookies.qxy.common.SLabel;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 教工标签表 */
public class SLabelDBO extends MyDataBaseObjectSupport {
	/**
	 * 教工标签id
	 */
	private Long labelId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 教工标签名称
	 */
	private String labelName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取教工标签id
	 *
	 * @return Label_id 教工标签id
	 */
	public Long getLabelId() {
		return this.labelId;
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
	 * 获取教工标签名称
	 *
	 * @return Label_name 教工标签名称
	 */
	public String getLabelName() {
		return this.labelName;
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
	 * 设置教工标签id
	 *
	 * @param Label_id
	 *            教工标签id
	 */
	public void setLabelId(Long labelid) {
		this.labelId = labelid;
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
	 * 设置教工标签名称
	 *
	 * @param Label_name
	 *            教工标签名称
	 */
	public void setLabelName(String labelname) {
		this.labelName = labelname;
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
