package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信标签表 */
@Named
public class WeiXinCompanyTagDBO extends MyDataBaseObjectSupport {
	/**
	 * 数据id
	 */
	private Long pid = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 标签id
	 */
	private Long tid = null;

	/**
	 * 标签名称
	 */
	private String tagName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取数据id
	 *
	 * @return Pid 数据id
	 */
	public Long getPid() {
		return this.pid;
	}

	/**
	 * 获取学校id
	 *
	 * @return Sid 学校id
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取标签id
	 *
	 * @return Tid 标签id
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取标签名称
	 *
	 * @return Tag_name 标签名称
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * 获取是否启用
	 *
	 * @return Is_use 是否启用
	 */
	public Integer getIsUse() {
		return this.isUse;
	}

	/**
	 * 设置数据id
	 *
	 * @param Pid
	 *            数据id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * 设置学校id
	 *
	 * @param Sid
	 *            学校id
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置标签id
	 *
	 * @param Tid
	 *            用户标签id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置标签名称
	 *
	 * @param Tag_name
	 *            标签名称
	 */
	public void setTagName(String tagname) {
		this.tagName = tagname;
	}

	/**
	 * 设置是否启用
	 *
	 * @param Is_use
	 *            是否启用
	 */
	public void setIsUse(Integer isuse) {
		this.isUse = isuse;
	}
}
