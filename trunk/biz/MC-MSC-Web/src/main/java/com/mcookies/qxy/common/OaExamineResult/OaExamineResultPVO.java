package com.mcookies.qxy.common.OaExamineResult;

import javax.inject.Named;

@Named
/** OA审批结果表 */
public class OaExamineResultPVO extends OaExamineResultDBO {
	private String teacherName;

	private Integer auditresult;
	
	private Integer type; //#0-请假；1-活动；2-保修
	
	private String applyContent;  //申请内容
	
	private String createTime; //申请流程时间
	
	private Integer oatagsId;
	
	private String oatagsName;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getOatagsId() {
		return oatagsId;
	}

	public void setOatagsId(Integer oatagsId) {
		this.oatagsId = oatagsId;
	}

	public String getOatagsName() {
		return oatagsName;
	}

	public void setOatagsName(String oatagsName) {
		this.oatagsName = oatagsName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getAuditresult() {
		return auditresult;
	}

	public void setAuditresult(Integer auditresult) {
		this.auditresult = auditresult;
	}

}
