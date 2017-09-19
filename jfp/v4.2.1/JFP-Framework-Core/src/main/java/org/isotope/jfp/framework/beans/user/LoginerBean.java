package org.isotope.jfp.framework.beans.user;

/**
 * 用户登录信息
 * 
 * @author Spook
 * @version 2.0.1 2015/07/07
 * @version 1.1.0 2014/12/15
 * @version 0.1.0 2014/05/30
 * @since 0.1.0 2014/5/30
 */

public class LoginerBean extends TokenBean {

	/**
	 * 账户
	 */
	private String account;
	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 安全码
	 */
	private String securityCode;

	/**
	 * 登录来源类别（0APP1网页2手机3微信4支付宝）
	 */
	private String clientType;

	/**
	 * 登录页面访问地址
	 */
	private String loginUrl;

	/**
	 * 回调地址
	 */
	private String callBackUrl;

	/**
	 * 第三方登录ID
	 * 
	 * @return
	 */
	private String openId;

	/**
	 * 登录账户类别(0游客1普通用户2vip用户)
	 */
	private String accountType;

	/**
	 * 登陆结果<br>
	 * 0:成功 1:密码错误 2:用户不存在 3:二次登录8:用户类型未知 9:用户异常锁定
	 */
	private String loginStatus;

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

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

}
