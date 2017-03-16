package org.isotope.jfp.mpc.weixin.beans.recever;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信企业号部门信息 <br>
 * 参考【微信企业号用户组表】company_weixin_group
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @see <WeiXinCompanyGroupDBO>
 */
public class WeiXinCompanyGroupReceverBean extends UserReceverBean {

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 用户组id
	 */
	private String groupId;

	/**
	 * 上级用户组ID
	 */
	private String parentId;

	/**
	 * 用户组编号
	 */
	private String groupNo;

	/**
	 * 用户组名称
	 */
	private String groupName;

	/**
	 * 用户组openId
	 */
	private String openid;

	/**
	 * 微信ID
	 */
	private String wxId;

	/**
	 * 是否启用
	 */
	private String isUse;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
