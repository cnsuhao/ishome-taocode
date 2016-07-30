package com.mcookies.qxy.base.NewsColumn;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 栏目表*/
public class NewsColumnDBO extends MyDataBaseObjectSupport
{
    /** 
     * 栏目id（自增）
     */
    private Long columnId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 栏目名称
     */
    private String title = null;
 
    /** 
     * 访问权限
     */
    private Integer auth = null;
 
    /** 
     * 是否需要审核
     */
    private Integer isCheck = null;
 
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
     * 获取栏目id（自增）
     *
     * @return Column_id 栏目id（自增）
     */
    public Long getColumnId() {
        return this.columnId;
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
     * 获取栏目名称
     *
     * @return Title 栏目名称
     */
    public String getTitle() {
        return this.title;
    }
 
    /** 
     * 获取访问权限
     *
     * @return Auth 访问权限
     */
    public Integer getAuth() {
        return this.auth;
    }
 
    /** 
     * 获取是否需要审核
     *
     * @return Is_check 是否需要审核
     */
    public Integer getIsCheck() {
        return this.isCheck;
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
     * 设置栏目id（自增）
     *
     * @param Column_id 栏目id（自增）
     */
    public void setColumnId(Long columnid) {
        this.columnId = columnid;
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
     * 设置栏目名称
     *
     * @param Title 栏目名称
     */
    public void setTitle(String title) {
        this.title = title;
    }
 
    /** 
     * 设置访问权限
     *
     * @param Auth 访问权限
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }
 
    /** 
     * 设置是否需要审核
     *
     * @param Is_check 是否需要审核
     */
    public void setIsCheck(Integer ischeck) {
        this.isCheck = ischeck;
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
