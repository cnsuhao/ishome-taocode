package com.mcookies.qxy.common.STrip;
import javax.inject.Named;
 
@Named
/** 行程表*/
public class STripPVO extends STripDBO
{
	/**
	 * 教师姓名
	 */
	private String teacherName = null;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
