package com.mcookies.qxy.base.UTeacherExt;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 教师信息扩展表*/
public class UTeacherExtDBO extends MyDataBaseObjectSupport
{
    /** 
     * 教师id
     */
    private Long tid = null;
 
    /** 
     * 教师工号
     */
    private String jobNumber = null;
 
    /** 
     * 教师性别
     */
    private Integer gender = null;
 
    /** 
     * 出生年月
     */
    private String dateOfBirth = null;
 
    /** 
     * 开始工作时间
     */
    private String startWorkT = null;
 
    /** 
     * 证件类型
     */
    private String cardType = null;
 
    /** 
     * 证件号码
     */
    private String cardNumber = null;
 
    /** 
     * 家庭住址
     */
    private String homeAddress = null;
 
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
     * 获取教师id
     *
     * @return Tid 教师id
     */
    public Long getTid() {
        return this.tid;
    }
 
    /** 
     * 获取教师工号
     *
     * @return Job_number 教师工号
     */
    public String getJobNumber() {
        return this.jobNumber;
    }
 
    /** 
     * 获取教师性别
     *
     * @return Gender 教师性别
     */
    public Integer getGender() {
        return this.gender;
    }
 
    /** 
     * 获取出生年月
     *
     * @return Date_of_birth 出生年月
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
 
    /** 
     * 获取开始工作时间
     *
     * @return Start_work_t 开始工作时间
     */
    public String getStartWorkT() {
        return this.startWorkT;
    }
 
    /** 
     * 获取证件类型
     *
     * @return Card_type 证件类型
     */
    public String getCardType() {
        return this.cardType;
    }
 
    /** 
     * 获取证件号码
     *
     * @return Card_number 证件号码
     */
    public String getCardNumber() {
        return this.cardNumber;
    }
 
    /** 
     * 获取家庭住址
     *
     * @return Home_address 家庭住址
     */
    public String getHomeAddress() {
        return this.homeAddress;
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
     * 设置教师id
     *
     * @param Tid 教师id
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }
 
    /** 
     * 设置教师工号
     *
     * @param Job_number 教师工号
     */
    public void setJobNumber(String jobnumber) {
        this.jobNumber = jobnumber;
    }
 
    /** 
     * 设置教师性别
     *
     * @param Gender 教师性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }
 
    /** 
     * 设置出生年月
     *
     * @param Date_of_birth 出生年月
     */
    public void setDateOfBirth(String dateofbirth) {
        this.dateOfBirth = dateofbirth;
    }
 
    /** 
     * 设置开始工作时间
     *
     * @param Start_work_t 开始工作时间
     */
    public void setStartWorkT(String startworkt) {
        this.startWorkT = startworkt;
    }
 
    /** 
     * 设置证件类型
     *
     * @param Card_type 证件类型
     */
    public void setCardType(String cardtype) {
        this.cardType = cardtype;
    }
 
    /** 
     * 设置证件号码
     *
     * @param Card_number 证件号码
     */
    public void setCardNumber(String cardnumber) {
        this.cardNumber = cardnumber;
    }
 
    /** 
     * 设置家庭住址
     *
     * @param Home_address 家庭住址
     */
    public void setHomeAddress(String homeaddress) {
        this.homeAddress = homeaddress;
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
