package org.isotope.jfp.persistent.LogLoginer;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 用户登录日志 */
public class LogLoginerDBO extends MyDataBaseObjectSupport {
	/**
	 * 系统数据唯一识别ID（固定主键）
	 */
	private String puk = null;

	/**
	 * 登录账户名
	 */
	private String account = null;

	/**
	 * 用户ID
	 */
	private Long uid = null;

	/**
	 * 用户种类
	 */
	private Integer userType = null;

	/**
	 * 超级管理员
	 */
	private Integer isAdmin = null;

	/**
	 * MAC地址
	 */
	private String macAdress = null;

	/**
	 * IP地址
	 */
	private String ipAdress = null;

	/**
	 * 客户端种类
	 */
	private Integer clientType = null;

	/**
	 * 学校ID
	 */
	private Long sid = null;

	/**
	 * 产品ID
	 */
	private String goodId = null;

	/**
	 * 动作类别
	 */
	private Integer actType = null;

	/**
	 * 获取系统数据唯一识别ID（固定主键）
	 *
	 * @return Puk 系统数据唯一识别ID（固定主键）
	 */
	public String getPuk() {
		return this.puk;
	}

	/**
	 * 获取登录账户名
	 *
	 * @return Account 登录账户名
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * 获取用户ID
	 *
	 * @return Uid 用户ID
	 */
	public Long getUid() {
		return this.uid;
	}

	/**
	 * 获取用户种类
	 *
	 * @return User_type 用户种类
	 */
	public Integer getUserType() {
		return this.userType;
	}

	/**
	 * 获取超级管理员
	 *
	 * @return Is_admin 超级管理员
	 */
	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * 获取MAC地址
	 *
	 * @return Mac_adress MAC地址
	 */
	public String getMacAdress() {
		return this.macAdress;
	}

	/**
	 * 获取IP地址
	 *
	 * @return Ip_adress IP地址
	 */
	public String getIpAdress() {
		return this.ipAdress;
	}

	/**
	 * 获取客户端种类
	 *
	 * @return Client_type 客户端种类
	 */
	public Integer getClientType() {
		return this.clientType;
	}

	/**
	 * 获取学校ID
	 *
	 * @return Sid 学校ID
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取产品ID
	 *
	 * @return Good_id 产品ID
	 */
	public String getGoodId() {
		return this.goodId;
	}

	/**
	 * 获取动作类别
	 *
	 * @return Act_type 动作类别
	 */
	public Integer getActType() {
		return this.actType;
	}

	/**
	 * 设置系统数据唯一识别ID（固定主键）
	 *
	 * @param Puk
	 *            系统数据唯一识别ID（固定主键）
	 */
	public void setPuk(String puk) {
		this.puk = puk;
	}

	/**
	 * 设置登录账户名
	 *
	 * @param Account
	 *            登录账户名
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 设置用户ID
	 *
	 * @param Uid
	 *            用户ID
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 设置用户种类
	 *
	 * @param User_type
	 *            用户种类
	 */
	public void setUserType(Integer usertype) {
		this.userType = usertype;
	}

	/**
	 * 设置超级管理员
	 *
	 * @param Is_admin
	 *            超级管理员
	 */
	public void setIsAdmin(Integer isadmin) {
		this.isAdmin = isadmin;
	}

	/**
	 * 设置MAC地址
	 *
	 * @param Mac_adress
	 *            MAC地址
	 */
	public void setMacAdress(String macadress) {
		this.macAdress = macadress;
	}

	/**
	 * 设置IP地址
	 *
	 * @param Ip_adress
	 *            IP地址
	 */
	public void setIpAdress(String ipadress) {
		this.ipAdress = ipadress;
	}

	/**
	 * 设置客户端种类
	 *
	 * @param Client_type
	 *            客户端种类
	 */
	public void setClientType(Integer clienttype) {
		this.clientType = clienttype;
	}

	/**
	 * 设置学校ID
	 *
	 * @param Sid
	 *            学校ID
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置产品ID
	 *
	 * @param Good_id
	 *            产品ID
	 */
	public void setGoodId(String goodid) {
		this.goodId = goodid;
	}

	/**
	 * 设置动作类别
	 *
	 * @param Act_type
	 *            动作类别
	 */
	public void setActType(Integer acttype) {
		this.actType = acttype;
	}
}
