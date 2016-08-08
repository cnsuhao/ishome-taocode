package com.mcookies.qxy.common.OaExamineResult;

import javax.inject.Named;

@Named
/** OA审批结果表 */
public class OaExamineResultPVO extends OaExamineResultDBO {
	private String teacherName;

	private Integer auditresult;

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
