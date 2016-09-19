package org.isotope.jfp.mpc.weixin.beans;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信企业号标签信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinCompanyTagReceverBean extends UserReceverBean {
	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 标签名称
	 */
	private String tagName;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
