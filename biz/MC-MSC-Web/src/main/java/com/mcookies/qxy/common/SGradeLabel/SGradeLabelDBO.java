package com.mcookies.qxy.common.SGradeLabel;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 年级标签表 */
public class SGradeLabelDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long gradeId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 年级名称
	 */
	private String gradeName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取自增id
	 *
	 * @return Grade_id 自增id
	 */
	public Long getGradeId() {
		return this.gradeId;
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
	 * 获取年级名称
	 *
	 * @return Grade_name 年级名称
	 */
	public String getGradeName() {
		return this.gradeName;
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
	 * 设置自增id
	 *
	 * @param Grade_id
	 *            自增id
	 */
	public void setGradeId(Long gradeid) {
		this.gradeId = gradeid;
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
	 * 设置年级名称
	 *
	 * @param Grade_name
	 *            年级名称
	 */
	public void setGradeName(String gradename) {
		this.gradeName = gradename;
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
