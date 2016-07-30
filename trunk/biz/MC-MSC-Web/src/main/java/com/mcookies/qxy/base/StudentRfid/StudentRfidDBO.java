package com.mcookies.qxy.base.StudentRfid;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 学生一卡通关联表*/
public class StudentRfidDBO extends MyDataBaseObjectSupport
{
    /** 
     * 自增id
     */
    private Long id = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 学生id
     */
    private Long studentId = null;
 
    /** 
     * 学生卡id
     */
    private Long rfid = null;
 
    /** 
     * 是否有效
     */
    private Integer isEffective = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
    /** 
     * 创建时间
     */
    private Long createTime = null;
 
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
     * 获取自增id
     *
     * @return Id 自增id
     */
    public Long getId() {
        return this.id;
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
     * 获取学生id
     *
     * @return Student_id 学生id
     */
    public Long getStudentId() {
        return this.studentId;
    }
 
    /** 
     * 获取学生卡id
     *
     * @return Rfid 学生卡id
     */
    public Long getRfid() {
        return this.rfid;
    }
 
    /** 
     * 获取是否有效
     *
     * @return Is_effective 是否有效
     */
    public Integer getIsEffective() {
        return this.isEffective;
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
    public Long getCreateTime() {
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
     * 设置自增id
     *
     * @param Id 自增id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 设置学生id
     *
     * @param Student_id 学生id
     */
    public void setStudentId(Long studentid) {
        this.studentId = studentid;
    }
 
    /** 
     * 设置学生卡id
     *
     * @param Rfid 学生卡id
     */
    public void setRfid(Long rfid) {
        this.rfid = rfid;
    }
 
    /** 
     * 设置是否有效
     *
     * @param Is_effective 是否有效
     */
    public void setIsEffective(Integer iseffective) {
        this.isEffective = iseffective;
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
    public void setCreateTime(Long createtime) {
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
