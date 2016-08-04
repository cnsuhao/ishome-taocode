package com.mcookies.qxy.common.STerm;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学期设置表 */
public class STermDBO extends MyDataBaseObjectSupport {
	/**
	 * 学期id
	 */
	private Long termId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学期名称
	 */
	private String termName = null;

	/**
	 * 开始时间
	 */
	private Date startTime = null;

	/**
	 * 结束时间
	 */
	private Date endTime = null;

	/**
	 * 是否为缺省学期
	 */
	private Integer isDefault = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取学期id
	 *
	 * @return Term_id 学期id
	 */
	public Long getTermId() {
		return this.termId;
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
	 * 获取学期名称
	 *
	 * @return Term_name 学期名称
	 */
	public String getTermName() {
		return this.termName;
	}

	/**
	 * 获取开始时间
	 *
	 * @return Start_time 开始时间
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * 获取结束时间
	 *
	 * @return End_time 结束时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 获取是否为缺省学期
	 *
	 * @return Is_default 是否为缺省学期
	 */
	public Integer getIsDefault() {
		return this.isDefault;
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
	 * 设置学期id
	 *
	 * @param Term_id
	 *            学期id
	 */
	public void setTermId(Long termid) {
		this.termId = termid;
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
	 * 设置学期名称
	 *
	 * @param Term_name
	 *            学期名称
	 */
	public void setTermName(String termname) {
		this.termName = termname;
	}

	/**
	 * 设置开始时间
	 *
	 * @param Start_time
	 *            开始时间
	 */
	public void setStartTime(Date starttime) {
		this.startTime = starttime;
	}

	/**
	 * 设置结束时间
	 *
	 * @param End_time
	 *            结束时间
	 */
	public void setEndTime(Date endtime) {
		this.endTime = endtime;
	}

	/**
	 * 设置是否为缺省学期
	 *
	 * @param Is_default
	 *            是否为缺省学期
	 */
	public void setIsDefault(Integer isdefault) {
		this.isDefault = isdefault;
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
