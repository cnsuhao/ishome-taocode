package com.mcookies.qxy.common.SMessage;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 短信消息记录表 */
public class SMessageDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 消息类型
	 */
	private Integer type = null;

	/**
	 * 对象类型
	 */
	private Integer object = null;

	/**
	 * 教师id
	 */
	private Long tid = null;

	/**
	 * 教师姓名
	 */
	private String teacherName = null;

	/**
	 * 家长id
	 */
	private Long parentId = null;

	/**
	 * 家长姓名
	 */
	private String parentName = null;

	/**
	 * 手机号
	 */
	private String phone = null;

	/**
	 * 发送时间
	 */
	private String sendingTime = null;

	/**
	 * 短信内容
	 */
	private String message = null;

	/**
	 * 是否需要回执
	 */
	private Integer isreceipt = null;

	/**
	 * 状态
	 */
	private Integer status = null;

	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 获取消息类型
	 *
	 * @return Type 消息类型
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 获取对象类型
	 *
	 * @return Object 对象类型
	 */
	public Integer getObject() {
		return this.object;
	}

	/**
	 * 获取教师id
	 *
	 * @return Tid 教师id
	 */
	public Long getTid() {
		return this.tid;
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
	 * 获取家长id
	 *
	 * @return Parent_id 家长id
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**
	 * 获取家长姓名
	 *
	 * @return Parent_name 家长姓名
	 */
	public String getParentName() {
		return this.parentName;
	}

	/**
	 * 获取手机号
	 *
	 * @return Phone 手机号
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 获取发送时间
	 *
	 * @return Sending_time 发送时间
	 */
	public String getSendingTime() {
		return this.sendingTime;
	}

	/**
	 * 获取短信内容
	 *
	 * @return Message 短信内容
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 获取是否需要回执
	 *
	 * @return IsReceipt 是否需要回执
	 */
	public Integer getIsreceipt() {
		return this.isreceipt;
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
	 * 设置自增id
	 *
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置消息类型
	 *
	 * @param Type
	 *            消息类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置对象类型
	 *
	 * @param Object
	 *            对象类型
	 */
	public void setObject(Integer object) {
		this.object = object;
	}

	/**
	 * 设置教师id
	 *
	 * @param Tid
	 *            教师id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
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
	 * 设置家长id
	 *
	 * @param Parent_id
	 *            家长id
	 */
	public void setParentId(Long parentid) {
		this.parentId = parentid;
	}

	/**
	 * 设置家长姓名
	 *
	 * @param Parent_name
	 *            家长姓名
	 */
	public void setParentName(String parentname) {
		this.parentName = parentname;
	}

	/**
	 * 设置手机号
	 *
	 * @param Phone
	 *            手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 设置发送时间
	 *
	 * @param Sending_time
	 *            发送时间
	 */
	public void setSendingTime(String sendingtime) {
		this.sendingTime = sendingtime;
	}

	/**
	 * 设置短信内容
	 *
	 * @param Message
	 *            短信内容
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 设置是否需要回执
	 *
	 * @param IsReceipt
	 *            是否需要回执
	 */
	public void setIsreceipt(Integer isreceipt) {
		this.isreceipt = isreceipt;
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

}
