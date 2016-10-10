package com.mcookies.qxy.common.ClassTeacher;

import javax.inject.Named;

@Named
/** 班级教师关联表 */
public class ClassTeacherPVO extends ClassTeacherDBO {
	private String courseName;
	private String teacherName;
	private String phone;
	private String email;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
