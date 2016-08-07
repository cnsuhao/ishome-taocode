package com.mcookies.qxy.common.AlarmRule;
import javax.inject.Named;
 
@Named
/** 报警规则表*/
public class AlarmRulePVO extends AlarmRuleDBO
{
	private String className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	private Integer count;
	
	
}
