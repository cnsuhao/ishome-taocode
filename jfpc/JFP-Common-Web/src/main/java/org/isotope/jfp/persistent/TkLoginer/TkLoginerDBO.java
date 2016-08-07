package org.isotope.jfp.persistent.TkLoginer;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 第三方授权Token表*/
public class TkLoginerDBO extends MyDataBaseObjectSupport
{
    /** 
     * TOKEN
     */
    private String authorizerAccessToken = null;
 
    /** 
     * 用户ID
     */
    private String json = null;
 
    /** 
     * 获取TOKEN
     *
     * @return Authorizer_access_token TOKEN
     */
    public String getAuthorizerAccessToken() {
        return this.authorizerAccessToken;
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
    public void setAuthorizerAccessToken(String authorizeraccesstoken) {
        this.authorizerAccessToken = authorizeraccesstoken;
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
