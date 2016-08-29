package org.isotope.jfp.persistent.TkLoginer;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 当前登录用户Token表*/
public class TkLoginerDBO extends MyDataBaseObjectSupport
{
    /** 
     * TOKEN
     */
    private String token = null;
 
    /** 
     * 用户ID
     */
    private String json = null;
 
    /** 
     * 获取TOKEN
     *
     * @return Authorizer_access_token TOKEN
     */
    public String getToken() {
        return this.token;
    }
 
    /** 
     * 获取用户ID
     *
     * @return Json 用户ID
     */
    public String getJson() {
        return this.json;
    }
 
    /** 
     * 设置TOKEN
     *
     * @param Authorizer_access_token TOKEN
     */
    public void setToken(String token) {
        this.token = token;
    }
 
    /** 
     * 设置用户ID
     *
     * @param Json 用户ID
     */
    public void setJson(String json) {
        this.json = json;
    }
 
}
