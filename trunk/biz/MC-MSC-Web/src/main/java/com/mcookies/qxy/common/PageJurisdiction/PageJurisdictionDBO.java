package com.mcookies.qxy.common.PageJurisdiction;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 页面权限管理表 */
public class PageJurisdictionDBO extends MyDataBaseObjectSupport {
	/**
	 * 页面权限id
	 */
	private Long jurisdictionId = null;

	/**
	 * 权限内容
	 */
	private Long content = null;

	/**
	 * 对应页面
	 */
	private String pageId = null;

	/**
	 * 对应身份
	 */
	private Integer identity = null;

	/**
	 * 获取页面权限id
	 *
	 * @return Jurisdiction_id 页面权限id
	 */
	public Long getJurisdictionId() {
		return this.jurisdictionId;
	}

	/**
	 * 获取权限内容
	 *
	 * @return Content 权限内容
	 */
	public Long getContent() {
		return this.content;
	}

	/**
	 * 获取对应页面
	 *
	 * @return Page_id 对应页面
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * 获取对应身份
	 *
	 * @return Identity 对应身份
	 */
	public Integer getIdentity() {
		return this.identity;
	}

	/**
	 * 设置页面权限id
	 *
	 * @param Jurisdiction_id
	 *            页面权限id
	 */
	public void setJurisdictionId(Long jurisdictionid) {
		this.jurisdictionId = jurisdictionid;
	}

	/**
	 * 设置权限内容
	 *
	 * @param Content
	 *            权限内容
	 */
	public void setContent(Long content) {
		this.content = content;
	}

	/**
	 * 设置对应页面
	 *
	 * @param Page_id
	 *            对应页面
	 */
	public void setPageId(String pageid) {
		this.pageId = pageid;
	}

	/**
	 * 设置对应身份
	 *
	 * @param Identity
	 *            对应身份
	 */
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

}
