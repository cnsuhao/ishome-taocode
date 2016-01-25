package com.aek56.atm.master.MDA_CPXXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 产品线信息*/
public class MDA_CPXXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 厂商ID
     */
    private String k01_csid = "";
 
    /** 
     * 产品线类别
     */
    private String k02_cpxlb = "";
 
    /** 
     * 主键3
     */
    private String k03 = "";
 
    /** 
     * 产品线中文名称
     */
    private String f01_zwmc = "";
 
    /** 
     * 产品线英文名称
     */
    private String f02_ywmc = "";
 
    /** 
     * 产品线中文名称拼音码
     */
    private String f03_zwmcpym = "";
 
    /** 
     * 产品线英文名称缩写
     */
    private String f04_ywmcsx = "";
 
    /** 
     * 产品线中文描述
     */
    private String f05_zwms = "";
 
    /** 
     * 产品线英文描述
     */
    private String f06_ywms = "";
 
    /** 
     * 节奏快慢（字段保留待用）
     */
    private String f07_jzkm = "";
 
    /** 
     * 自动化程度（字段保留待用）
     */
    private String f08_zdhcd = "";
 
    /** 
     * 产品线长度（米）（字段保留待用）
     */
    private String f09_cpxcd = "";
 
    /** 
     * 灭菌方式（字段保留待用）
     */
    private String f10_mjfs = "";
 
    /** 
     * 字段11
     */
    private String f11 = "";
 
    /** 
     * 字段12
     */
    private String f12 = "";
 
    /** 
     * 字段13
     */
    private String f13 = "";
 
    /** 
     * 字段14
     */
    private String f14 = "";
 
    /** 
     * 字段15
     */
    private String f15 = "";
 
    /** 
     * 字段16
     */
    private String f16 = "";
 
    /** 
     * 字段17
     */
    private String f17 = "";
 
    /** 
     * 字段18
     */
    private String f18 = "";
 
    /** 
     * 字段19
     */
    private String f19 = "";
 
    /** 
     * 字段20
     */
    private String f20 = "";
 
    /** 
     * 获取厂商ID
     *
     * @return K01_CSID 厂商ID
     */
    public String getK01_csid() {
        return this.k01_csid;
    }
 
    /** 
     * 获取产品线类别
     *
     * @return K02_CPXLB 产品线类别
     */
    public String getK02_cpxlb() {
        return this.k02_cpxlb;
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
     * 获取产品线中文名称
     *
     * @return F01_ZWMC 产品线中文名称
     */
    public String getF01_zwmc() {
        return this.f01_zwmc;
    }
 
    /** 
     * 获取产品线英文名称
     *
     * @return F02_YWMC 产品线英文名称
     */
    public String getF02_ywmc() {
        return this.f02_ywmc;
    }
 
    /** 
     * 获取产品线中文名称拼音码
     *
     * @return F03_ZWMCPYM 产品线中文名称拼音码
     */
    public String getF03_zwmcpym() {
        return this.f03_zwmcpym;
    }
 
    /** 
     * 获取产品线英文名称缩写
     *
     * @return F04_YWMCSX 产品线英文名称缩写
     */
    public String getF04_ywmcsx() {
        return this.f04_ywmcsx;
    }
 
    /** 
     * 获取产品线中文描述
     *
     * @return F05_ZWMS 产品线中文描述
     */
    public String getF05_zwms() {
        return this.f05_zwms;
    }
 
    /** 
     * 获取产品线英文描述
     *
     * @return F06_YWMS 产品线英文描述
     */
    public String getF06_ywms() {
        return this.f06_ywms;
    }
 
    /** 
     * 获取节奏快慢（字段保留待用）
     *
     * @return F07_JZKM 节奏快慢（字段保留待用）
     */
    public String getF07_jzkm() {
        return this.f07_jzkm;
    }
 
    /** 
     * 获取自动化程度（字段保留待用）
     *
     * @return F08_ZDHCD 自动化程度（字段保留待用）
     */
    public String getF08_zdhcd() {
        return this.f08_zdhcd;
    }
 
    /** 
     * 获取产品线长度（米）（字段保留待用）
     *
     * @return F09_CPXCD 产品线长度（米）（字段保留待用）
     */
    public String getF09_cpxcd() {
        return this.f09_cpxcd;
    }
 
    /** 
     * 获取灭菌方式（字段保留待用）
     *
     * @return F10_MJFS 灭菌方式（字段保留待用）
     */
    public String getF10_mjfs() {
        return this.f10_mjfs;
    }
 
    /** 
     * 获取字段11
     *
     * @return F11 字段11
     */
    public String getF11() {
        return this.f11;
    }
 
    /** 
     * 获取字段12
     *
     * @return F12 字段12
     */
    public String getF12() {
        return this.f12;
    }
 
    /** 
     * 获取字段13
     *
     * @return F13 字段13
     */
    public String getF13() {
        return this.f13;
    }
 
    /** 
     * 获取字段14
     *
     * @return F14 字段14
     */
    public String getF14() {
        return this.f14;
    }
 
    /** 
     * 获取字段15
     *
     * @return F15 字段15
     */
    public String getF15() {
        return this.f15;
    }
 
    /** 
     * 获取字段16
     *
     * @return F16 字段16
     */
    public String getF16() {
        return this.f16;
    }
 
    /** 
     * 获取字段17
     *
     * @return F17 字段17
     */
    public String getF17() {
        return this.f17;
    }
 
    /** 
     * 获取字段18
     *
     * @return F18 字段18
     */
    public String getF18() {
        return this.f18;
    }
 
    /** 
     * 获取字段19
     *
     * @return F19 字段19
     */
    public String getF19() {
        return this.f19;
    }
 
    /** 
     * 获取字段20
     *
     * @return F20 字段20
     */
    public String getF20() {
        return this.f20;
    }
 
    /** 
     * 设置厂商ID
     *
     * @param K01_CSID 厂商ID
     */
    public void setK01_csid(String K01_CSID) {
        this.k01_csid = K01_CSID;
    }
 
    /** 
     * 设置产品线类别
     *
     * @param K02_CPXLB 产品线类别
     */
    public void setK02_cpxlb(String K02_CPXLB) {
        this.k02_cpxlb = K02_CPXLB;
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
     * 设置产品线中文名称
     *
     * @param F01_ZWMC 产品线中文名称
     */
    public void setF01_zwmc(String F01_ZWMC) {
        this.f01_zwmc = F01_ZWMC;
    }
 
    /** 
     * 设置产品线英文名称
     *
     * @param F02_YWMC 产品线英文名称
     */
    public void setF02_ywmc(String F02_YWMC) {
        this.f02_ywmc = F02_YWMC;
    }
 
    /** 
     * 设置产品线中文名称拼音码
     *
     * @param F03_ZWMCPYM 产品线中文名称拼音码
     */
    public void setF03_zwmcpym(String F03_ZWMCPYM) {
        this.f03_zwmcpym = F03_ZWMCPYM;
    }
 
    /** 
     * 设置产品线英文名称缩写
     *
     * @param F04_YWMCSX 产品线英文名称缩写
     */
    public void setF04_ywmcsx(String F04_YWMCSX) {
        this.f04_ywmcsx = F04_YWMCSX;
    }
 
    /** 
     * 设置产品线中文描述
     *
     * @param F05_ZWMS 产品线中文描述
     */
    public void setF05_zwms(String F05_ZWMS) {
        this.f05_zwms = F05_ZWMS;
    }
 
    /** 
     * 设置产品线英文描述
     *
     * @param F06_YWMS 产品线英文描述
     */
    public void setF06_ywms(String F06_YWMS) {
        this.f06_ywms = F06_YWMS;
    }
 
    /** 
     * 设置节奏快慢（字段保留待用）
     *
     * @param F07_JZKM 节奏快慢（字段保留待用）
     */
    public void setF07_jzkm(String F07_JZKM) {
        this.f07_jzkm = F07_JZKM;
    }
 
    /** 
     * 设置自动化程度（字段保留待用）
     *
     * @param F08_ZDHCD 自动化程度（字段保留待用）
     */
    public void setF08_zdhcd(String F08_ZDHCD) {
        this.f08_zdhcd = F08_ZDHCD;
    }
 
    /** 
     * 设置产品线长度（米）（字段保留待用）
     *
     * @param F09_CPXCD 产品线长度（米）（字段保留待用）
     */
    public void setF09_cpxcd(String F09_CPXCD) {
        this.f09_cpxcd = F09_CPXCD;
    }
 
    /** 
     * 设置灭菌方式（字段保留待用）
     *
     * @param F10_MJFS 灭菌方式（字段保留待用）
     */
    public void setF10_mjfs(String F10_MJFS) {
        this.f10_mjfs = F10_MJFS;
    }
 
    /** 
     * 设置字段11
     *
     * @param F11 字段11
     */
    public void setF11(String F11) {
        this.f11 = F11;
    }
 
    /** 
     * 设置字段12
     *
     * @param F12 字段12
     */
    public void setF12(String F12) {
        this.f12 = F12;
    }
 
    /** 
     * 设置字段13
     *
     * @param F13 字段13
     */
    public void setF13(String F13) {
        this.f13 = F13;
    }
 
    /** 
     * 设置字段14
     *
     * @param F14 字段14
     */
    public void setF14(String F14) {
        this.f14 = F14;
    }
 
    /** 
     * 设置字段15
     *
     * @param F15 字段15
     */
    public void setF15(String F15) {
        this.f15 = F15;
    }
 
    /** 
     * 设置字段16
     *
     * @param F16 字段16
     */
    public void setF16(String F16) {
        this.f16 = F16;
    }
 
    /** 
     * 设置字段17
     *
     * @param F17 字段17
     */
    public void setF17(String F17) {
        this.f17 = F17;
    }
 
    /** 
     * 设置字段18
     *
     * @param F18 字段18
     */
    public void setF18(String F18) {
        this.f18 = F18;
    }
 
    /** 
     * 设置字段19
     *
     * @param F19 字段19
     */
    public void setF19(String F19) {
        this.f19 = F19;
    }
 
    /** 
     * 设置字段20
     *
     * @param F20 字段20
     */
    public void setF20(String F20) {
        this.f20 = F20;
    }
 
}
