package com.mcookies.qxy.common.UStudentExt;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学生信息扩展表 */
public class UStudentExtDBO extends MyDataBaseObjectSupport {
	/**
	 * 学生id
	 */
	private Long studentId = null;

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

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取学生id
	 *
	 * @return Student_id 学生id
	 */
	public Long getStudentId() {
		return this.studentId;
	}

	/**
	 * 获取学籍照片
	 *
	 * @return Photo 学籍照片
	 */
	public String getPhoto() {
		return this.photo;
	}

	/**
	 * 获取民族
	 *
	 * @return Nation 民族
	 */
	public String getNation() {
		return this.nation;
	}

	/**
	 * 获取籍贯
	 *
	 * @return Native_place 籍贯
	 */
	public String getNativePlace() {
		return this.nativePlace;
	}

	/**
	 * 获取证件类型
	 *
	 * @return Card_type 证件类型
	 */
	public String getCardType() {
		return this.cardType;
	}

	/**
	 * 获取证件号码
	 *
	 * @return Card_number 证件号码
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * 获取是否港澳台侨胞
	 *
	 * @return Is_overseas 是否港澳台侨胞
	 */
	public Integer getIsOverseas() {
		return this.isOverseas;
	}

	/**
	 * 获取政治面貌
	 *
	 * @return Political 政治面貌
	 */
	public String getPolitical() {
		return this.political;
	}

	/**
	 * 获取宗教信仰
	 *
	 * @return Fith 宗教信仰
	 */
	public String getFith() {
		return this.fith;
	}

	/**
	 * 获取健康状况
	 *
	 * @return Health 健康状况
	 */
	public String getHealth() {
		return this.health;
	}

	/**
	 * 获取血型
	 *
	 * @return Blood 血型
	 */
	public String getBlood() {
		return this.blood;
	}

	/**
	 * 获取入学日期
	 *
	 * @return Date_of_school 入学日期
	 */
	public String getDateOfSchool() {
		return this.dateOfSchool;
	}

	/**
	 * 获取出生日期
	 *
	 * @return Date_of_birth 出生日期
	 */
	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * 获取学生类别
	 *
	 * @return Student_type 学生类别
	 */
	public String getStudentType() {
		return this.studentType;
	}

	/**
	 * 获取户口类型
	 *
	 * @return Residence_type 户口类型
	 */
	public String getResidenceType() {
		return this.residenceType;
	}

	/**
	 * 获取户口地址
	 *
	 * @return Residence_address 户口地址
	 */
	public String getResidenceAddress() {
		return this.residenceAddress;
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
	 * 设置学生id
	 *
	 * @param Student_id
	 *            学生id
	 */
	public void setStudentId(Long studentid) {
		this.studentId = studentid;
	}

	/**
	 * 设置学籍照片
	 *
	 * @param Photo
	 *            学籍照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * 设置民族
	 *
	 * @param Nation
	 *            民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * 设置籍贯
	 *
	 * @param Native_place
	 *            籍贯
	 */
	public void setNativePlace(String nativeplace) {
		this.nativePlace = nativeplace;
	}

	/**
	 * 设置证件类型
	 *
	 * @param Card_type
	 *            证件类型
	 */
	public void setCardType(String cardtype) {
		this.cardType = cardtype;
	}

	/**
	 * 设置证件号码
	 *
	 * @param Card_number
	 *            证件号码
	 */
	public void setCardNumber(String cardnumber) {
		this.cardNumber = cardnumber;
	}

	/**
	 * 设置是否港澳台侨胞
	 *
	 * @param Is_overseas
	 *            是否港澳台侨胞
	 */
	public void setIsOverseas(Integer isoverseas) {
		this.isOverseas = isoverseas;
	}

	/**
	 * 设置政治面貌
	 *
	 * @param Political
	 *            政治面貌
	 */
	public void setPolitical(String political) {
		this.political = political;
	}

	/**
	 * 设置宗教信仰
	 *
	 * @param Fith
	 *            宗教信仰
	 */
	public void setFith(String fith) {
		this.fith = fith;
	}

	/**
	 * 设置健康状况
	 *
	 * @param Health
	 *            健康状况
	 */
	public void setHealth(String health) {
		this.health = health;
	}

	/**
	 * 设置血型
	 *
	 * @param Blood
	 *            血型
	 */
	public void setBlood(String blood) {
		this.blood = blood;
	}

	/**
	 * 设置入学日期
	 *
	 * @param Date_of_school
	 *            入学日期
	 */
	public void setDateOfSchool(String dateofschool) {
		this.dateOfSchool = dateofschool;
	}

	/**
	 * 设置出生日期
	 *
	 * @param Date_of_birth
	 *            出生日期
	 */
	public void setDateOfBirth(String dateofbirth) {
		this.dateOfBirth = dateofbirth;
	}

	/**
	 * 设置学生类别
	 *
	 * @param Student_type
	 *            学生类别
	 */
	public void setStudentType(String studenttype) {
		this.studentType = studenttype;
	}

	/**
	 * 设置户口类型
	 *
	 * @param Residence_type
	 *            户口类型
	 */
	public void setResidenceType(String residencetype) {
		this.residenceType = residencetype;
	}

	/**
	 * 设置户口地址
	 *
	 * @param Residence_address
	 *            户口地址
	 */
	public void setResidenceAddress(String residenceaddress) {
		this.residenceAddress = residenceaddress;
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
