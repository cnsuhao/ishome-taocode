package com.mcookies.qxy.common.ClassAlarm;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 班级报警规则关联表 */
public class ClassAlarmDBO extends MyDataBaseObjectSupport {
	/** 
     * 数据id
     */
    private Long did = null;
 
    /** 
     * 班级id
     */
    private Long cid = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 规则id
     */
    private Long alarmruleId = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
  
    /** 
     * 获取数据id
     *
     * @return Did 数据id
     */
    public Long getDid() {
        return this.did;
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
     * 获取学校id
     *
     * @return Sid 学校id
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * 获取规则id
     *
     * @return Alarmrule_id 规则id
     */
    public Long getAlarmruleId() {
        return this.alarmruleId;
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
     * 设置数据id
     *
     * @param Did 数据id
     */
    public void setDid(Long did) {
        this.did = did;
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
     * 设置规则id
     *
     * @param Alarmrule_id 规则id
     */
    public void setAlarmruleId(Long alarmruleid) {
        this.alarmruleId = alarmruleid;
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
