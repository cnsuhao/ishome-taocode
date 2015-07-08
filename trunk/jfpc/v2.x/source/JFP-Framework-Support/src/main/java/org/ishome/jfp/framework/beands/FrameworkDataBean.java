package org.ishome.jfp.framework.beands;

import javax.inject.Named;

/**
 * 基底共通
 * 
 * @author 		Spook        
 * @version 	2.0.1 	2015/7/7
 * @version 0.1.0 2014/2/8
 * @since 0.1.0 2014/2/8
 */
@Named
public class FrameworkDataBean extends ObjectBean {

	/**
	 * 备注说明
	 */
	private String meno;

	/**
	 * 备用1
	 */
	private String fb1;

	/**
	 * 备用2
	 */
	private String fb2;

	/**
	 * 备用3
	 */
	private String fb3;

	/**
	 * 备用4
	 */
	private String fb4;

	/**
	 * 备用5
	 */
	private String fb5;

	/**
	 * 扩展1
	 */
	private String eb1;

	/**
	 * 扩展2
	 */
	private String eb2;

	/**
	 * 扩展3
	 */
	private String eb3;

	/**
	 * 扩展4
	 */
	private String eb4;

	/**
	 * 扩展5
	 */
	private String eb5;

	/**
	 * 所在组织
	 */
	private String projectId;

	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 有效标识
	 */
	private String delFlag;

	/**
	 * 创建时间
	 */
	private String creatTime;

	/**
	 * 创建者
	 */
	private String creatUser;

	/**
	 * 最后更新时间
	 */
	private String updateTime;

	/**
	 * 最后更新者
	 */
	private String updateUser;

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getFb1() {
		return fb1;
	}

	public void setFb1(String fb1) {
		this.fb1 = fb1;
	}

	public String getFb2() {
		return fb2;
	}

	public void setFb2(String fb2) {
		this.fb2 = fb2;
	}

	public String getFb3() {
		return fb3;
	}

	public void setFb3(String fb3) {
		this.fb3 = fb3;
	}

	public String getFb4() {
		return fb4;
	}

	public void setFb4(String fb4) {
		this.fb4 = fb4;
	}

	public String getFb5() {
		return fb5;
	}

	public void setFb5(String fb5) {
		this.fb5 = fb5;
	}

	public String getEb1() {
		return eb1;
	}

	public void setEb1(String eb1) {
		this.eb1 = eb1;
	}

	public String getEb2() {
		return eb2;
	}

	public void setEb2(String eb2) {
		this.eb2 = eb2;
	}

	public String getEb3() {
		return eb3;
	}

	public void setEb3(String eb3) {
		this.eb3 = eb3;
	}

	public String getEb4() {
		return eb4;
	}

	public void setEb4(String eb4) {
		this.eb4 = eb4;
	}

	public String getEb5() {
		return eb5;
	}

	public void setEb5(String eb5) {
		this.eb5 = eb5;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
