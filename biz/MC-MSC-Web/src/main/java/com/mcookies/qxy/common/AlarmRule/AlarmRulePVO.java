package com.mcookies.qxy.common.AlarmRule;
import javax.inject.Named;
 
@Named
/** 报警规则表*/
public class AlarmRulePVO extends AlarmRuleDBO
{
	private String className =null;
	private Integer classalarmcount=null;
	private String termName = null;
	private String gradeName = null;
	private Integer cid = null;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getClassalarmcount() {
		return classalarmcount;
	}
	public void setClassalarmcount(Integer classalarmcount) {
		this.classalarmcount = classalarmcount;
	}
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
