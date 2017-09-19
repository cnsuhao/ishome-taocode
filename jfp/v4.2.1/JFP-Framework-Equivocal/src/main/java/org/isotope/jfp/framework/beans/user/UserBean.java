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
	public UserBean(){
		
	}
	public UserBean(String hdp){
		this.hdp = hdp;
	}
	
	/**
	 * 网吧流水ID
	 * 
	 * @return
	 */
	public String getBarId() {
		return this.getCompanyId();
	}

	public String getBarName() {
		return this.getCompanyNameCN();
	}

	/////////////////////////////////////////////////////////////////////////////

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户姓名
	 */
	private String userNameCN;
	/**
	 * 用户电话
	 */
	private String userPhone;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 企业全称
	 */
	private String companyNameCN;
	/**
	 * 企业电话
	 */
	private String companyPhone;

	/**
	 * 企业图标
	 */
	private String companyLogo;

	/**
	 * 登录用户标识(0游客1基本用户9超级管理员)
	 */
	private String roolType;

	/**
	 * 平台标识
	 */

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getRoolType() {
		return roolType;
	}

	public void setRoolType(String roolType) {
		this.roolType = roolType;
	}

	public String getHdp() {
		return hdp;
	}

	public void setHdp(String hdp) {
		this.hdp = hdp;
	}
	private String hdp;

}
