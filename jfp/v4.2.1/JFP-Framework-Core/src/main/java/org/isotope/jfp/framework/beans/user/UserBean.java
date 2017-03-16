package org.isotope.jfp.framework.beans.user;

/**
 * 系统用户信息
 * 
 * @author Spook
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 */

public class UserBean extends LoginerBean {
	/**
	 * 登陆结果<br>
	 * 0:成功 1:密码错误 2:用户不存在 3:二次登录8:用户类型未知 9:用户异常锁定
	 */
	String loginStatus;

	/**
	 * 产品ID
	 */
	String productId;

	/**
	 * 激活状态<br>
	 * 0激活1未激活
	 */
	String activeType;

	/**
	 * 用户ID
	 */
	String userId;
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
	 * 性别
	 */
	String gender;
	/**
	 * 是否管理员（0超级管理员1普通用户）
	 */
	String isAdmin;

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
