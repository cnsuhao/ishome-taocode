package com.mcookies.qxy.biz.workmanage;

import java.sql.Date;
import java.util.List;

public class ScheduleCopyPUTDto {
	private String token;
	private List<Long> scheduleIds;
	private List<Week> weeks;
	private List<Date> days;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Long> getScheduleIds() {
		return scheduleIds;
	}

	public void setScheduleIds(List<Long> scheduleIds) {
		this.scheduleIds = scheduleIds;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}


	public List<Date> getDays() {
		return days;
	}

	public void setDays(List<Date> days) {
		this.days = days;
	}


	public class Week {
		private Integer week;
		private Date startTime;
		private Date endTime;
		
		public Integer getWeek() {
			return week;
		}
		public void setWeek(Integer week) {
			this.week = week;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
	}
}
