package org.jfpc.base.bean;

import javax.inject.Named;

import org.jfpc.base.bean.ObjectBean;
import org.jfpc.base.helper.DateHelper;
import org.jfpc.base.helper.TokenHelper;

/**
 * 系统用户登录信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Named
public class LoginerBean extends ObjectBean {
	public static LoginerBean buildLoginerBean(String token) {
		return TokenHelper.getLoginerBean(token);
	}

	/**
	 * 产品内部交互安全码
	 */
	String securityCode = "";
	/**
	 * 验证码
	 */
	String verCode = "";

	/**
	 * 登录页面访问地址
	 */
	String loginUrl = "";
	/**
	 * 回调地址
	 */
	String callBackUrl = "";

	/**
	 * 用户权限
	 */
	String token = "";
	/**
	 * 登录帐号(登录用户PUK)
	 */
	String userName = "";
	/**
	 * 密码
	 */
	String passWord = "";
	/**
	 * 企业ID
	 */
	String companyId = "";
	/**
	 * 产品ID
	 */
	String productId = "";
	/**
	 * 用户分类,用于区分数据来源<br>
	 * 1:企业用户,2:个人用户
	 */
	String userType = "";

	/**
	 * 激活状态<br>
	 * 0激活1未激活
	 */
	String activeType = "";
	/**
	 * 用户类型<br>
	 * 0启用1停用
	 */
	String useType = "";

	/**
	 * 登录时间
	 */
	String loginTime = DateHelper.currentTimeMillisCN1();

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

}
