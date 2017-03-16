package org.isotope.jfp.common.weixin;

import java.sql.Date;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 微信消息记录表*/
public class SWxMessageDBO extends MyDataBaseObjectSupport {
    /** 
     * 自增id
     */
    private Long id = null;
 
    /** 
     * 学校机构id
     */
    private Long companyid = null;
 
    /** 
     * 消息媒体类型
     */
    private String mediatype = null;
 
    /** 
     * 应用id
     */
    private String agentid = null;
 
    /** 
     * 群发消息内容
     */
    private String message = null;
 
    /** 
     * 用户id集合
     */
    private String userids = null;
 
    /** 
     * 用户名字集合
     */
    private String usernames = null;
 
    /** 
     * 学校分组id集合
     */
    private String groupids = null;
 
    /** 
     * 学校分组名字集合
     */
    private String groupnames = null;
 
    /** 
     * 发送人
     */
    private Long tid = null;
 
    /** 
     * 发送时间
     */
    private String sendingTime = null;
 

    /** 
     * 获取自增id
     *
     * @return Id 自增id
     */
    public Long getId() {
        return this.id;
    }
 
    /** 
     * 获取学校机构id
     *
     * @return CompanyId 学校机构id
     */
    public Long getCompanyid() {
        return this.companyid;
    }
 
    /** 
     * 获取消息媒体类型
     *
     * @return MediaType 消息媒体类型
     */
    public String getMediatype() {
        return this.mediatype;
    }
 
    /** 
     * 获取应用id
     *
     * @return Agentid 应用id
     */
    public String getAgentid() {
        return this.agentid;
    }
 
    /** 
     * 获取群发消息内容
     *
     * @return Message 群发消息内容
     */
    public String getMessage() {
        return this.message;
    }
 
    /** 
     * 获取用户id集合
     *
     * @return UserIds 用户id集合
     */
    public String getUserids() {
        return this.userids;
    }
 
    /** 
     * 获取用户名字集合
     *
     * @return UserNames 用户名字集合
     */
    public String getUsernames() {
        return this.usernames;
    }
 
    /** 
     * 获取学校分组id集合
     *
     * @return GroupIds 学校分组id集合
     */
    public String getGroupids() {
        return this.groupids;
    }
 
    /** 
     * 获取学校分组名字集合
     *
     * @return GroupNames 学校分组名字集合
     */
    public String getGroupnames() {
        return this.groupnames;
    }
 
    /** 
     * 获取发送人
     *
     * @return Tid 发送人
     */
    public Long getTid() {
        return this.tid;
    }
 
    /** 
     * 获取发送时间
     *
     * @return Sending_time 发送时间
     */
    public String getSendingTime() {
        return this.sendingTime;
    }
 
 
 
    /** 
     * 设置自增id
     *
     * @param Id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }
 
    /** 
     * 设置学校机构id
     *
     * @param CompanyId 学校机构id
     */
    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }
 
    /** 
     * 设置消息媒体类型
     *
     * @param MediaType 消息媒体类型
     */
    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }
 
    /** 
     * 设置应用id
     *
     * @param Agentid 应用id
     */
    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }
 
    /** 
     * 设置群发消息内容
     *
     * @param Message 群发消息内容
     */
    public void setMessage(String message) {
        this.message = message;
    }
 
    /** 
     * 设置用户id集合
     *
     * @param UserIds 用户id集合
     */
    public void setUserids(String userids) {
        this.userids = userids;
    }
 
    /** 
     * 设置用户名字集合
     *
     * @param UserNames 用户名字集合
     */
    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }
 
    /** 
     * 设置学校分组id集合
     *
     * @param GroupIds 学校分组id集合
     */
    public void setGroupids(String groupids) {
        this.groupids = groupids;
    }
 
    /** 
     * 设置学校分组名字集合
     *
     * @param GroupNames 学校分组名字集合
     */
    public void setGroupnames(String groupnames) {
        this.groupnames = groupnames;
    }
 
    /** 
     * 设置发送人
     *
     * @param Tid 发送人
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }
 
    /** 
     * 设置发送时间
     *
     * @param Sending_time 发送时间
     */
    public void setSendingTime(String sendingtime) {
        this.sendingTime = sendingtime;
    }
 

 
}
