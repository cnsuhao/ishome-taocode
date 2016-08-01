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
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private Long creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private Long updator = null;

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
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public Long getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public Long getUpdator() {
		return this.updator;
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

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(Long updator) {
		this.updator = updator;
	}

}
