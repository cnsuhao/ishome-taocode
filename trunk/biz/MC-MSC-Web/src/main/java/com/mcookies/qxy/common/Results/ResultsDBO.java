package com.mcookies.qxy.common.Results;

import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 成绩表*/
public class ResultsDBO extends MyDataBaseObjectSupport
{
    /** 
     * 成绩id
     */
    private Long resultsId = null;
 
    /** 
     * 成绩标签id
     */
    private Long resultsTagId = null;
 
    /** 
     * 课程id
     */
    private Long courseId = null;
 
    /** 
     * 班级id
     */
    private Long cid = null;
 
    /** 
     * 学生id
     */
    private Long studentId = null;
 
    /** 
     * 学生姓名
     */
    private String studentName = null;
 
    /** 
     * 分数
     */
    private String score = null;
 
    /** 
     * 考试时间
     */
    private String testTime = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 

 
    /** 
     * 获取成绩id
     *
     * @return Results_id 成绩id
     */
    public Long getResultsId() {
        return this.resultsId;
    }
 
    /** 
     * 获取成绩标签id
     *
     * @return Results_tag_id 成绩标签id
     */
    public Long getResultsTagId() {
        return this.resultsTagId;
    }
 
    /** 
     * 获取课程id
     *
     * @return Course_id 课程id
     */
    public Long getCourseId() {
        return this.courseId;
    }
 
    /** 
     * 获取班级id
     *
     * @return Cid 班级id
     */
    public Long getCid() {
        return this.cid;
    }
 
    /** 
     * 获取学生id
     *
     * @return Student_id 学生id
     */
    public Long getStudentId() {
        return this.studentId;
    }
 
    /** 
     * 获取学生姓名
     *
     * @return Student_name 学生姓名
     */
    public String getStudentName() {
        return this.studentName;
    }
 
    /** 
     * 获取分数
     *
     * @return Score 分数
     */
    public String getScore() {
        return this.score;
    }
 
    /** 
     * 获取考试时间
     *
     * @return Test_time 考试时间
     */
    public String getTestTime() {
        return this.testTime;
    }
 
    /** 
     * 获取是否启用
     *
     * @return Is_use 是否启用
     */
    public Integer getIsUse() {
        return this.isUse;
    }
  
    /** 
     * 设置成绩id
     *
     * @param Results_id 成绩id
     */
    public void setResultsId(Long resultsid) {
        this.resultsId = resultsid;
    }
 
    /** 
     * 设置成绩标签id
     *
     * @param Results_tag_id 成绩标签id
     */
    public void setResultsTagId(Long resultstagid) {
        this.resultsTagId = resultstagid;
    }
 
    /** 
     * 设置课程id
     *
     * @param Course_id 课程id
     */
    public void setCourseId(Long courseid) {
        this.courseId = courseid;
    }
 
    /** 
     * 设置班级id
     *
     * @param Cid 班级id
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }
 
    /** 
     * 设置学生id
     *
     * @param Student_id 学生id
     */
    public void setStudentId(Long studentid) {
        this.studentId = studentid;
    }
 
    /** 
     * 设置学生姓名
     *
     * @param Student_name 学生姓名
     */
    public void setStudentName(String studentname) {
        this.studentName = studentname;
    }
 
    /** 
     * 设置分数
     *
     * @param Score 分数
     */
    public void setScore(String score) {
        this.score = score;
    }
 
    /** 
     * 设置考试时间
     *
     * @param Test_time 考试时间
     */
    public void setTestTime(String testtime) {
        this.testTime = testtime;
    }
 
    /** 
     * 设置是否启用
     *
     * @param Is_use 是否启用
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
 
}
