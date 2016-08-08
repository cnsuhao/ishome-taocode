package org.isotope.jfp.framework.beans.user;

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
	String schoolNameCN;
	/**
	 * 企业电话
	 */
	String schoolPhone;
	/**
	 * 企业电话
	 */
	String schoolLogo;
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

	public String getSchoolNameCN() {
		return schoolNameCN;
	}

	public void setSchoolNameCN(String schoolNameCN) {
		this.schoolNameCN = schoolNameCN;
	}

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getSchoolLogo() {
		return schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
