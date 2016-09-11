package org.isotope.jfp.mpc.weixin.beans;

import org.isotope.jfp.framework.beans.message.info.CustomerSenderBean;

/**
 * 微信企业号组织信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyGroupSenderBean extends CustomerSenderBean {
	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 组织机构ID
	 */
	private String groupId;
	/**
	 * 组织机构编号
	 */
	private String groupNo;
	/**
	 * 组织机构名称
	 */
	private String groupName;

	/**
	 * 用户组openId
	 */
	private String openid;
	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
