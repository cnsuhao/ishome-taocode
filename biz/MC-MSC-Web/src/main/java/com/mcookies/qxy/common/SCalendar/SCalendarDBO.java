package com.mcookies.qxy.common.SCalendar;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 校历表 */
public class SCalendarDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long calendarId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学期id
	 */
	private Long termId = null;

	/**
	 * 开始时间
	 */
	private Date startTime = null;

	/**
	 * 结束时间
	 */
	private Date endTime = null;

	/**
	 * 校历事项
	 */
	private String content = null;

	/**
	 * 责任部门
	 */
	private String department = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private Long creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private Long updator = null;

	/**
	 * 获取自增id
	 *
	 * @return Calendar_id 自增id
	 */
	public Long getCalendarId() {
		return this.calendarId;
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
	 * 获取学期id
	 *
	 * @return Term_id 学期id
	 */
	public Long getTermId() {
		return this.termId;
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
	 * 获取校历事项
	 *
	 * @return Content 校历事项
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 获取责任部门
	 *
	 * @return Department 责任部门
	 */
	public String getDepartment() {
		return this.department;
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
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public Long getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public Long getUpdator() {
		return this.updator;
	}

	/**
	 * 设置自增id
	 *
	 * @param Calendar_id
	 *            自增id
	 */
	public void setCalendarId(Long calendarid) {
		this.calendarId = calendarid;
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
	 * 设置学期id
	 *
	 * @param Term_id
	 *            学期id
	 */
	public void setTermId(Long termid) {
		this.termId = termid;
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
	 * 设置校历事项
	 *
	 * @param Content
	 *            校历事项
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置责任部门
	 *
	 * @param Department
	 *            责任部门
	 */
	public void setDepartment(String department) {
		this.department = department;
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

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(Long updator) {
		this.updator = updator;
	}

}
