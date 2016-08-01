package com.mcookies.qxy.common.UParent;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 家长表 */
public class UParentDBO extends MyDataBaseObjectSupport {
	/**
	 * 家长id（自增）
	 */
	private Long parentId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 用户id
	 */
	private Long uid = null;

	/**
	 * 家长姓名
	 */
	private Long parentName = null;

	/**
	 * 家长电话
	 */
	private String phone = null;

	/**
	 * 工作单位
	 */
	private String workUnit = null;

	/**
	 * 职位
	 */
	private String position = null;

	/**
	 * 认证方式
	 */
	private Integer method = null;

	/**
	 * openid
	 */
	private String openid = null;

	/**
	 * 微信名
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
	private String gender = null;

	/**
	 * 状态
	 */
	private Integer status = null;

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
	 * 获取家长id（自增）
	 *
	 * @return Parent_id 家长id（自增）
	 */
	public Long getParentId() {
		return this.parentId;
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
	 * 获取用户id
	 *
	 * @return Uid 用户id
	 */
	public Long getUid() {
		return this.uid;
	}

	/**
	 * 获取家长姓名
	 *
	 * @return Parent_name 家长姓名
	 */
	public Long getParentName() {
		return this.parentName;
	}

	/**
	 * 获取家长电话
	 *
	 * @return Phone 家长电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 获取工作单位
	 *
	 * @return Work_unit 工作单位
	 */
	public String getWorkUnit() {
		return this.workUnit;
	}

	/**
	 * 获取职位
	 *
	 * @return Position 职位
	 */
	public String getPosition() {
		return this.position;
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
	 * 获取微信名
	 *
	 * @return Wechatname 微信名
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
	public String getGender() {
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
	 * 设置家长id（自增）
	 *
	 * @param Parent_id
	 *            家长id（自增）
	 */
	public void setParentId(Long parentid) {
		this.parentId = parentid;
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
	 * 设置用户id
	 *
	 * @param Uid
	 *            用户id
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 设置家长姓名
	 *
	 * @param Parent_name
	 *            家长姓名
	 */
	public void setParentName(Long parentname) {
		this.parentName = parentname;
	}

	/**
	 * 设置家长电话
	 *
	 * @param Phone
	 *            家长电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 设置工作单位
	 *
	 * @param Work_unit
	 *            工作单位
	 */
	public void setWorkUnit(String workunit) {
		this.workUnit = workunit;
	}

	/**
	 * 设置职位
	 *
	 * @param Position
	 *            职位
	 */
	public void setPosition(String position) {
		this.position = position;
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
	 * 设置微信名
	 *
	 * @param Wechatname
	 *            微信名
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
	public void setGender(String gender) {
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
