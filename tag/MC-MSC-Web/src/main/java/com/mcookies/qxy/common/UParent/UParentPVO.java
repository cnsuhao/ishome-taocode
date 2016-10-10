package com.mcookies.qxy.common.UParent;

import javax.inject.Named;

@Named
/** 家长表 */
public class UParentPVO extends UParentDBO {
	/**
	 * 家长角色
	 */
	private Integer role = null;

	/**
	 * 学生id
	 */
	private Long studentId = null;
	
	/**
	 * 学生name
	 */
	private String studentName = null;
	
	/**
	 * 是否为缺省学生
	 */
	private Integer isDefault = null;

	/**
	 * 学生家长id
	 */
	private Long id = null;
	
	private Long cid;
	
	private Long termId;

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
