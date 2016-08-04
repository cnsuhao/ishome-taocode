package com.mcookies.qxy.common.DeviceTag;
import javax.inject.Named;
 
@Named
/** 设备标签表*/
public class DeviceTagPVO extends DeviceTagDBO
{
	private String schoolName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
