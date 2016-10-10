package com.mcookies.qxy.common.STerm;

import java.util.TreeSet;

import javax.inject.Named;

@Named
/** 学期设置表 */
public class STermPVO extends STermDBO {
	/**
	 * 关联教师id
	 */
	private Long tid;
	/**
	 * 值周列表
	 */
	private TreeSet<Integer> weeklist;
	/**
	 * 值日列表
	 */
	private TreeSet<String> days;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public TreeSet<Integer> getWeeklist() {
		return weeklist;
	}

	public void setWeeklist(TreeSet<Integer> weeklist) {
		this.weeklist = weeklist;
	}

	public TreeSet<String> getDays() {
		return days;
	}

	public void setDays(TreeSet<String> days) {
		this.days = days;
	}

}
