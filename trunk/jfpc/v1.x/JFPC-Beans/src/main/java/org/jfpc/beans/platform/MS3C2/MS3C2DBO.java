package org.jfpc.beans.platform.MS3C2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 发送短信信息*/
public class MS3C2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 业务标识ID
     */
    private String k01_ywbsid = "";
 
    /** 
     * 手机号码
     */
    private String k02_sjhm = "";
 
    /** 
     * 短信网关ID
     */
    private String k03_dxwgid = "";
 
    /** 
     * 网关状态
     */
    private String f01_wgzt = "";
 
    /** 
     * 发送状态
     */
    private String f02_fszt = "";
 
    /** 
     * 网关服务返回状态
     */
    private String f03_wgfhzt = "";
 
    /** 
     * 获取业务标识ID
     *
     * @return K01_YWBSID 业务标识ID
     */
    public String getK01_ywbsid() {
        return this.k01_ywbsid;
    }
 
    /** 
     * 获取手机号码
     *
     * @return K02_SJHM 手机号码
     */
    public String getK02_sjhm() {
        return this.k02_sjhm;
    }
 
    /** 
     * 获取短信网关ID
     *
     * @return K03_DXWGID 短信网关ID
     */
    public String getK03_dxwgid() {
        return this.k03_dxwgid;
    }
 
    /** 
     * 获取网关状态
     *
     * @return F01_WGZT 网关状态
     */
    public String getF01_wgzt() {
        return this.f01_wgzt;
    }
 
    /** 
     * 获取发送状态
     *
     * @return F02_FSZT 发送状态
     */
    public String getF02_fszt() {
        return this.f02_fszt;
    }
 
    /** 
     * 获取网关服务返回状态
     *
     * @return F03_WGFHZT 网关服务返回状态
     */
    public String getF03_wgfhzt() {
        return this.f03_wgfhzt;
    }
 
    /** 
     * 设置业务标识ID
     *
     * @param K01_YWBSID 业务标识ID
     */
    public void setK01_ywbsid(String K01_YWBSID) {
        this.k01_ywbsid = K01_YWBSID;
    }
 
    /** 
     * 设置手机号码
     *
     * @param K02_SJHM 手机号码
     */
    public void setK02_sjhm(String K02_SJHM) {
        this.k02_sjhm = K02_SJHM;
    }
 
    /** 
     * 设置短信网关ID
     *
     * @param K03_DXWGID 短信网关ID
     */
    public void setK03_dxwgid(String K03_DXWGID) {
        this.k03_dxwgid = K03_DXWGID;
    }
 
    /** 
     * 设置网关状态
     *
     * @param F01_WGZT 网关状态
     */
    public void setF01_wgzt(String F01_WGZT) {
        this.f01_wgzt = F01_WGZT;
    }
 
    /** 
     * 设置发送状态
     *
     * @param F02_FSZT 发送状态
     */
    public void setF02_fszt(String F02_FSZT) {
        this.f02_fszt = F02_FSZT;
    }
 
    /** 
     * 设置网关服务返回状态
     *
     * @param F03_WGFHZT 网关服务返回状态
     */
    public void setF03_wgfhzt(String F03_WGFHZT) {
        this.f03_wgfhzt = F03_WGFHZT;
    }
 
}
