package com.mcookies.qxy.common.Class;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 班级表*/
public class ClassDBO extends MyDataBaseObjectSupport
{
    /** 
     * 班级id
     */
    private Long cid = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 班级名称
     */
    private String className = null;
 
    /** 
     * 入学年份
     */
    private String year = null;
 
    /** 
     * 班级编号
     */
    private String cnum = null;
 
    /** 
     * 所属学期id
     */
    private Long termId = null;
 
    /** 
     * 年级标签id
     */
    private Long gradeId = null;
 
    /** 
     * 作息时间模板id
     */
    private Long workId = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
    /** 
     * 创建时间
     */
    private String createTime = null;
 
    /** 
     * 创建者
     */
    private Long creator = null;
 
    /** 
     * 更新时间
     */
    private String updateTime = null;
 
    /** 
     * 最后更新者
     */
    private Long updator = null;
 
    /** 
     * 获取班级id
     *
     * @return Cid 班级id
     */
    public Long getCid() {
        return this.cid;
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
     * 获取班级名称
     *
     * @return Class_name 班级名称
     */
    public String getClassName() {
        return this.className;
    }
 
    /** 
     * 获取入学年份
     *
     * @return Year 入学年份
     */
    public String getYear() {
        return this.year;
    }
 
    /** 
     * 获取班级编号
     *
     * @return Cnum 班级编号
     */
    public String getCnum() {
        return this.cnum;
    }
 
    /** 
     * 获取所属学期id
     *
     * @return Term_id 所属学期id
     */
    public Long getTermId() {
        return this.termId;
    }
 
    /** 
     * 获取年级标签id
     *
     * @return Grade_id 年级标签id
     */
    public Long getGradeId() {
        return this.gradeId;
    }
 
    /** 
     * 获取作息时间模板id
     *
     * @return Work_id 作息时间模板id
     */
    public Long getWorkId() {
        return this.workId;
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
     * 获取创建时间
     *
     * @return Create_time 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 获取创建者
     *
     * @return Creator 创建者
     */
    public Long getCreator() {
        return this.creator;
    }
 
    /** 
     * 获取更新时间
     *
     * @return Update_time 更新时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }
 
    /** 
     * 获取最后更新者
     *
     * @return Updator 最后更新者
     */
    public Long getUpdator() {
        return this.updator;
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
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置班级名称
     *
     * @param Class_name 班级名称
     */
    public void setClassName(String classname) {
        this.className = classname;
    }
 
    /** 
     * 设置入学年份
     *
     * @param Year 入学年份
     */
    public void setYear(String year) {
        this.year = year;
    }
 
    /** 
     * 设置班级编号
     *
     * @param Cnum 班级编号
     */
    public void setCnum(String cnum) {
        this.cnum = cnum;
    }
 
    /** 
     * 设置所属学期id
     *
     * @param Term_id 所属学期id
     */
    public void setTermId(Long termid) {
        this.termId = termid;
    }
 
    /** 
     * 设置年级标签id
     *
     * @param Grade_id 年级标签id
     */
    public void setGradeId(Long gradeid) {
        this.gradeId = gradeid;
    }
 
    /** 
     * 设置作息时间模板id
     *
     * @param Work_id 作息时间模板id
     */
    public void setWorkId(Long workid) {
        this.workId = workid;
    }
 
    /** 
     * 设置是否启用
     *
     * @param Is_use 是否启用
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
    /** 
     * 设置创建时间
     *
     * @param Create_time 创建时间
     */
    public void setCreateTime(String createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置创建者
     *
     * @param Creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
 
    /** 
     * 设置更新时间
     *
     * @param Update_time 更新时间
     */
    public void setUpdateTime(String updatetime) {
        this.updateTime = updatetime;
    }
 
    /** 
     * 设置最后更新者
     *
     * @param Updator 最后更新者
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
 
}
