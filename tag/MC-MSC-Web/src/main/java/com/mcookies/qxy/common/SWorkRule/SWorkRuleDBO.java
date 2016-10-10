package com.mcookies.qxy.common.SWorkRule;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 作息时间规则表 */
public class SWorkRuleDBO extends MyDataBaseObjectSupport {
	/**
	 * 作息时间规则id
	 */
	private Long wruleId = null;

	/**
	 * 作息时间模板id
	 */
	private Long workId = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 作息时间规则名称
	 */
	private String wruleName = null;

	/**
	 * 规则对应时间段
	 */
	private Integer wruleStage = null;

	/**
	 * 开始时间
	 */
	private String startTime = null;

	/**
	 * 结束时间
	 */
	private String endTime = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取作息时间规则id
	 *
	 * @return Wrule_id 作息时间规则id
	 */
	public Long getWruleId() {
		return this.wruleId;
	}

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
	 * 获取作息时间规则名称
	 *
	 * @return Wrule_name 作息时间规则名称
	 */
	public String getWruleName() {
		return this.wruleName;
	}

	/**
	 * 获取规则对应时间段
	 *
	 * @return Wrule_stage 规则对应时间段
	 */
	public Integer getWruleStage() {
		return this.wruleStage;
	}

	/**
	 * 获取开始时间
	 *
	 * @return Start_time 开始时间
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * 获取结束时间
	 *
	 * @return End_time 结束时间
	 */
	public String getEndTime() {
		return this.endTime;
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
	 * 设置作息时间规则id
	 *
	 * @param Wrule_id
	 *            作息时间规则id
	 */
	public void setWruleId(Long wruleid) {
		this.wruleId = wruleid;
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
	 * 设置作息时间规则名称
	 *
	 * @param Wrule_name
	 *            作息时间规则名称
	 */
	public void setWruleName(String wrulename) {
		this.wruleName = wrulename;
	}

	/**
	 * 设置规则对应时间段
	 *
	 * @param Wrule_stage
	 *            规则对应时间段
	 */
	public void setWruleStage(Integer wrulestage) {
		this.wruleStage = wrulestage;
	}

	/**
	 * 设置开始时间
	 *
	 * @param Start_time
	 *            开始时间
	 */
	public void setStartTime(String starttime) {
		this.startTime = starttime;
	}

	/**
	 * 设置结束时间
	 *
	 * @param End_time
	 *            结束时间
	 */
	public void setEndTime(String endtime) {
		this.endTime = endtime;
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
