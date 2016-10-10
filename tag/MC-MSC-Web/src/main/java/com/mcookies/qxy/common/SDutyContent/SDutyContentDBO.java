package com.mcookies.qxy.common.SDutyContent;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 岗位内容设置表 */
public class SDutyContentDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 岗位id
	 */
	private Long dutyId = null;

	/**
	 * 岗位职责
	 */
	private String content = null;

	/**
	 * 开始时间
	 */
	private String startTime = null;

	/**
	 * 结束时间
	 */
	private String endTime = null;

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
	 * 获取岗位id
	 *
	 * @return Duty_id 岗位id
	 */
	public Long getDutyId() {
		return this.dutyId;
	}

	/**
	 * 获取岗位职责
	 *
	 * @return Content 岗位职责
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 获取开始时间
	 *
	 * @return Start_time 开始时间
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * 获取结束时间
	 *
	 * @return End_time 结束时间
	 */
	public String getEndTime() {
		return this.endTime;
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
	 * 设置岗位id
	 *
	 * @param Duty_id
	 *            岗位id
	 */
	public void setDutyId(Long dutyid) {
		this.dutyId = dutyid;
	}

	/**
	 * 设置岗位职责
	 *
	 * @param Content
	 *            岗位职责
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置开始时间
	 *
	 * @param Start_time
	 *            开始时间
	 */
	public void setStartTime(String starttime) {
		this.startTime = starttime;
	}

	/**
	 * 设置结束时间
	 *
	 * @param End_time
	 *            结束时间
	 */
	public void setEndTime(String endtime) {
		this.endTime = endtime;
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
