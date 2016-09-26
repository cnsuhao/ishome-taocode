package org.isotope.jfp.mpc.weixin.beans.recever;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信企业号部门信息 <br>
 * 参考【微信企业号用户组表】company_weixin_group
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyGroupReceverBean extends UserReceverBean {
	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 微信id
	 */
	private String wxId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
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

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
