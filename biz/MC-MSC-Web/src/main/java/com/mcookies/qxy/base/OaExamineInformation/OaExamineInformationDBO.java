package com.mcookies.qxy.base.OaExamineInformation;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** OA审批信息表*/
public class OaExamineInformationDBO extends MyDataBaseObjectSupport
{
    /** 
     * 审核信息id
     */
    private Long approvalInformationId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 标签类型
     */
    private Integer type = null;
 
    /** 
     * 标签id
     */
    private Long tagsId = null;
 
    /** 
     * 发起人id
     */
    private Long tid = null;
 
    /** 
     * 待审批内容
     */
    private String content = null;
 
    /** 
     * 审核结果
     */
    private Integer result = null;
 
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
     * 获取审核信息id
     *
     * @return Approval_information_id 审核信息id
     */
    public Long getApprovalInformationId() {
        return this.approvalInformationId;
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
     * 获取标签类型
     *
     * @return Type 标签类型
     */
    public Integer getType() {
        return this.type;
    }
 
    /** 
     * 获取标签id
     *
     * @return Tags_id 标签id
     */
    public Long getTagsId() {
        return this.tagsId;
    }
 
    /** 
     * 获取发起人id
     *
     * @return Tid 发起人id
     */
    public Long getTid() {
        return this.tid;
    }
 
    /** 
     * 获取待审批内容
     *
     * @return Content 待审批内容
     */
    public String getContent() {
        return this.content;
    }
 
    /** 
     * 获取审核结果
     *
     * @return Result 审核结果
     */
    public Integer getResult() {
        return this.result;
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
     * 设置审核信息id
     *
     * @param Approval_information_id 审核信息id
     */
    public void setApprovalInformationId(Long approvalinformationid) {
        this.approvalInformationId = approvalinformationid;
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
     * 设置标签类型
     *
     * @param Type 标签类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
 
    /** 
     * 设置标签id
     *
     * @param Tags_id 标签id
     */
    public void setTagsId(Long tagsid) {
        this.tagsId = tagsid;
    }
 
    /** 
     * 设置发起人id
     *
     * @param Tid 发起人id
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }
 
    /** 
     * 设置待审批内容
     *
     * @param Content 待审批内容
     */
    public void setContent(String content) {
        this.content = content;
    }
 
    /** 
     * 设置审核结果
     *
     * @param Result 审核结果
     */
    public void setResult(Integer result) {
        this.result = result;
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
