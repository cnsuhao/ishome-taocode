package com.mcookies.qxy.common.SDutyScheduling;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 岗位人员排班表 */
public class SDutySchedulingDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 所属学期
	 */
	private Long termId = null;

	/**
	 * 岗位类型
	 */
	private Integer type = null;

	/**
	 * 指定该学期的对应周
	 */
	private Integer week = null;

	/**
	 * 周开始日期
	 */
	private Date startTime = null;

	/**
	 * 周结束日期
	 */
	private Date endTime = null;

	/**
	 * 指定该学期的对应日期
	 */
	private Date date = null;

	/**
	 * 值日或值周领导id
	 */
	private Long leaderTids = null;

	/**
	 * 值日或值周教师id
	 */
	private Long tids = null;

	/**
	 * 岗位id
	 */
	private Long dutyId = null;

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
	 * 获取所属学期
	 *
	 * @return Term_id 所属学期
	 */
	public Long getTermId() {
		return this.termId;
	}

	/**
	 * 获取岗位类型
	 *
	 * @return Type 岗位类型
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 获取指定该学期的对应周
	 *
	 * @return Week 指定该学期的对应周
	 */
	public Integer getWeek() {
		return this.week;
	}

	/**
	 * 获取周开始日期
	 *
	 * @return Start_time 周开始日期
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * 获取周结束日期
	 *
	 * @return End_time 周结束日期
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 获取指定该学期的对应日期
	 *
	 * @return Date 指定该学期的对应日期
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * 获取值日或值周领导id
	 *
	 * @return Leader_tids 值日或值周领导id
	 */
	public Long getLeaderTids() {
		return this.leaderTids;
	}

	/**
	 * 获取值日或值周教师id
	 *
	 * @return Tids 值日或值周教师id
	 */
	public Long getTids() {
		return this.tids;
	}

	/**
	 * 获取岗位id
	 *
	 * @return Duty_id 岗位id
	 */
	public Long getDutyId() {
		return this.dutyId;
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
	 * 设置所属学期
	 *
	 * @param Term_id
	 *            所属学期
	 */
	public void setTermId(Long termid) {
		this.termId = termid;
	}

	/**
	 * 设置岗位类型
	 *
	 * @param Type
	 *            岗位类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置指定该学期的对应周
	 *
	 * @param Week
	 *            指定该学期的对应周
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}

	/**
	 * 设置周开始日期
	 *
	 * @param Start_time
	 *            周开始日期
	 */
	public void setStartTime(Date starttime) {
		this.startTime = starttime;
	}

	/**
	 * 设置周结束日期
	 *
	 * @param End_time
	 *            周结束日期
	 */
	public void setEndTime(Date endtime) {
		this.endTime = endtime;
	}

	/**
	 * 设置指定该学期的对应日期
	 *
	 * @param Date
	 *            指定该学期的对应日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 设置值日或值周领导id
	 *
	 * @param Leader_tids
	 *            值日或值周领导id
	 */
	public void setLeaderTids(Long leadertids) {
		this.leaderTids = leadertids;
	}

	/**
	 * 设置值日或值周教师id
	 *
	 * @param Tids
	 *            值日或值周教师id
	 */
	public void setTids(Long tids) {
		this.tids = tids;
	}

	/**
	 * 设置岗位id
	 *
	 * @param Duty_id
	 *            岗位id
	 */
	public void setDutyId(Long dutyid) {
		this.dutyId = dutyid;
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
