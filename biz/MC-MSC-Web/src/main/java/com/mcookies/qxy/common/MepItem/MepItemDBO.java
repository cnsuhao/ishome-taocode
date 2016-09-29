package com.mcookies.qxy.common.MepItem;

import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 德育个人项目表*/
public class MepItemDBO extends MyDataBaseObjectSupport
{
    /** 
     * 德育个人项目id
     */
    private Long mepItemId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 德育个人项目名称
     */
    private String mepItemName = null;
 
    /** 
     * 德育个人项目说明
     */
    private String mepItemExplain = null;
 
    /** 
     * 规则数量
     */
    private String ruleNum = null;
 
    /** 
     * 初始分
     */
    private String initialScore = null;
 
    /** 
     * 间隔分
     */
    private String intervalScore = null;
 
    /** 
     * 权限
     */
    private Integer auth = null;
 
    /** 
     * 可见教师集合
     */
    private String tids = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
 
    /** 
     * 获取德育个人项目id
     *
     * @return Mep_item_id 德育个人项目id
     */
    public Long getMepItemId() {
        return this.mepItemId;
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
     * 获取德育个人项目名称
     *
     * @return Mep_item_name 德育个人项目名称
     */
    public String getMepItemName() {
        return this.mepItemName;
    }
 
    /** 
     * 获取德育个人项目说明
     *
     * @return Mep_item_explain 德育个人项目说明
     */
    public String getMepItemExplain() {
        return this.mepItemExplain;
    }
 
    /** 
     * 获取规则数量
     *
     * @return Rule_num 规则数量
     */
    public String getRuleNum() {
        return this.ruleNum;
    }
 
    /** 
     * 获取初始分
     *
     * @return Initial_score 初始分
     */
    public String getInitialScore() {
        return this.initialScore;
    }
 
    /** 
     * 获取间隔分
     *
     * @return Interval_score 间隔分
     */
    public String getIntervalScore() {
        return this.intervalScore;
    }
 
    /** 
     * 获取权限
     *
     * @return Auth 权限
     */
    public Integer getAuth() {
        return this.auth;
    }
 
    /** 
     * 获取可见教师集合
     *
     * @return Tids 可见教师集合
     */
    public String getTids() {
        return this.tids;
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
     * 设置德育个人项目id
     *
     * @param Mep_item_id 德育个人项目id
     */
    public void setMepItemId(Long mepitemid) {
        this.mepItemId = mepitemid;
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
     * 设置德育个人项目名称
     *
     * @param Mep_item_name 德育个人项目名称
     */
    public void setMepItemName(String mepitemname) {
        this.mepItemName = mepitemname;
    }
 
    /** 
     * 设置德育个人项目说明
     *
     * @param Mep_item_explain 德育个人项目说明
     */
    public void setMepItemExplain(String mepitemexplain) {
        this.mepItemExplain = mepitemexplain;
    }
 
    /** 
     * 设置规则数量
     *
     * @param Rule_num 规则数量
     */
    public void setRuleNum(String rulenum) {
        this.ruleNum = rulenum;
    }
 
    /** 
     * 设置初始分
     *
     * @param Initial_score 初始分
     */
    public void setInitialScore(String initialscore) {
        this.initialScore = initialscore;
    }
 
    /** 
     * 设置间隔分
     *
     * @param Interval_score 间隔分
     */
    public void setIntervalScore(String intervalscore) {
        this.intervalScore = intervalscore;
    }
 
    /** 
     * 设置权限
     *
     * @param Auth 权限
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }
 
    /** 
     * 设置可见教师集合
     *
     * @param Tids 可见教师集合
     */
    public void setTids(String tids) {
        this.tids = tids;
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
