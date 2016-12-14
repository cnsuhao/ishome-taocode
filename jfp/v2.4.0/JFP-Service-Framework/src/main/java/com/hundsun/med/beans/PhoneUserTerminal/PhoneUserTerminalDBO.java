package com.hundsun.med.beans.PhoneUserTerminal;
import javax.inject.Named;

import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 用户终端表*/
public class PhoneUserTerminalDBO extends MyDataBaseObjectSupport
{
    /** 
     * 用户ID
     */
    private String usId = null;
 
    /** 
     * 医院ID
     */
    private String hosId = null;
 
    /** 
     * 终端类型：android:1,ios:2
     */
    private String terminalType = null;
 
    /** 
     * 手机的唯一标识
     */
    private String token = null;
 
    /** 
     * IOS推送token
     */
    private String pushToken = null;
 
    /** 
     * 获取用户ID
     *
     * @return US_ID 用户ID
     */
    public String getUsId() {
        return this.usId;
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
     * 获取终端类型：android:1,ios:2
     *
     * @return TERMINAL_TYPE 终端类型：android:1,ios:2
     */
    public String getTerminalType() {
        return this.terminalType;
    }
 
    /** 
     * 获取手机的唯一标识
     *
     * @return TOKEN 手机的唯一标识
     */
    public String getToken() {
        return this.token;
    }
 
    /** 
     * 获取IOS推送token
     *
     * @return PUSH_TOKEN IOS推送token
     */
    public String getPushToken() {
        return this.pushToken;
    }
 
    /** 
     * 设置用户ID
     *
     * @param US_ID 用户ID
     */
    public void setUsId(String usid) {
        this.usId = usid;
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
     * 设置终端类型：android:1,ios:2
     *
     * @param TERMINAL_TYPE 终端类型：android:1,ios:2
     */
    public void setTerminalType(String terminaltype) {
        this.terminalType = terminaltype;
    }
 
    /** 
     * 设置手机的唯一标识
     *
     * @param TOKEN 手机的唯一标识
     */
    public void setToken(String token) {
        this.token = token;
    }
 
    /** 
     * 设置IOS推送token
     *
     * @param PUSH_TOKEN IOS推送token
     */
    public void setPushToken(String pushtoken) {
        this.pushToken = pushtoken;
    }
 
}
