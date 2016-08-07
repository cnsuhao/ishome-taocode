package org.isotope.jfp.framework.beans.user;

import javax.inject.Named;

import org.isotope.jfp.framework.utils.DateHelper;

/**
 * 系统用户登录信息
 * 
 * @author Spook
 * @version 2.0.1 2015/7/7
 * @version 1.1.0 2014/12/15
 * @version 0.1.0 2014/5/30
 * @since 0.1.0 2014/5/30
 */
@Named
public class LoginerBean extends TokenBean {

	/**
	 * 密码
	 */
	String account;
	/**
	 * 密码
	 */
	String passWord;
	/**
	 * 用户分类,用于区分数据来源<br>
	 * 1:教师,2:家长,3:学生
	 */
	String userType;

	/**
	 * 登录时间
	 */
	String loginTime = DateHelper.currentTimeMillisCN1();

	/**
	 * 登录页面访问地址
	 */
	String loginUrl;
	/**
	 * 登录页面访问地址
	 */
	String loginSource;

	/**
	 * 产品内部交互安全码
	 */
	String securityCode;

	/**
	 * 回调地址
	 */
	String callBackUrl;

	/**
	 * 第三方登录ID
	 * 
	 * @return
	 */
	String openId;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
