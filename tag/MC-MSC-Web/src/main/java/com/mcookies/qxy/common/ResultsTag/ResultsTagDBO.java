package com.mcookies.qxy.common.ResultsTag;

import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 成绩标签表*/
public class ResultsTagDBO extends MyDataBaseObjectSupport
{
    /** 
     * 成绩标签id
     */
    private Long resultsTagId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 学期id
     */
    private Long termId = null;
 
    /** 
     * 成绩标签名称
     */
    private String resultsTagName = null;
 
    /** 
     * 成绩标签说明
     */
    private String resultsTagExplain = null;
 
    /** 
     * 考试开始时间
     */
    private String startTime = null;
 
    /** 
     * 考试结束时间
     */
    private String endTime = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
 
    /** 
     * 获取成绩标签id
     *
     * @return Results_tag_id 成绩标签id
     */
    public Long getResultsTagId() {
        return this.resultsTagId;
    }
 
    /** 
     * 获取学校id
     *
     * @return Sid 学校id
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * 获取学期id
     *
     * @return Term_id 学期id
     */
    public Long getTermId() {
        return this.termId;
    }
 
    /** 
     * 获取成绩标签名称
     *
     * @return Results_tag_name 成绩标签名称
     */
    public String getResultsTagName() {
        return this.resultsTagName;
    }
 
    /** 
     * 获取成绩标签说明
     *
     * @return Results_tag_explain 成绩标签说明
     */
    public String getResultsTagExplain() {
        return this.resultsTagExplain;
    }
 
    /** 
     * 获取考试开始时间
     *
     * @return Start_time 考试开始时间
     */
    public String getStartTime() {
        return this.startTime;
    }
 
    /** 
     * 获取考试结束时间
     *
     * @return End_time 考试结束时间
     */
    public String getEndTime() {
        return this.endTime;
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
     * 设置成绩标签id
     *
     * @param Results_tag_id 成绩标签id
     */
    public void setResultsTagId(Long resultstagid) {
        this.resultsTagId = resultstagid;
    }
 
    /** 
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置学期id
     *
     * @param Term_id 学期id
     */
    public void setTermId(Long termid) {
        this.termId = termid;
    }
 
    /** 
     * 设置成绩标签名称
     *
     * @param Results_tag_name 成绩标签名称
     */
    public void setResultsTagName(String resultstagname) {
        this.resultsTagName = resultstagname;
    }
 
    /** 
     * 设置成绩标签说明
     *
     * @param Results_tag_explain 成绩标签说明
     */
    public void setResultsTagExplain(String resultstagexplain) {
        this.resultsTagExplain = resultstagexplain;
    }
 
    /** 
     * 设置考试开始时间
     *
     * @param Start_time 考试开始时间
     */
    public void setStartTime(String starttime) {
        this.startTime = starttime;
    }
 
    /** 
     * 设置考试结束时间
     *
     * @param End_time 考试结束时间
     */
    public void setEndTime(String endtime) {
        this.endTime = endtime;
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
