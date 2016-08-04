package com.mcookies.qxy.common.StudentRfid;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学生一卡通关联表 */
public class StudentRfidDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 学生id
	 */
	private Long studentId = null;

	/**
	 * 学生卡id
	 */
	private Long rfid = null;

	/**
	 * 是否有效
	 */
	private Integer isEffective = null;

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
	 * 获取学生卡id
	 *
	 * @return Rfid 学生卡id
	 */
	public Long getRfid() {
		return this.rfid;
	}

	/**
	 * 获取是否有效
	 *
	 * @return Is_effective 是否有效
	 */
	public Integer getIsEffective() {
		return this.isEffective;
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
	 * 设置学生卡id
	 *
	 * @param Rfid
	 *            学生卡id
	 */
	public void setRfid(Long rfid) {
		this.rfid = rfid;
	}

	/**
	 * 设置是否有效
	 *
	 * @param Is_effective
	 *            是否有效
	 */
	public void setIsEffective(Integer iseffective) {
		this.isEffective = iseffective;
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
