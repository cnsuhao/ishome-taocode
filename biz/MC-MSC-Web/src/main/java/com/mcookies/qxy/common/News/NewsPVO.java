package com.mcookies.qxy.common.News;

import java.util.List;

import javax.inject.Named;

import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;

@Named
/** 新闻表 */
public class NewsPVO extends NewsDBO {
	private String columnName;
	
	private String authorName;

	private List<UTeacherDBO> newsReaders;

	private List<ClassDBO> newsClassers;
	
	private String startTime;

	private String endTime;
	
	/**
	 * 可查看的教师tid
	 */
	private Long tid;
	
	/**
	 * 可查看的家长parentId
	 */
	private Long parentId;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<UTeacherDBO> getNewsReaders() {
		return newsReaders;
	}

	public void setNewsReaders(List<UTeacherDBO> newsReaders) {
		this.newsReaders = newsReaders;
	}

	public List<ClassDBO> getNewsClassers() {
		return newsClassers;
	}

	public void setNewsClassers(List<ClassDBO> newsClassers) {
		this.newsClassers = newsClassers;
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

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
