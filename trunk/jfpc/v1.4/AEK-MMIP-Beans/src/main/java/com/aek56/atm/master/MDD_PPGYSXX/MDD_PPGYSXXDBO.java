package com.aek56.atm.master.MDD_PPGYSXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 品牌关联供应商信息*/
public class MDD_PPGYSXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String k01_gyid = "";
 
    /** 
     * 企业类别
     */
    private String k02_qylb = "";
 
    /** 
     * 等级标识
     */
    private String k03_djbs = "";
 
    /** 
     * 字段1
     */
    private String f01 = "";
 
    /** 
     * 字段2
     */
    private String f02 = "";
 
    /** 
     * 字段3
     */
    private String f03 = "";
 
    /** 
     * 字段4
     */
    private String f04 = "";
 
    /** 
     * 字段5
     */
    private String f05 = "";
 
    /** 
     * 获取供应商ID
     *
     * @return K01_GYID 供应商ID
     */
    public String getK01_gyid() {
        return this.k01_gyid;
    }
 
    /** 
     * 获取企业类别
     *
     * @return K02_QYLB 企业类别
     */
    public String getK02_qylb() {
        return this.k02_qylb;
    }
 
    /** 
     * 获取等级标识
     *
     * @return K03_DJBS 等级标识
     */
    public String getK03_djbs() {
        return this.k03_djbs;
    }
 
    /** 
     * 获取字段1
     *
     * @return F01 字段1
     */
    public String getF01() {
        return this.f01;
    }
 
    /** 
     * 获取字段2
     *
     * @return F02 字段2
     */
    public String getF02() {
        return this.f02;
    }
 
    /** 
     * 获取字段3
     *
     * @return F03 字段3
     */
    public String getF03() {
        return this.f03;
    }
 
    /** 
     * 获取字段4
     *
     * @return F04 字段4
     */
    public String getF04() {
        return this.f04;
    }
 
    /** 
     * 获取字段5
     *
     * @return F05 字段5
     */
    public String getF05() {
        return this.f05;
    }
 
    /** 
     * 设置供应商ID
     *
     * @param K01_GYID 供应商ID
     */
    public void setK01_gyid(String K01_GYID) {
        this.k01_gyid = K01_GYID;
    }
 
    /** 
     * 设置企业类别
     *
     * @param K02_QYLB 企业类别
     */
    public void setK02_qylb(String K02_QYLB) {
        this.k02_qylb = K02_QYLB;
    }
 
    /** 
     * 设置等级标识
     *
     * @param K03_DJBS 等级标识
     */
    public void setK03_djbs(String K03_DJBS) {
        this.k03_djbs = K03_DJBS;
    }
 
    /** 
     * 设置字段1
     *
     * @param F01 字段1
     */
    public void setF01(String F01) {
        this.f01 = F01;
    }
 
    /** 
     * 设置字段2
     *
     * @param F02 字段2
     */
    public void setF02(String F02) {
        this.f02 = F02;
    }
 
    /** 
     * 设置字段3
     *
     * @param F03 字段3
     */
    public void setF03(String F03) {
        this.f03 = F03;
    }
 
    /** 
     * 设置字段4
     *
     * @param F04 字段4
     */
    public void setF04(String F04) {
        this.f04 = F04;
    }
 
    /** 
     * 设置字段5
     *
     * @param F05 字段5
     */
    public void setF05(String F05) {
        this.f05 = F05;
    }
 
}
