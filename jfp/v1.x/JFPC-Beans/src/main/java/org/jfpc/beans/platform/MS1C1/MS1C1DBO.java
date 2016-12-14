package org.jfpc.beans.platform.MS1C1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 登录用户临时授权*/
public class MS1C1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 业务标识ID
     */
    private String k01_ywbsid = "";
 
    /** 
     * 授权人ID
     */
    private String k02_sqrid = "";
 
    /** 
     * 授权开始日时
     */
    private String f01_kssj = "";
 
    /** 
     * 授权终了日时
     */
    private String f02_zlsj = "";
 
    /** 
     * 获取业务标识ID
     *
     * @return K01_YWBSID 业务标识ID
     */
    public String getK01_ywbsid() {
        return this.k01_ywbsid;
    }
 
    /** 
     * 获取授权人ID
     *
     * @return K02_SQRID 授权人ID
     */
    public String getK02_sqrid() {
        return this.k02_sqrid;
    }
 
    /** 
     * 获取授权开始日时
     *
     * @return F01_KSSJ 授权开始日时
     */
    public String getF01_kssj() {
        return this.f01_kssj;
    }
 
    /** 
     * 获取授权终了日时
     *
     * @return F02_ZLSJ 授权终了日时
     */
    public String getF02_zlsj() {
        return this.f02_zlsj;
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
     * 设置授权人ID
     *
     * @param K02_SQRID 授权人ID
     */
    public void setK02_sqrid(String K02_SQRID) {
        this.k02_sqrid = K02_SQRID;
    }
 
    /** 
     * 设置授权开始日时
     *
     * @param F01_KSSJ 授权开始日时
     */
    public void setF01_kssj(String F01_KSSJ) {
        this.f01_kssj = F01_KSSJ;
    }
 
    /** 
     * 设置授权终了日时
     *
     * @param F02_ZLSJ 授权终了日时
     */
    public void setF02_zlsj(String F02_ZLSJ) {
        this.f02_zlsj = F02_ZLSJ;
    }
 
}
