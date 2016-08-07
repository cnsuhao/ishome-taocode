package org.isotope.jfp.framework.beans.user;

public class UserBean extends LoginerBean {
	/**
	 * 登陆结果<br>
	 * 0:成功 1:密码错误 2:用户不存在 3:二次登录8:用户类型未知 9:用户异常锁定
	 */
	String loginStatus;

	/**
	 * 登录用户Id
	 */
	String userId;
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
	 * 1:教师,2:家长,3:学生
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

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
