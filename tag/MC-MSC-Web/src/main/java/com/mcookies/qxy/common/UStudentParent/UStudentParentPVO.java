package com.mcookies.qxy.common.UStudentParent;

import javax.inject.Named;

@Named
/** 学生家长关联表 */
public class UStudentParentPVO extends UStudentParentDBO {

	/**
	 * 学生名字
	 */
	private String studentName = null;
	
	/**
	 * 班级Id
	 */
	private Long cid = null;
	
	/**
	 * 班级名称
	 */
	private String className = null;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	

}
