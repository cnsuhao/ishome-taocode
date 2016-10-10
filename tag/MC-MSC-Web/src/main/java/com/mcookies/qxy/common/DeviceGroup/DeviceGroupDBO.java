package com.mcookies.qxy.common.DeviceGroup;
import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 设备分组表*/
public class DeviceGroupDBO extends MyDataBaseObjectSupport
{
    /** 
     * 分组id
     */
    private Long dgroupId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 分组名称
     */
    private String dgroupName = null;
 
    /** 
     * 分组说明
     */
    private String dgroupExplain = null;
 
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
     * 获取分组id
     *
     * @return Dgroup_id 分组id
     */
    public Long getDgroupId() {
        return this.dgroupId;
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
     * 获取分组名称
     *
     * @return Dgroup_name 分组名称
     */
    public String getDgroupName() {
        return this.dgroupName;
    }
 
    /** 
     * 获取分组说明
     *
     * @return Dgroup_explain 分组说明
     */
    public String getDgroupExplain() {
        return this.dgroupExplain;
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
     * 设置分组id
     *
     * @param Dgroup_id 分组id
     */
    public void setDgroupId(Long dgroupid) {
        this.dgroupId = dgroupid;
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
     * 设置分组名称
     *
     * @param Dgroup_name 分组名称
     */
    public void setDgroupName(String dgroupname) {
        this.dgroupName = dgroupname;
    }
 
    /** 
     * 设置分组说明
     *
     * @param Dgroup_explain 分组说明
     */
    public void setDgroupExplain(String dgroupexplain) {
        this.dgroupExplain = dgroupexplain;
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
