package com.mcookies.qxy.common.DeviceAlarm;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 设备报警表 */
public class DeviceAlarmDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学校设备id
	 */
	private Long deviceId = null;

	/**
	 * 报警信息
	 */
	private String alarmInformation = null;

	/**
	 * 报警时间
	 */
	private String alarmTime = null;

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
	 * 获取学校设备id
	 *
	 * @return Device_id 学校设备id
	 */
	public Long getDeviceId() {
		return this.deviceId;
	}

	/**
	 * 获取报警信息
	 *
	 * @return Alarm_information 报警信息
	 */
	public String getAlarmInformation() {
		return this.alarmInformation;
	}

	/**
	 * 获取报警时间
	 *
	 * @return Alarm_time 报警时间
	 */
	public String getAlarmTime() {
		return this.alarmTime;
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
	 * 设置学校设备id
	 *
	 * @param Device_id
	 *            学校设备id
	 */
	public void setDeviceId(Long deviceid) {
		this.deviceId = deviceid;
	}

	/**
	 * 设置报警信息
	 *
	 * @param Alarm_information
	 *            报警信息
	 */
	public void setAlarmInformation(String alarminformation) {
		this.alarmInformation = alarminformation;
	}

	/**
	 * 设置报警时间
	 *
	 * @param Alarm_time
	 *            报警时间
	 */
	public void setAlarmTime(String alarmtime) {
		this.alarmTime = alarmtime;
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
