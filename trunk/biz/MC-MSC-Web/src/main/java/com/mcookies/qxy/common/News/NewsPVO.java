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

}
