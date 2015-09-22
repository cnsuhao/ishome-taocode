package com.aek56.atm.master.MD9_CPCPXXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 产品产品线信息*/
public class MD9_CPCPXXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 证件号唯一码（注册证）
     */
    private String k01_zchwym = "";
 
    /** 
     * 厂商ID
     */
    private String k02_csid = "";
 
    /** 
     * 产品线ID
     */
    private String k03_cpxid = "";
 
    /** 
     * 获取证件号唯一码（注册证）
     *
     * @return K01_ZCHWYM 证件号唯一码（注册证）
     */
    public String getK01_zchwym() {
        return this.k01_zchwym;
    }
 
    /** 
     * 获取厂商ID
     *
     * @return K02_CSID 厂商ID
     */
    public String getK02_csid() {
        return this.k02_csid;
    }
 
    /** 
     * 获取产品线ID
     *
     * @return K03_CPXID 产品线ID
     */
    public String getK03_cpxid() {
        return this.k03_cpxid;
    }
 
    /** 
     * 设置证件号唯一码（注册证）
     *
     * @param K01_ZCHWYM 证件号唯一码（注册证）
     */
    public void setK01_zchwym(String K01_ZCHWYM) {
        this.k01_zchwym = K01_ZCHWYM;
    }
 
    /** 
     * 设置厂商ID
     *
     * @param K02_CSID 厂商ID
     */
    public void setK02_csid(String K02_CSID) {
        this.k02_csid = K02_CSID;
    }
 
    /** 
     * 设置产品线ID
     *
     * @param K03_CPXID 产品线ID
     */
    public void setK03_cpxid(String K03_CPXID) {
        this.k03_cpxid = K03_CPXID;
    }
 
}
