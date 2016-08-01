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
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private Long creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private Long updator = null;

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
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public Long getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public Long getUpdator() {
		return this.updator;
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

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(Long updator) {
		this.updator = updator;
	}

}
