package com.mcookies.qxy.common.LogSecurity;
import javax.inject.Named;
 
@Named
/** 安全日志表*/
public class LogSecurityPVO extends LogSecurityDBO
{
	/**
	 * 学生姓名
	 */
	private String studentName = null;
	/**
	 * 学号
	 */
	private Long number = null;
	
	/**
	 * 地点名字
	 */
	private String deviceName = null;
	
	private String startTime = null;
	private String endTime = null;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
