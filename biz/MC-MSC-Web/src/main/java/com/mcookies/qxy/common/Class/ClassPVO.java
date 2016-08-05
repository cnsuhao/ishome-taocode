package com.mcookies.qxy.common.Class;
import javax.inject.Named;
 
@Named
/** 班级表*/
public class ClassPVO extends ClassDBO
{
    
	/**
	 * 学期名称
	 */
	private String termName = null;
	
	/**
	 * 年级名称
	 */
	private String gradeName = null;
	
	
	/**
	 * 作息时间模板名称
	 */
	private String workName = null;
	
	


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


	public String getWorkName() {
		return workName;
	}


	public void setWorkName(String workName) {
		this.workName = workName;
	}

}
