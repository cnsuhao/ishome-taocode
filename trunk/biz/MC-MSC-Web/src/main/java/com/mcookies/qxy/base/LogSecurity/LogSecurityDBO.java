package com.mcookies.qxy.base.LogSecurity;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 安全日志表*/
public class LogSecurityDBO extends MyDataBaseObjectSupport
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
     * 学期id
     */
    private Long termId = null;
 
    /** 
     * 班级id
     */
    private Long cid = null;
 
    /** 
     * 学生id
     */
    private Long studentId = null;
 
    /** 
     * 学生卡id
     */
    private Long rfid = null;
 
    /** 
     * 学校设备id
     */
    private Long deviceId = null;
 
    /** 
     * 打卡时间
     */
    private String markTime = null;
 
    /** 
     * 行为状态
     */
    private Integer flag = null;
 
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
     * 获取学期id
     *
     * @return Term_id 学期id
     */
    public Long getTermId() {
        return this.termId;
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
     * 获取学生卡id
     *
     * @return Rfid 学生卡id
     */
    public Long getRfid() {
        return this.rfid;
    }
 
    /** 
     * 获取学校设备id
     *
     * @return Device_id 学校设备id
     */
    public Long getDeviceId() {
        return this.deviceId;
    }
 
    /** 
     * 获取打卡时间
     *
     * @return Mark_time 打卡时间
     */
    public String getMarkTime() {
        return this.markTime;
    }
 
    /** 
     * 获取行为状态
     *
     * @return Flag 行为状态
     */
    public Integer getFlag() {
        return this.flag;
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
     * 设置学期id
     *
     * @param Term_id 学期id
     */
    public void setTermId(Long termid) {
        this.termId = termid;
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
     * 设置学生卡id
     *
     * @param Rfid 学生卡id
     */
    public void setRfid(Long rfid) {
        this.rfid = rfid;
    }
 
    /** 
     * 设置学校设备id
     *
     * @param Device_id 学校设备id
     */
    public void setDeviceId(Long deviceid) {
        this.deviceId = deviceid;
    }
 
    /** 
     * 设置打卡时间
     *
     * @param Mark_time 打卡时间
     */
    public void setMarkTime(String marktime) {
        this.markTime = marktime;
    }
 
    /** 
     * 设置行为状态
     *
     * @param Flag 行为状态
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
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
