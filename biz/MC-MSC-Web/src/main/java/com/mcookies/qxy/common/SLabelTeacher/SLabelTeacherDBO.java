package com.mcookies.qxy.common.SLabelTeacher;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 教工标签关联表 */
public class SLabelTeacherDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 群组标签id
	 */
	private Long labelId = null;

	/**
	 * 教师id
	 */
	private Long tid = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
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
	 * 获取群组标签id
	 *
	 * @return Label_id 群组标签id
	 */
	public Long getLabelId() {
		return this.labelId;
	}

	/**
	 * 获取教师id
	 *
	 * @return Tid 教师id
	 */
	public Long getTid() {
		return this.tid;
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
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 设置群组标签id
	 *
	 * @param Label_id
	 *            群组标签id
	 */
	public void setLabelId(Long labelid) {
		this.labelId = labelid;
	}

	/**
	 * 设置教师id
	 *
	 * @param Tid
	 *            教师id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
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
