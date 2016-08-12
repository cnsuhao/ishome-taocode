package com.mcookies.qxy.common.LogAttendance;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 考勤日志表 */
public class LogAttendanceDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学生卡id
	 */
	private Long rfid = null;

	/**
	 * 学校设备id
	 */
	private Long deviceId = null;

	/**
	 * 打卡时间
	 */
	private String markTime = null;

	/**
	 * 行为状态
	 */
	private Integer flag = null;

	/**
	 * 接收原文日志json
	 */
	private String sourceJson = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学期id
	 */
	private Long termId = null;

	/**
	 * 班级id
	 */
	private Long cid = null;

	/**
	 * 学生id
	 */
	private Long studentId = null;
	
	
	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 获取学生卡id
	 *
	 * @return Rfid 学生卡id
	 */
	public Long getRfid() {
		return this.rfid;
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
	 * 获取打卡时间
	 *
	 * @return Mark_time 打卡时间
	 */
	public String getMarkTime() {
		return this.markTime;
	}

	/**
	 * 获取行为状态
	 *
	 * @return Flag 行为状态
	 */
	public Integer getFlag() {
		return this.flag;
	}

	/**
	 * 获取接收原文日志json
	 *
	 * @return Source_json 接收原文日志json
	 */
	public String getSourceJson() {
		return this.sourceJson;
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
	 * 设置学生卡id
	 *
	 * @param Rfid
	 *            学生卡id
	 */
	public void setRfid(Long rfid) {
		this.rfid = rfid;
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
	 * 设置打卡时间
	 *
	 * @param Mark_time
	 *            打卡时间
	 */
	public void setMarkTime(String marktime) {
		this.markTime = marktime;
	}

	/**
	 * 设置行为状态
	 *
	 * @param Flag
	 *            行为状态
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * 设置接收原文日志json
	 *
	 * @param Source_json
	 *            接收原文日志json
	 */
	public void setSourceJson(String sourcejson) {
		this.sourceJson = sourcejson;
	}

}
