package org.ishome.jfp.beans.HospitalCloudAccessRule;
import java.util.Date;

import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class HospitalCloudAccessRuleDBO extends MyDataBaseObjectSupport
{
    /** 
     * 数据识别ID
     */
    private String ruleId = null;
 
    /** 
     * 医院ID
     */
    private String hosId = null;
 
    /** 
     * 对接模块类型
     */
    private String moduleType = null;
 
    /** 
     * 对接模块类别
     */
    private String accessType = null;
 
    /** 
     * 对接模块开关
     */
    private String accessFlag = null;
 
    /** 
     * 每秒钟数据处理量
     */
    private String concurrency = null;
 
    /** 
     * 加密状态
     */
    private String fb3 = null;
 
    /** 
     * 有效标识
     */
    private String isDeleted = null;
 
    /** 
     * 创建时间
     */
    private Date createTime = null;
 
    /** 
     * 创建者
     */
    private String creator = null;
 
    /** 
     * 最后更新者
     */
    private String updator = null;
 
    /** 
     * 获取数据识别ID
     *
     * @return RULE_ID 数据识别ID
     */
    public String getRuleId() {
        return this.ruleId;
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
     * 获取对接模块类型
     *
     * @return MODULE_TYPE 对接模块类型
     */
    public String getModuleType() {
        return this.moduleType;
    }
 
    /** 
     * 获取对接模块类别
     *
     * @return ACCESS_TYPE 对接模块类别
     */
    public String getAccessType() {
        return this.accessType;
    }
 
    /** 
     * 获取对接模块开关
     *
     * @return ACCESS_FLAG 对接模块开关
     */
    public String getAccessFlag() {
        return this.accessFlag;
    }
 
    /** 
     * 获取每秒钟数据处理量
     *
     * @return CONCURRENCY 每秒钟数据处理量
     */
    public String getConcurrency() {
        return this.concurrency;
    }
 
    /** 
     * 获取加密状态
     *
     * @return FB3 加密状态
     */
    public String getFb3() {
        return this.fb3;
    }
 
    /** 
     * 获取有效标识
     *
     * @return IS_DELETED 有效标识
     */
    public String getIsDeleted() {
        return this.isDeleted;
    }
 
    /** 
     * 获取创建时间
     *
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 获取创建者
     *
     * @return CREATOR 创建者
     */
    public String getCreator() {
        return this.creator;
    }
 
    /** 
     * 获取最后更新者
     *
     * @return UPDATOR 最后更新者
     */
    public String getUpdator() {
        return this.updator;
    }
 
    /** 
     * 设置数据识别ID
     *
     * @param RULE_ID 数据识别ID
     */
    public void setRuleId(String ruleid) {
        this.ruleId = ruleid;
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
     * 设置对接模块类型
     *
     * @param MODULE_TYPE 对接模块类型
     */
    public void setModuleType(String moduletype) {
        this.moduleType = moduletype;
    }
 
    /** 
     * 设置对接模块类别
     *
     * @param ACCESS_TYPE 对接模块类别
     */
    public void setAccessType(String accesstype) {
        this.accessType = accesstype;
    }
 
    /** 
     * 设置对接模块开关
     *
     * @param ACCESS_FLAG 对接模块开关
     */
    public void setAccessFlag(String accessflag) {
        this.accessFlag = accessflag;
    }
 
    /** 
     * 设置每秒钟数据处理量
     *
     * @param CONCURRENCY 每秒钟数据处理量
     */
    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }
 
    /** 
     * 设置加密状态
     *
     * @param FB3 加密状态
     */
    public void setFb3(String fb3) {
        this.fb3 = fb3;
    }
 
    /** 
     * 设置有效标识
     *
     * @param IS_DELETED 有效标识
     */
    public void setIsDeleted(String isdeleted) {
        this.isDeleted = isdeleted;
    }
 
    /** 
     * 设置创建时间
     *
     * @param CREATE_TIME 创建时间
     */
    public void setCreateTime(Date createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置创建者
     *
     * @param CREATOR 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    /** 
     * 设置最后更新者
     *
     * @param UPDATOR 最后更新者
     */
    public void setUpdator(String updator) {
        this.updator = updator;
    }
 
}
