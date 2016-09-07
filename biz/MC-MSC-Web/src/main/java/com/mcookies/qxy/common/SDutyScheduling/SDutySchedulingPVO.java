package com.mcookies.qxy.common.SDutyScheduling;

import java.util.List;

import javax.inject.Named;

import com.mcookies.qxy.common.UTeacher.UTeacherDBO;

@Named
/** 岗位人员排班表 */
public class SDutySchedulingPVO extends SDutySchedulingDBO {
	/**
	 * 关联教师id
	 */
	private Long tid;
	
	private String dutyName;
	
	private String content;
	
	private List<UTeacherDBO> leaders;
	
	private List<UTeacherDBO> teachers;
	
	private Long scheduleId;
	
	
	
	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public List<UTeacherDBO> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<UTeacherDBO> leaders) {
		this.leaders = leaders;
	}

	public List<UTeacherDBO> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<UTeacherDBO> teachers) {
		this.teachers = teachers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
