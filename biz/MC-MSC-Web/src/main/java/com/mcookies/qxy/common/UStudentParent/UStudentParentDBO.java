package com.mcookies.qxy.common.UStudentParent;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学生家长关联表 */
public class UStudentParentDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 家长id
	 */
	private Long parentId = null;

	/**
	 * 家长角色
	 */
	private Integer role = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学生id
	 */
	private Long studentId = null;

	/**
	 * 是否为缺省学生
	 */
	private Integer isDefault = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 获取家长id
	 *
	 * @return Parent_id 家长id
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 * 获取家长角色
	 *
	 * @return Role 家长角色
	 */
	public Integer getRole() {
		return this.role;
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
	 * 获取学生id
	 *
	 * @return Student_id 学生id
	 */
	public Long getStudentId() {
		return this.studentId;
	}

	/**
	 * 获取是否为缺省学生
	 *
	 * @return Is_default 是否为缺省学生
	 */
	public Integer getIsDefault() {
		return this.isDefault;
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
	 * 设置自增id
	 *
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置家长id
	 *
	 * @param Parent_id
	 *            家长id
	 */
	public void setParentId(Long parentid) {
		this.parentId = parentid;
	}

	/**
	 * 设置家长角色
	 *
	 * @param Role
	 *            家长角色
	 */
	public void setRole(Integer role) {
		this.role = role;
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
	 * 设置学生id
	 *
	 * @param Student_id
	 *            学生id
	 */
	public void setStudentId(Long studentid) {
		this.studentId = studentid;
	}

	/**
	 * 设置是否为缺省学生
	 *
	 * @param Is_default
	 *            是否为缺省学生
	 */
	public void setIsDefault(Integer isdefault) {
		this.isDefault = isdefault;
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
