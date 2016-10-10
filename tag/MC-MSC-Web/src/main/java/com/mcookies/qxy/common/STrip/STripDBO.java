package com.mcookies.qxy.common.STrip;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 行程表 */
public class STripDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long tripId = null;

	/**
	 * 学期id
	 */
	private Long termId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 教师id
	 */
	private Long tid = null;

	/**
	 * 内容
	 */
	private String content = null;

	/**
	 * 日期
	 */
	private String date = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取自增id
	 *
	 * @return Trip_id 自增id
	 */
	public Long getTripId() {
		return this.tripId;
	}

	/**
	 * 获取学期id
	 *
	 * @return Term_id 学期id
	 */
	public Long getTermId() {
		return this.termId;
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
	 * 获取教师id
	 *
	 * @return Tid 教师id
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取内容
	 *
	 * @return Content 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 获取日期
	 *
	 * @return Date 日期
	 */
	public String getDate() {
		return this.date;
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
	 * 设置自增id
	 *
	 * @param Trip_id
	 *            自增id
	 */
	public void setTripId(Long tripid) {
		this.tripId = tripid;
	}

	/**
	 * 设置学期id
	 *
	 * @param Term_id
	 *            学期id
	 */
	public void setTermId(Long termid) {
		this.termId = termid;
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
	 * 设置教师id
	 *
	 * @param Tid
	 *            教师id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置内容
	 *
	 * @param Content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置日期
	 *
	 * @param Date
	 *            日期
	 */
	public void setDate(String date) {
		this.date = date;
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
