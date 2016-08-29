package org.isotope.jfp.framework.beans.user;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.common.BusinessTokenBean;

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
public class LoginerBean extends BusinessTokenBean {

	/**
	 * 密码
	 */
	String account;
	/**
	 * 密码
	 */
	String passWord;

	/**
	 * 安全码
	 */
	String securityCode;

	/**
	 * 登录来源类别（0APP1网页2手机3微信4支付宝）
	 */
	Integer clientType;

	/**
	 * IP地址
	 */
	String ipAdress;

	/**
	 * 登录页面访问地址
	 */
	String loginUrl;
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

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
