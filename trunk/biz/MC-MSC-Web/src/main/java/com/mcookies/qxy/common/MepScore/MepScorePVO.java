package com.mcookies.qxy.common.MepScore;

import javax.inject.Named;
 
@Named
/** 德育个人评分表*/
public class MepScorePVO extends MepScoreDBO{
	/**
	 * 学号
	 */
	private Long number = null;

	/**
	 * 学生姓名
	 */
	private String studentName = null;
    /** 
     * 德育个人综合评分
     */
    private Integer totalScore = null;
    
    /** 
     * 德育个人给合排名
     */
    private String ranking = null;
    
    /** 
     * 学生id
     */
    private Long studentId = null;
    
    /** 
     * 德育个人项目名称
     */
    private String mepItemName = null;
 
    /** 
     * 德育个人项目说明
     */
    private String mepItemExplain = null;
	/**
	 * 教师姓名
	 */
	private String teacherName = null;
	/**
	 * 开始时间
	 */
	private String startTime = null;
	
	/**
	 * 结束时间
	 */
	private String endTime = null;
    /** 
     * 年级标签id
     */
    private Long gradeId = null;
    

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getMepItemName() {
		return mepItemName;
	}

	public void setMepItemName(String mepItemName) {
		this.mepItemName = mepItemName;
	}

	public String getMepItemExplain() {
		return mepItemExplain;
	}

	public void setMepItemExplain(String mepItemExplain) {
		this.mepItemExplain = mepItemExplain;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
      		
}

















