package com.mcookies.qxy.common.User;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 用户表 */
public class UserDBO extends MyDataBaseObjectSupport {
	/**
	 * 用户id（自增）
	 */
	private Long uid = null;

	/**
	 * 登录账号
	 */
	private String account = null;

	/**
	 * 登录邮箱
	 */
	private String email = null;

	/**
	 * 邮箱是否验证
	 */
	private Integer emailStatus = null;

	/**
	 * 手机号
	 */
	private String phone = null;

	/**
	 * 密码
	 */
	private String password = null;

	/**
	 * 状态（不启用）
	 */
	private Integer status = null;

	/**
	 * 获取用户id（自增）
	 *
	 * @return Uid 用户id（自增）
	 */
	public Long getUid() {
		return this.uid;
	}

	/**
	 * 获取登录账号
	 *
	 * @return Account 登录账号
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * 获取登录邮箱
	 *
	 * @return Email 登录邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 获取邮箱是否验证
	 *
	 * @return Email_status 邮箱是否验证
	 */
	public Integer getEmailStatus() {
		return this.emailStatus;
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
	 * 获取密码
	 *
	 * @return Password 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 获取状态（不启用）
	 *
	 * @return Status 状态（不启用）
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置用户id（自增）
	 *
	 * @param Uid
	 *            用户id（自增）
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 设置登录账号
	 *
	 * @param Account
	 *            登录账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 设置登录邮箱
	 *
	 * @param Email
	 *            登录邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 设置邮箱是否验证
	 *
	 * @param Email_status
	 *            邮箱是否验证
	 */
	public void setEmailStatus(Integer emailstatus) {
		this.emailStatus = emailstatus;
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
	 * 设置密码
	 *
	 * @param Password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置状态（不启用）
	 *
	 * @param Status
	 *            状态（不启用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
