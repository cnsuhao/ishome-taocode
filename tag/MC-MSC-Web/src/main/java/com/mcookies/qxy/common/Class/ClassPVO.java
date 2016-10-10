package com.mcookies.qxy.common.Class;

import javax.inject.Named;

@Named
/** 班级表 */
public class ClassPVO extends ClassDBO {

	/**
	 * 学期名称
	 */
	private String termName = null;

	/**
	 * 年级名称
	 */
	private String gradeName = null;

	/**
	 * 作息时间模板名称
	 */
	private String workName = null;

	/**
	 * 教师id
	 */
	private Long tid = null;

	/**
	 * 教师姓名
	 */
	private String teacherName = null;

	private Integer count = null;
	
	private Integer isLeader = null;

	/**
	 * 班主任姓名
	 */
	private String leaderName = null;

	/**
	 * 课程数
	 */
	private Integer courseCount;

	/**
	 * 学生数
	 */
	private Integer studentCount;

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public Integer getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}


}
