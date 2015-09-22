package com.aek56.atm.master.MD8_CPZCZXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 产品注册证信息*/
public class MD8_CPZCZXXDBO extends MyDataBaseObjectSupport
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
 
}
