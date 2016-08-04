package com.mcookies.qxy.common.SWorkTime;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 作息时间模板 */
public class SWorkTimeDBO extends MyDataBaseObjectSupport {
	/**
	 * 作息时间模板id
	 */
	private Long workId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 作息时间模板名称
	 */
	private String workName = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取作息时间模板id
	 *
	 * @return Work_id 作息时间模板id
	 */
	public Long getWorkId() {
		return this.workId;
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
	 * 获取作息时间模板名称
	 *
	 * @return Work_name 作息时间模板名称
	 */
	public String getWorkName() {
		return this.workName;
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
	 * 设置作息时间模板id
	 *
	 * @param Work_id
	 *            作息时间模板id
	 */
	public void setWorkId(Long workid) {
		this.workId = workid;
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
	 * 设置作息时间模板名称
	 *
	 * @param Work_name
	 *            作息时间模板名称
	 */
	public void setWorkName(String workname) {
		this.workName = workname;
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
