package com.mcookies.qxy.common.SchoolWeixinGroupUser;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 学校微信企业号用户所属用户组表*/
public class SchoolWeixinGroupUserDBO extends MyDataBaseObjectSupport
{
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 用户组id
     */
    private Long gid = null;
 
    /** 
     * 用户id
     */
    private Long uid = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
 
    /** 
     * 获取学校id
     *
     * @return Sid 学校id
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * 获取用户组id
     *
     * @return Gid 用户组id
     */
    public Long getGid() {
        return this.gid;
    }
 
    /** 
     * 获取用户id
     *
     * @return Uid 用户id
     */
    public Long getUid() {
        return this.uid;
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
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置用户组id
     *
     * @param Gid 用户组id
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }
 
    /** 
     * 设置用户id
     *
     * @param Uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
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
