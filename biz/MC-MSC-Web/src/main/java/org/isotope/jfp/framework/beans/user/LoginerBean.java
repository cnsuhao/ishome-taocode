package org.isotope.jfp.framework.beans.user;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
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
public class LoginerBean extends FrameworkDataBean {
	// public static LoginerBean buildLoginerBean(String token) {
	// return UserTokenHelper.getLoginerBean(token);
	// }

	/**
	 * 登录时间
	 */
	String loginTime = DateHelper.currentTimeMillisCN1();

	/**
	 * 产品内部交互安全码
	 */
	String securityCode;

	/**
	 * 登录页面访问地址
	 */
	String loginUrl;
	/**
	 * 回调地址
	 */
	String callBackUrl;

	/**
	 * 用户权限
	 */
	String token;
	/**
	 * 登录用户Id
	 */
	String userId;
	/**
	 * 密码
	 */
	String passWord;
	/**
	 * 企业ID
	 */
	String companyId;
	/**
	 * 产品ID
	 */
	String productId;
	/**
	 * 用户分类,用于区分数据来源<br>
	 * 1:企业用户,2:个人用户
	 */
	String userType;

	/**
	 * 激活状态<br>
	 * 0激活1未激活
	 */
	String activeType;
	/**
	 * 用户类型<br>
	 * 0启用1停用
	 */
	String useType;

	/**
	 * 用户姓名
	 */
	String userNameCN;
	/**
	 * 用户电话
	 */
	String userPhone;
	/**
	 * 企业全称
	 */
	String companyNameCN;
	/**
	 * 企业电话
	 */
	String companyPhone;
	/**
	 * 企业电话
	 */
	String companyLogo;
	/**
	 * 是否管理员（0超级管理员1普通用户）
	 */
	String isAdmin;

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUserNameCN() {
		return userNameCN;
	}

	public void setUserNameCN(String userNameCN) {
		this.userNameCN = userNameCN;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getCompanyNameCN() {
		return companyNameCN;
	}

	public void setCompanyNameCN(String companyNameCN) {
		this.companyNameCN = companyNameCN;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
