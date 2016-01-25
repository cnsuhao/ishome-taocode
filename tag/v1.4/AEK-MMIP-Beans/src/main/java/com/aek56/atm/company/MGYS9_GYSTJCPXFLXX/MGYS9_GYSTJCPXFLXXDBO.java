package com.aek56.atm.company.MGYS9_GYSTJCPXFLXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商添加产品线分类信息*/
public class MGYS9_GYSTJCPXFLXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String p11_gysid = "";
 
    /** 
     * 产品线简称
     */
    private String n01_cpxjc = "";
 
    /** 
     * 产品线简拼
     */
    private String n02_cpxjp = "";
 
    /** 
     * 产品线ID
     */
    private String k01_scxid = "";
 
    /** 
     * 厂商ID
     */
    private String k02_csid = "";
 
    /** 
     * 主键3
     */
    private String k03 = "";
 
    /** 
     * 分类名称
     */
    private String f01_flmc = "";
 
    /** 
     * 分类名称拼音码
     */
    private String f02_flmcpym = "";
 
    /** 
     * 分类类型
     */
    private String f03_fllx = "";
 
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
     * @return P11_GYSID 供应商ID
     */
    public String getP11_gysid() {
        return this.p11_gysid;
    }
 
    /** 
     * 获取产品线简称
     *
     * @return N01_CPXJC 产品线简称
     */
    public String getN01_cpxjc() {
        return this.n01_cpxjc;
    }
 
    /** 
     * 获取产品线简拼
     *
     * @return N02_CPXJP 产品线简拼
     */
    public String getN02_cpxjp() {
        return this.n02_cpxjp;
    }
 
    /** 
     * 获取产品线ID
     *
     * @return K01_SCXID 产品线ID
     */
    public String getK01_scxid() {
        return this.k01_scxid;
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
     * 获取主键3
     *
     * @return K03 主键3
     */
    public String getK03() {
        return this.k03;
    }
 
    /** 
     * 获取分类名称
     *
     * @return F01_FLMC 分类名称
     */
    public String getF01_flmc() {
        return this.f01_flmc;
    }
 
    /** 
     * 获取分类名称拼音码
     *
     * @return F02_FLMCPYM 分类名称拼音码
     */
    public String getF02_flmcpym() {
        return this.f02_flmcpym;
    }
 
    /** 
     * 获取分类类型
     *
     * @return F03_FLLX 分类类型
     */
    public String getF03_fllx() {
        return this.f03_fllx;
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
     * @param P11_GYSID 供应商ID
     */
    public void setP11_gysid(String P11_GYSID) {
        this.p11_gysid = P11_GYSID;
    }
 
    /** 
     * 设置产品线简称
     *
     * @param N01_CPXJC 产品线简称
     */
    public void setN01_cpxjc(String N01_CPXJC) {
        this.n01_cpxjc = N01_CPXJC;
    }
 
    /** 
     * 设置产品线简拼
     *
     * @param N02_CPXJP 产品线简拼
     */
    public void setN02_cpxjp(String N02_CPXJP) {
        this.n02_cpxjp = N02_CPXJP;
    }
 
    /** 
     * 设置产品线ID
     *
     * @param K01_SCXID 产品线ID
     */
    public void setK01_scxid(String K01_SCXID) {
        this.k01_scxid = K01_SCXID;
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
     * 设置主键3
     *
     * @param K03 主键3
     */
    public void setK03(String K03) {
        this.k03 = K03;
    }
 
    /** 
     * 设置分类名称
     *
     * @param F01_FLMC 分类名称
     */
    public void setF01_flmc(String F01_FLMC) {
        this.f01_flmc = F01_FLMC;
    }
 
    /** 
     * 设置分类名称拼音码
     *
     * @param F02_FLMCPYM 分类名称拼音码
     */
    public void setF02_flmcpym(String F02_FLMCPYM) {
        this.f02_flmcpym = F02_FLMCPYM;
    }
 
    /** 
     * 设置分类类型
     *
     * @param F03_FLLX 分类类型
     */
    public void setF03_fllx(String F03_FLLX) {
        this.f03_fllx = F03_FLLX;
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
