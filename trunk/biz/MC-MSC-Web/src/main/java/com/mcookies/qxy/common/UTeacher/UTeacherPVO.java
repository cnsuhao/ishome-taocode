package com.mcookies.qxy.common.UTeacher;

import javax.inject.Named;

@Named
/** 教师表 */
public class UTeacherPVO extends UTeacherDBO {
	/**
	 * 教师性别
	 */
	private Integer gender = null;

	/**
	 * 出生年月
	 */
	private String dateOfBirth = null;

	/**
	 * 开始工作时间
	 */
	private String startWorkT = null;
	/**
	 * 教师工号
	 */
	private String jobNumber = null;

	/**
	 * 证件类型
	 */
	private Integer cardType = null;

	/**
	 * 证件号码
	 */
	private String cardNumber = null;

	/**
	 * 家庭住址
	 */
	private String homeAddress = null;

	/**
	 * 班级id
	 */
	private Long cid = null;

	private Long termId = null;

	private String courseName = null;

	private Long courseId = null;

	private Integer isLeader = null;

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStartWorkT() {
		return startWorkT;
	}

	public void setStartWorkT(String startWorkT) {
		this.startWorkT = startWorkT;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

}
