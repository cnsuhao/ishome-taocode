package com.mcookies.qxy.common.MecScore;

import javax.inject.Named;
 
@Named
/** 德育班级评分表*/
public class MecScorePVO extends MecScoreDBO{
    /** 
     * 班级名称
     */
    private String className = null;

    /** 
     * 德育班级综合评分
     */
    private Integer totalScore = null;
    
	/**
	 * 年级名称
	 */
    
	private String gradeName = null;
	
    /** 
     * 德育班级给合排名
     */
    private String ranking = null;
	
    /** 
     * 德育班级项目名称
     */
    private String mecItemName = null;
    
    /** 
     * 德育班级项目说明
     */
    private String mecItemExplain = null;
    
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
     * 班级id
     */
    private Long cid = null;
    /** 
     * 年级标签id
     */
    private Long gradeId = null;
 
    
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
	public String getMecItemName() {
		return mecItemName;
	}
	public void setMecItemName(String mecItemName) {
		this.mecItemName = mecItemName;
	}
	public String getMecItemExplain() {
		return mecItemExplain;
	}
	public void setMecItemExplain(String mecItemExplain) {
		this.mecItemExplain = mecItemExplain;
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
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}

	
	
}
