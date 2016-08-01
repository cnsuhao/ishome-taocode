package com.mcookies.qxy.common.OaExamineResult;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** OA审批结果表 */
public class OaExamineResultDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 审核人id
	 */
	private Long tid = null;

	/**
	 * 审核信息id
	 */
	private Long approvalInformationId = null;

	/**
	 * 规则id
	 */
	private Long oaruleId = null;

	/**
	 * 规则状态
	 */
	private Integer oaruleStatus = null;

	/**
	 * 审核结果
	 */
	private Integer result = null;

	/**
	 * 审核时间
	 */
	private String time = null;

	/**
	 * 审核意见
	 */
	private String content = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

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
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
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
	 * 获取审核人id
	 *
	 * @return Tid 审核人id
	 */
	public Long getTid() {
		return this.tid;
	}

	/**
	 * 获取审核信息id
	 *
	 * @return Approval_information_id 审核信息id
	 */
	public Long getApprovalInformationId() {
		return this.approvalInformationId;
	}

	/**
	 * 获取规则id
	 *
	 * @return Oarule_id 规则id
	 */
	public Long getOaruleId() {
		return this.oaruleId;
	}

	/**
	 * 获取规则状态
	 *
	 * @return Oarule_status 规则状态
	 */
	public Integer getOaruleStatus() {
		return this.oaruleStatus;
	}

	/**
	 * 获取审核结果
	 *
	 * @return Result 审核结果
	 */
	public Integer getResult() {
		return this.result;
	}

	/**
	 * 获取审核时间
	 *
	 * @return Time 审核时间
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * 获取审核意见
	 *
	 * @return Content 审核意见
	 */
	public String getContent() {
		return this.content;
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
	 * 设置自增id
	 *
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 设置审核人id
	 *
	 * @param Tid
	 *            审核人id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}

	/**
	 * 设置审核信息id
	 *
	 * @param Approval_information_id
	 *            审核信息id
	 */
	public void setApprovalInformationId(Long approvalinformationid) {
		this.approvalInformationId = approvalinformationid;
	}

	/**
	 * 设置规则id
	 *
	 * @param Oarule_id
	 *            规则id
	 */
	public void setOaruleId(Long oaruleid) {
		this.oaruleId = oaruleid;
	}

	/**
	 * 设置规则状态
	 *
	 * @param Oarule_status
	 *            规则状态
	 */
	public void setOaruleStatus(Integer oarulestatus) {
		this.oaruleStatus = oarulestatus;
	}

	/**
	 * 设置审核结果
	 *
	 * @param Result
	 *            审核结果
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * 设置审核时间
	 *
	 * @param Time
	 *            审核时间
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 设置审核意见
	 *
	 * @param Content
	 *            审核意见
	 */
	public void setContent(String content) {
		this.content = content;
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
