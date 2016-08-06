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
	
	/**
	 * 教师id
	 */
	private Long tid = null;
	
	
	/**
	 * 教师姓名
	 */
	private String teacherName = null;
	
	
	private Integer count = null;


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


	public Long getTid() {
		return tid;
	}


	public void setTid(Long tid) {
		this.tid = tid;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}

}
