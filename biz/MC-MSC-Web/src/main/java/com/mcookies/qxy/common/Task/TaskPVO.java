package com.mcookies.qxy.common.Task;
import javax.inject.Named;
 
@Named
/** 作业表*/
public class TaskPVO extends TaskDBO
{
	public String authorName;  //作者名
	
	public String courseName;  //学科名
		
	public String taskClasserName; //作业发布的班级名
	
	
	
	
	
	
	
	

	public String getTaskClasserName() {
		return taskClasserName;
	}

	public void setTaskClasserName(String taskClasserName) {
		this.taskClasserName = taskClasserName;
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

