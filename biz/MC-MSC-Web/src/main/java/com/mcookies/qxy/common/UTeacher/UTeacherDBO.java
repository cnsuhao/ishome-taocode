package com.mcookies.qxy.common.UTeacher;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 教师表 */
public class UTeacherDBO extends MyDataBaseObjectSupport {
	/**
	 * 教师id（自增）
	 */
	private Long tid = null;

	/**
	 * 用户id
	 */
	private Long uid = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 教师姓名
	 */
	private String teacherName = null;

	/**
	 * 联系电话
	 */
	private String phone = null;

	/**
	 * 邮箱
	 */
	private String email = null;

	/**
	 * 用户类型
	 */
	private Integer idType = null;//默认值为0.

	/**
	 * 认证方式
	 */
	private Integer method = null;

	/**
	 * openid
	 */
	private String openid = null;

	/**
	 * 微信名（不启用）
	 */
	private String wechatname = null;

	/**
	 * 微信头像
	 */
	private String wechathead = null;

	/**
	 * 城市
	 */
	private String city = null;

	/**
	 * 性别
	 */
	private Integer gender = null;

	/**
	 * 状态
	 */
	private Integer status = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取教师id（自增）
	 *
	 * @return Tid 教师id（自增）
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取用户id
	 *
	 * @return Uid 用户id
	 */
	public Long getUid() {
		return this.uid;
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
	 * 获取教师姓名
	 *
	 * @return Teacher_name 教师姓名
	 */
	public String getTeacherName() {
		return this.teacherName;
	}

	/**
	 * 获取联系电话
	 *
	 * @return Phone 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 获取邮箱
	 *
	 * @return Email 邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 获取用户类型
	 *
	 * @return Id_type 用户类型
	 */
	public Integer getIdType() {
		return this.idType;
	}

	/**
	 * 获取认证方式
	 *
	 * @return Method 认证方式
	 */
	public Integer getMethod() {
		return this.method;
	}

	/**
	 * 获取openid
	 *
	 * @return Openid openid
	 */
	public String getOpenid() {
		return this.openid;
	}

	/**
	 * 获取微信名（不启用）
	 *
	 * @return Wechatname 微信名（不启用）
	 */
	public String getWechatname() {
		return this.wechatname;
	}

	/**
	 * 获取微信头像
	 *
	 * @return Wechathead 微信头像
	 */
	public String getWechathead() {
		return this.wechathead;
	}

	/**
	 * 获取城市
	 *
	 * @return City 城市
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 获取性别
	 *
	 * @return Gender 性别
	 */
	public Integer getGender() {
		return this.gender;
	}

	/**
	 * 获取状态
	 *
	 * @return Status 状态
	 */
	public Integer getStatus() {
		return this.status;
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
	 * 设置教师id（自增）
	 *
	 * @param Tid
	 *            教师id（自增）
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置用户id
	 *
	 * @param Uid
	 *            用户id
	 */
	public void setUid(Long uid) {
		this.uid = uid;
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
	 * 设置教师姓名
	 *
	 * @param Teacher_name
	 *            教师姓名
	 */
	public void setTeacherName(String teachername) {
		this.teacherName = teachername;
	}

	/**
	 * 设置联系电话
	 *
	 * @param Phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 设置邮箱
	 *
	 * @param Email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 设置用户类型
	 *
	 * @param Id_type
	 *            用户类型
	 */
	public void setIdType(Integer idtype) {
		this.idType = idtype;
	}

	/**
	 * 设置认证方式
	 *
	 * @param Method
	 *            认证方式
	 */
	public void setMethod(Integer method) {
		this.method = method;
	}

	/**
	 * 设置openid
	 *
	 * @param Openid
	 *            openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 设置微信名（不启用）
	 *
	 * @param Wechatname
	 *            微信名（不启用）
	 */
	public void setWechatname(String wechatname) {
		this.wechatname = wechatname;
	}

	/**
	 * 设置微信头像
	 *
	 * @param Wechathead
	 *            微信头像
	 */
	public void setWechathead(String wechathead) {
		this.wechathead = wechathead;
	}

	/**
	 * 设置城市
	 *
	 * @param City
	 *            城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 设置性别
	 *
	 * @param Gender
	 *            性别
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 设置状态
	 *
	 * @param Status
	 *            状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
