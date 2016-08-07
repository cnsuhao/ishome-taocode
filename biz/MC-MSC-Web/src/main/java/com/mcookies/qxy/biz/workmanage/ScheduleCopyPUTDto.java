package com.mcookies.qxy.biz.workmanage;

import java.util.List;

public class ScheduleCopyPUTDto {
	private String token;
	private List<Long> scheduleIds;
	private List<Week> weeks;
	private List<String> days;
	
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

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public class Week {
		private Integer week;
		private String startTime;
		private String endTime;
		
		public Integer getWeek() {
			return week;
		}
		public void setWeek(Integer week) {
			this.week = week;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		
	}
}
