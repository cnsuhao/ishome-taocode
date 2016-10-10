package com.mcookies.qxy.common.ClassCourse;
import javax.inject.Named;
 
@Named
/** 班级课程教师关联表*/
public class ClassCoursePVO extends ClassCourseDBO
{
	/**
	 * 是否为班主任
	 */
	private Integer isLeader = null;

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}
	
}
