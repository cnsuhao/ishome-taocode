package com.hundsun.med.beans.PushInfoLog;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 推送信息日志表*/
public class PushInfoLogDBO extends MyDataBaseObjectSupport
{
    /** 
     * 推送日志ID
     */
    private String psLogId = null;
 
    /** 
     * 推送信息ID
     */
    private String psId = null;
 
    /** 
     * 手机用户ID
     */
    private String usId = null;
 
    /** 
     * 用户类型(1：医院员工;0或者null：患者用户)
     */
    private BigDecimal userType = BigDecimal.ZERO;
 
    /** 
     * 发送时间
     */
    private Date sendTime = null;
 
    /** 
     * 
     */
    private BigDecimal monthDate = BigDecimal.ZERO;
 
    /** 
     * 是否发送成功:2处理中;1成功;0失败
     */
    private BigDecimal isPushed = BigDecimal.ZERO;
 
    /** 
     * 发送的标题
     */
    private String title = null;
 
    /** 
     * 发送的内容
     */
    private String msgContent = null;
 
    /** 
     * 控制唯一对应临时表数据
     */
    private String psTempId = null;
 
    /** 
     * 医院ID
     */
    private String hosId = null;
 
    /** 
     * 推送类型(0：文本型；1：网页型)
     */
    private BigDecimal taskType = BigDecimal.ZERO;
 
    /** 
     * 网页型HTTPS链接
     */
    private String httpsUrl = null;
 
    /** 
     * 网页型图片链接
     */
    private String imgUrl = null;
 
    /** 
     * 获取推送日志ID
     *
     * @return PS_LOG_ID 推送日志ID
     */
    public String getPsLogId() {
        return this.psLogId;
    }
 
    /** 
     * 获取推送信息ID
     *
     * @return PS_ID 推送信息ID
     */
    public String getPsId() {
        return this.psId;
    }
 
    /** 
     * 获取手机用户ID
     *
     * @return US_ID 手机用户ID
     */
    public String getUsId() {
        return this.usId;
    }
 
    /** 
     * 获取用户类型(1：医院员工;0或者null：患者用户)
     *
     * @return USER_TYPE 用户类型(1：医院员工;0或者null：患者用户)
     */
    public BigDecimal getUserType() {
        return this.userType;
    }
 
    /** 
     * 获取发送时间
     *
     * @return SEND_TIME 发送时间
     */
    public Date getSendTime() {
        return this.sendTime;
    }
 
    /** 
     * 获取
     *
     * @return MONTH_DATE 
     */
    public BigDecimal getMonthDate() {
        return this.monthDate;
    }
 
    /** 
     * 获取是否发送成功:2处理中;1成功;0失败
     *
     * @return IS_PUSHED 是否发送成功:2处理中;1成功;0失败
     */
    public BigDecimal getIsPushed() {
        return this.isPushed;
    }
 
    /** 
     * 获取发送的标题
     *
     * @return TITLE 发送的标题
     */
    public String getTitle() {
        return this.title;
    }
 
    /** 
     * 获取发送的内容
     *
     * @return MSG_CONTENT 发送的内容
     */
    public String getMsgContent() {
        return this.msgContent;
    }
 
    /** 
     * 获取控制唯一对应临时表数据
     *
     * @return PS_TEMP_ID 控制唯一对应临时表数据
     */
    public String getPsTempId() {
        return this.psTempId;
    }
 
    /** 
     * 获取医院ID
     *
     * @return HOS_ID 医院ID
     */
    public String getHosId() {
        return this.hosId;
    }
 
    /** 
     * 获取推送类型(0：文本型；1：网页型)
     *
     * @return TASK_TYPE 推送类型(0：文本型；1：网页型)
     */
    public BigDecimal getTaskType() {
        return this.taskType;
    }
 
    /** 
     * 获取网页型HTTPS链接
     *
     * @return HTTPS_URL 网页型HTTPS链接
     */
    public String getHttpsUrl() {
        return this.httpsUrl;
    }
 
    /** 
     * 获取网页型图片链接
     *
     * @return IMG_URL 网页型图片链接
     */
    public String getImgUrl() {
        return this.imgUrl;
    }
 
    /** 
     * 设置推送日志ID
     *
     * @param PS_LOG_ID 推送日志ID
     */
    public void setPsLogId(String pslogid) {
        this.psLogId = pslogid;
    }
 
    /** 
     * 设置推送信息ID
     *
     * @param PS_ID 推送信息ID
     */
    public void setPsId(String psid) {
        this.psId = psid;
    }
 
    /** 
     * 设置手机用户ID
     *
     * @param US_ID 手机用户ID
     */
    public void setUsId(String usid) {
        this.usId = usid;
    }
 
    /** 
     * 设置用户类型(1：医院员工;0或者null：患者用户)
     *
     * @param USER_TYPE 用户类型(1：医院员工;0或者null：患者用户)
     */
    public void setUserType(BigDecimal usertype) {
        this.userType = usertype;
    }
 
    /** 
     * 设置发送时间
     *
     * @param SEND_TIME 发送时间
     */
    public void setSendTime(Date sendtime) {
        this.sendTime = sendtime;
    }
 
    /** 
     * 设置
     *
     * @param MONTH_DATE 
     */
    public void setMonthDate(BigDecimal monthdate) {
        this.monthDate = monthdate;
    }
 
    /** 
     * 设置是否发送成功:2处理中;1成功;0失败
     *
     * @param IS_PUSHED 是否发送成功:2处理中;1成功;0失败
     */
    public void setIsPushed(BigDecimal ispushed) {
        this.isPushed = ispushed;
    }
 
    /** 
     * 设置发送的标题
     *
     * @param TITLE 发送的标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
 
    /** 
     * 设置发送的内容
     *
     * @param MSG_CONTENT 发送的内容
     */
    public void setMsgContent(String msgcontent) {
        this.msgContent = msgcontent;
    }
 
    /** 
     * 设置控制唯一对应临时表数据
     *
     * @param PS_TEMP_ID 控制唯一对应临时表数据
     */
    public void setPsTempId(String pstempid) {
        this.psTempId = pstempid;
    }
 
    /** 
     * 设置医院ID
     *
     * @param HOS_ID 医院ID
     */
    public void setHosId(String hosid) {
        this.hosId = hosid;
    }
 
    /** 
     * 设置推送类型(0：文本型；1：网页型)
     *
     * @param TASK_TYPE 推送类型(0：文本型；1：网页型)
     */
    public void setTaskType(BigDecimal tasktype) {
        this.taskType = tasktype;
    }
 
    /** 
     * 设置网页型HTTPS链接
     *
     * @param HTTPS_URL 网页型HTTPS链接
     */
    public void setHttpsUrl(String httpsurl) {
        this.httpsUrl = httpsurl;
    }
 
    /** 
     * 设置网页型图片链接
     *
     * @param IMG_URL 网页型图片链接
     */
    public void setImgUrl(String imgurl) {
        this.imgUrl = imgurl;
    }
 
}
