package com.mcookies.qxy.common.UStudent;

import javax.inject.Named;

@Named
/** 学生表 */
public class UStudentPVO extends UStudentDBO {
	private String termName = null;
	private String gradeName = null;
	private String className = null;

	private Long cid;

	/**
	 * 学籍照片
	 */
	private String photo = null;

	/**
	 * 民族
	 */
	private String nation = null;

	/**
	 * 籍贯
	 */
	private String nativePlace = null;

	/**
	 * 证件类型
	 */
	private String cardType = null;

	/**
	 * 证件号码
	 */
	private String cardNumber = null;

	/**
	 * 是否港澳台侨胞
	 */
	private Integer isOverseas = null;

	/**
	 * 政治面貌
	 */
	private String political = null;

	/**
	 * 宗教信仰
	 */
	private String fith = null;

	/**
	 * 健康状况
	 */
	private String health = null;

	/**
	 * 血型
	 */
	private String blood = null;

	/**
	 * 入学日期
	 */
	private String dateOfSchool = null;

	/**
	 * 出生日期
	 */
	private String dateOfBirth = null;

	/**
	 * 学生类别
	 */
	private String studentType = null;

	/**
	 * 户口类型
	 */
	private String residenceType = null;

	/**
	 * 户口地址
	 */
	private String residenceAddress = null;

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getIsOverseas() {
		return isOverseas;
	}

	public void setIsOverseas(Integer isOverseas) {
		this.isOverseas = isOverseas;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getFith() {
		return fith;
	}

	public void setFith(String fith) {
		this.fith = fith;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getDateOfSchool() {
		return dateOfSchool;
	}

	public void setDateOfSchool(String dateOfSchool) {
		this.dateOfSchool = dateOfSchool;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

}
