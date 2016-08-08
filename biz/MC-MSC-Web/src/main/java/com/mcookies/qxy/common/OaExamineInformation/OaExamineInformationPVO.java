package com.mcookies.qxy.common.OaExamineInformation;

import java.util.List;

import javax.inject.Named;

import com.mcookies.qxy.common.OaExamineResult.OaExamineResultPVO;

@Named
/** OA审批信息表 */
public class OaExamineInformationPVO extends OaExamineInformationDBO {
	private String oatagsName;
	private String oaruleStatus;
	private Long id;	// 审批id
	private List<OaExamineResultPVO> resultinfo;

	public String getOatagsName() {
		return oatagsName;
	}

	public void setOatagsName(String oatagsName) {
		this.oatagsName = oatagsName;
	}

	public String getOaruleStatus() {
		return oaruleStatus;
	}

	public void setOaruleStatus(String oaruleStatus) {
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
