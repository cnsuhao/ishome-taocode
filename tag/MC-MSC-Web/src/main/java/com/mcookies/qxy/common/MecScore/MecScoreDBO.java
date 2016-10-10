package com.mcookies.qxy.common.MecScore;

import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 德育班级评分表*/
public class MecScoreDBO extends MyDataBaseObjectSupport
{
    /** 
     * 评分id
     */
    private Long mecScoreId = null;
 
    /** 
     * 学期id
     */
    private Long termId = null;
 
    /** 
     * 班级id
     */
    private Long cid = null;
 
    /** 
     * 德育项目id
     */
    private Long mecItemId = null;
 
    /** 
     * 德育班级评分序号
     */
    private String scroeOrder = null;
 
    /** 
     * 德育班级评分
     */
    private String scroe = null;
 
    /** 
     * 评分时间
     */
    private String scoreTime = null;
 
    /** 
     * 评分人（tid）
     */
    private Long tid = null;
 
    /** 
     * 评分图片
     */
    private String scorePic = null;
 
    /** 
     * 评分内容
     */
    private String scoreContent = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
 
    /** 
     * 获取评分id
     *
     * @return Mec_score_id 评分id
     */
    public Long getMecScoreId() {
        return this.mecScoreId;
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
     * 获取德育项目id
     *
     * @return Mec_item_id 德育项目id
     */
    public Long getMecItemId() {
        return this.mecItemId;
    }
 
    /** 
     * 获取德育班级评分序号
     *
     * @return Scroe_order 德育班级评分序号
     */
    public String getScroeOrder() {
        return this.scroeOrder;
    }
 
    /** 
     * 获取德育班级评分
     *
     * @return Scroe 德育班级评分
     */
    public String getScroe() {
        return this.scroe;
    }
 
    /** 
     * 获取评分时间
     *
     * @return Score_time 评分时间
     */
    public String getScoreTime() {
        return this.scoreTime;
    }
 
    /** 
     * 获取评分人（tid）
     *
     * @return Tid 评分人（tid）
     */
    public Long getTid() {
        return this.tid;
    }
 
    /** 
     * 获取评分图片
     *
     * @return Score_pic 评分图片
     */
    public String getScorePic() {
        return this.scorePic;
    }
 
    /** 
     * 获取评分内容
     *
     * @return Score_content 评分内容
     */
    public String getScoreContent() {
        return this.scoreContent;
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
     * 设置评分id
     *
     * @param Mec_score_id 评分id
     */
    public void setMecScoreId(Long mecscoreid) {
        this.mecScoreId = mecscoreid;
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
     * 设置德育项目id
     *
     * @param Mec_item_id 德育项目id
     */
    public void setMecItemId(Long mecitemid) {
        this.mecItemId = mecitemid;
    }
 
    /** 
     * 设置德育班级评分序号
     *
     * @param Scroe_order 德育班级评分序号
     */
    public void setScroeOrder(String scroeorder) {
        this.scroeOrder = scroeorder;
    }
 
    /** 
     * 设置德育班级评分
     *
     * @param Scroe 德育班级评分
     */
    public void setScroe(String scroe) {
        this.scroe = scroe;
    }
 
    /** 
     * 设置评分时间
     *
     * @param Score_time 评分时间
     */
    public void setScoreTime(String scoretime) {
        this.scoreTime = scoretime;
    }
 
    /** 
     * 设置评分人（tid）
     *
     * @param Tid 评分人（tid）
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }
 
    /** 
     * 设置评分图片
     *
     * @param Score_pic 评分图片
     */
    public void setScorePic(String scorepic) {
        this.scorePic = scorepic;
    }
 
    /** 
     * 设置评分内容
     *
     * @param Score_content 评分内容
     */
    public void setScoreContent(String scorecontent) {
        this.scoreContent = scorecontent;
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
