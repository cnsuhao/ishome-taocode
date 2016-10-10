package com.mcookies.qxy.common.Task;
import java.util.TreeSet;

import javax.inject.Named;
 
@Named
/** 作业表*/
public class TaskPVO extends TaskDBO
{
	public String authorName;  //作者名
	
	public String courseName;  //学科名
		
	public TreeSet<String> taskClasserName; //作业发布的班级名
	
	public String cid; //班级id
	

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public TreeSet<String> getTaskClasserName() {
		return taskClasserName;
	}

	public void setTaskClasserName(TreeSet<String> taskClasserName2) {
		this.taskClasserName = taskClasserName2;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
	
	
	
}

