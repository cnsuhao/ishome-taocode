package com.mcookies.qxy.common.SCourse;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 课表内容标签表 */
public class SCourseDBO extends MyDataBaseObjectSupport {
	/**
	 * 课程id
	 */
	private Long courseId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 课程名称
	 */
	private String courseName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取课程id
	 *
	 * @return Course_id 课程id
	 */
	public Long getCourseId() {
		return this.courseId;
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
	 * 获取课程名称
	 *
	 * @return Course_name 课程名称
	 */
	public String getCourseName() {
		return this.courseName;
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
	 * 设置课程id
	 *
	 * @param Course_id
	 *            课程id
	 */
	public void setCourseId(Long courseid) {
		this.courseId = courseid;
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
	 * 设置课程名称
	 *
	 * @param Course_name
	 *            课程名称
	 */
	public void setCourseName(String coursename) {
		this.courseName = coursename;
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
