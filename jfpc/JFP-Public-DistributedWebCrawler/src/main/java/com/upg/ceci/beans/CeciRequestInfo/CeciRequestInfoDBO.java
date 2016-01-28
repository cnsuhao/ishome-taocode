package com.upg.ceci.beans.CeciRequestInfo;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciRequestInfoDBO extends MyDataBaseObjectSupport
{
    /** 
     * 主键ID
     */
    private String id = null;
 
    /** 
     * 区域码
     */
    private String areaCode = null;
 
    /** 
     * 域名
     */
    private String host = null;
 
    /** 
     * 请求地址
     */
    private String rurl = null;
 
    /** 
     * 是否需要cookie
     */
    private String isCookie = null;
 
    /** 
     * cookie域
     */
    private String cookieDomen = null;
 
    /** 
     * cookie路径
     */
    private String cookiePath = null;
 
    /** 
     * 模块类型
     */
    private String modeType = null;
 
    /** 
     * 模块说明
     */
    private String modeTypeDesc = null;
 
    /** 
     * 请求方式(get,post)
     */
    private String requestType = null;
 
    /** 
     * 信息类型
     */
    private String infoType = null;
 
    /** 
     * 参数
     */
    private String params = null;
 
    /** 
     * 子类请求ID
     */
    private String subId = null;
 
    /** 
     * 获取主键ID
     *
     * @return ID 主键ID
     */
    public String getId() {
        return this.id;
    }
 
    /** 
     * 获取区域码
     *
     * @return Area_code 区域码
     */
    public String getAreaCode() {
        return this.areaCode;
    }
 
    /** 
     * 获取域名
     *
     * @return Host 域名
     */
    public String getHost() {
        return this.host;
    }
 
    /** 
     * 获取请求地址
     *
     * @return Rurl 请求地址
     */
    public String getRurl() {
        return this.rurl;
    }
 
    /** 
     * 获取是否需要cookie
     *
     * @return Is_cookie 是否需要cookie
     */
    public String getIsCookie() {
        return this.isCookie;
    }
 
    /** 
     * 获取cookie域
     *
     * @return Cookie_domen cookie域
     */
    public String getCookieDomen() {
        return this.cookieDomen;
    }
 
    /** 
     * 获取cookie路径
     *
     * @return Cookie_path cookie路径
     */
    public String getCookiePath() {
        return this.cookiePath;
    }
 
    /** 
     * 获取模块类型
     *
     * @return Mode_type 模块类型
     */
    public String getModeType() {
        return this.modeType;
    }
 
    /** 
     * 获取模块说明
     *
     * @return Mode_type_desc 模块说明
     */
    public String getModeTypeDesc() {
        return this.modeTypeDesc;
    }
 
    /** 
     * 获取请求方式(get,post)
     *
     * @return Request_type 请求方式(get,post)
     */
    public String getRequestType() {
        return this.requestType;
    }
 
    /** 
     * 获取信息类型
     *
     * @return Info_type 信息类型
     */
    public String getInfoType() {
        return this.infoType;
    }
 
    /** 
     * 获取参数
     *
     * @return Params 参数
     */
    public String getParams() {
        return this.params;
    }
 
    /** 
     * 获取子类请求ID
     *
     * @return Sub_id 子类请求ID
     */
    public String getSubId() {
        return this.subId;
    }
 
    /** 
     * 设置主键ID
     *
     * @param ID 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }
 
    /** 
     * 设置区域码
     *
     * @param Area_code 区域码
     */
    public void setAreaCode(String areacode) {
        this.areaCode = areacode;
    }
 
    /** 
     * 设置域名
     *
     * @param Host 域名
     */
    public void setHost(String host) {
        this.host = host;
    }
 
    /** 
     * 设置请求地址
     *
     * @param Rurl 请求地址
     */
    public void setRurl(String rurl) {
        this.rurl = rurl;
    }
 
    /** 
     * 设置是否需要cookie
     *
     * @param Is_cookie 是否需要cookie
     */
    public void setIsCookie(String iscookie) {
        this.isCookie = iscookie;
    }
 
    /** 
     * 设置cookie域
     *
     * @param Cookie_domen cookie域
     */
    public void setCookieDomen(String cookiedomen) {
        this.cookieDomen = cookiedomen;
    }
 
    /** 
     * 设置cookie路径
     *
     * @param Cookie_path cookie路径
     */
    public void setCookiePath(String cookiepath) {
        this.cookiePath = cookiepath;
    }
 
    /** 
     * 设置模块类型
     *
     * @param Mode_type 模块类型
     */
    public void setModeType(String modetype) {
        this.modeType = modetype;
    }
 
    /** 
     * 设置模块说明
     *
     * @param Mode_type_desc 模块说明
     */
    public void setModeTypeDesc(String modetypedesc) {
        this.modeTypeDesc = modetypedesc;
    }
 
    /** 
     * 设置请求方式(get,post)
     *
     * @param Request_type 请求方式(get,post)
     */
    public void setRequestType(String requesttype) {
        this.requestType = requesttype;
    }
 
    /** 
     * 设置信息类型
     *
     * @param Info_type 信息类型
     */
    public void setInfoType(String infotype) {
        this.infoType = infotype;
    }
 
    /** 
     * 设置参数
     *
     * @param Params 参数
     */
    public void setParams(String params) {
        this.params = params;
    }
 
    /** 
     * 设置子类请求ID
     *
     * @param Sub_id 子类请求ID
     */
    public void setSubId(String subid) {
        this.subId = subid;
    }
 
}
