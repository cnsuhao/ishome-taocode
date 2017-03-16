package org.isotope.jfp.mpc.weixin.beans.recever;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信企业号标签信息 <br>
 * 参考【微信企业号表】company_weixin
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @deprecated
 */
public class WeiXinCompanyTagReceverBean extends UserReceverBean {
	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 标签ID
	 */
	private String tagId;

	/**
	 * 微信id
	 */
	private String wxId;

	/**
	 * 标签名称
	 */
	private String tagName;

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

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
