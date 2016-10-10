package com.mcookies.qxy.common.OaExamineInformation;

import java.util.List;

import javax.inject.Named;

import com.mcookies.qxy.common.OaExamineResult.OaExamineResultPVO;

@Named
/** OA审批信息表 */
public class OaExamineInformationPVO extends OaExamineInformationDBO {
	private String oatagsName;
	private Integer oaruleStatus;
	private Long id;	// 审批id
	private List<OaExamineResultPVO> resultinfo;
	private String teacherName;  //教师姓名
	private String content;        //审批内容
	private String applyContent;  //申请内容

	
	
	
	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOatagsName() {
		return oatagsName;
	}

	public void setOatagsName(String oatagsName) {
		this.oatagsName = oatagsName;
	}

	public Integer getOaruleStatus() {
		return oaruleStatus;
	}

	public void setOaruleStatus(Integer oaruleStatus) {
		this.oaruleStatus = oaruleStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OaExamineResultPVO> getResultinfo() {
		return resultinfo;
	}

	public void setResultinfo(List<OaExamineResultPVO> resultinfo) {
		this.resultinfo = resultinfo;
	}

}
