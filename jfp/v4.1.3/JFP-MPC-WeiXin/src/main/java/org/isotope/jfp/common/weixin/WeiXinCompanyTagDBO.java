package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信标签表
 * @deprecated
 */
@Named
public class WeiXinCompanyTagDBO extends MyDataBaseObjectSupport {
	/**
	 * 数据id
	 */
	private String pid;

	/**
	 * 学校id
	 */
	private String companyId;

	/**
	 * 标签id
	 */
	private String tid;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 是否启用
	 */
	private String isUse;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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
