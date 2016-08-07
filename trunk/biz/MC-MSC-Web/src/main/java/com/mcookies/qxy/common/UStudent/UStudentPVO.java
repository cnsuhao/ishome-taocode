package com.mcookies.qxy.common.UStudent;

import javax.inject.Named;

@Named
/** 学生表 */
public class UStudentPVO extends UStudentDBO {
	private String termName = null;
	private String gradeName = null;
	private String className = null;

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
