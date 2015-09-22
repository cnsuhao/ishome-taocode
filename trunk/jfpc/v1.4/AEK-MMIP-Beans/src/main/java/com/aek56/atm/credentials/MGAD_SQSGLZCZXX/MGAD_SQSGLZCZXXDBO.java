package com.aek56.atm.credentials.MGAD_SQSGLZCZXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商授权书关联注册证信息*/
public class MGAD_SQSGLZCZXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商企业ID
     */
    private String k01_gysid = "";
 
    /** 
     * 注册证ID（业务主键）
     */
    private String k02_zczid = "";
 
    /** 
     * 获取供应商企业ID
     *
     * @return K01_GYSID 供应商企业ID
     */
    public String getK01_gysid() {
        return this.k01_gysid;
    }
 
    /** 
     * 获取注册证ID（业务主键）
     *
     * @return K02_ZCZID 注册证ID（业务主键）
     */
    public String getK02_zczid() {
        return this.k02_zczid;
    }
 
    /** 
     * 设置供应商企业ID
     *
     * @param K01_GYSID 供应商企业ID
     */
    public void setK01_gysid(String K01_GYSID) {
        this.k01_gysid = K01_GYSID;
    }
 
    /** 
     * 设置注册证ID（业务主键）
     *
     * @param K02_ZCZID 注册证ID（业务主键）
     */
    public void setK02_zczid(String K02_ZCZID) {
        this.k02_zczid = K02_ZCZID;
    }
 
}
