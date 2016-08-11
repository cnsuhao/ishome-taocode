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
	 * 是否为缺省学生
	 */
	private Integer isDefault = null;

	/**
	 * 学生家长id
	 */
	private Long id = null;

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

}
