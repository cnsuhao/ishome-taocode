package com.aek56.atm.credentials.MGTB_CJHCSPRZ;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商提供医院厂家耗材商品3C认证*/
public class MGTB_CJHCSPRZDBO extends MyDataBaseObjectSupport
{
    /** 
     * 医院ID
     */
    private String p01_yyid = "";
 
    /** 
     * 供应商ID
     */
    private String k01_gysid = "";
 
    /** 
     * 生产厂家企业ID
     */
    private String k02_sccj_qyid = "";
 
    /** 
     * 证件编号
     */
    private String k03_zjbh = "";
 
    /** 
     * 证件类别
     */
    private String k04_zjlb = "";
 
    /** 
     * 发证单位名称
     */
    private String f01_fzdwmc = "";
 
    /** 
     * 发证日期
     */
    private String f02_fzrq = "";
 
    /** 
     * 有效年限
     */
    private String f03_yxnx = "";
 
    /** 
     * 有效开始日期
     */
    private String f04_yxksrq = "";
 
    /** 
     * 有效终止日期
     */
    private String f05_yxzzrq = "";
 
    /** 
     * 审核状态
     */
    private String f06_shzt = "";
 
    /** 
     * 字段7
     */
    private String f07 = "";
 
    /** 
     * 字段8
     */
    private String f08 = "";
 
    /** 
     * 字段9
     */
    private String f09 = "";
 
    /** 
     * 字段10
     */
    private String f10 = "";
 
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
     * 物理文件保存位置
     */
    private String f19_wlwjbcwz = "";
 
    /** 
     * 是否停用
     */
    private String f20_sfty = "";
 
    /** 
     * 获取医院ID
     *
     * @return P01_YYID 医院ID
     */
    public String getP01_yyid() {
        return this.p01_yyid;
    }
 
    /** 
     * 获取供应商ID
     *
     * @return K01_GYSID 供应商ID
     */
    public String getK01_gysid() {
        return this.k01_gysid;
    }
 
    /** 
     * 获取生产厂家企业ID
     *
     * @return K02_SCCJ_QYID 生产厂家企业ID
     */
    public String getK02_sccj_qyid() {
        return this.k02_sccj_qyid;
    }
 
    /** 
     * 获取证件编号
     *
     * @return K03_ZJBH 证件编号
     */
    public String getK03_zjbh() {
        return this.k03_zjbh;
    }
 
    /** 
     * 获取证件类别
     *
     * @return K04_ZJLB 证件类别
     */
    public String getK04_zjlb() {
        return this.k04_zjlb;
    }
 
    /** 
     * 获取发证单位名称
     *
     * @return F01_FZDWMC 发证单位名称
     */
    public String getF01_fzdwmc() {
        return this.f01_fzdwmc;
    }
 
    /** 
     * 获取发证日期
     *
     * @return F02_FZRQ 发证日期
     */
    public String getF02_fzrq() {
        return this.f02_fzrq;
    }
 
    /** 
     * 获取有效年限
     *
     * @return F03_YXNX 有效年限
     */
    public String getF03_yxnx() {
        return this.f03_yxnx;
    }
 
    /** 
     * 获取有效开始日期
     *
     * @return F04_YXKSRQ 有效开始日期
     */
    public String getF04_yxksrq() {
        return this.f04_yxksrq;
    }
 
    /** 
     * 获取有效终止日期
     *
     * @return F05_YXZZRQ 有效终止日期
     */
    public String getF05_yxzzrq() {
        return this.f05_yxzzrq;
    }
 
    /** 
     * 获取审核状态
     *
     * @return F06_SHZT 审核状态
     */
    public String getF06_shzt() {
        return this.f06_shzt;
    }
 
    /** 
     * 获取字段7
     *
     * @return F07 字段7
     */
    public String getF07() {
        return this.f07;
    }
 
    /** 
     * 获取字段8
     *
     * @return F08 字段8
     */
    public String getF08() {
        return this.f08;
    }
 
    /** 
     * 获取字段9
     *
     * @return F09 字段9
     */
    public String getF09() {
        return this.f09;
    }
 
    /** 
     * 获取字段10
     *
     * @return F10 字段10
     */
    public String getF10() {
        return this.f10;
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
     * 获取物理文件保存位置
     *
     * @return F19_WLWJBCWZ 物理文件保存位置
     */
    public String getF19_wlwjbcwz() {
        return this.f19_wlwjbcwz;
    }
 
    /** 
     * 获取是否停用
     *
     * @return F20_SFTY 是否停用
     */
    public String getF20_sfty() {
        return this.f20_sfty;
    }
 
    /** 
     * 设置医院ID
     *
     * @param P01_YYID 医院ID
     */
    public void setP01_yyid(String P01_YYID) {
        this.p01_yyid = P01_YYID;
    }
 
    /** 
     * 设置供应商ID
     *
     * @param K01_GYSID 供应商ID
     */
    public void setK01_gysid(String K01_GYSID) {
        this.k01_gysid = K01_GYSID;
    }
 
    /** 
     * 设置生产厂家企业ID
     *
     * @param K02_SCCJ_QYID 生产厂家企业ID
     */
    public void setK02_sccj_qyid(String K02_SCCJ_QYID) {
        this.k02_sccj_qyid = K02_SCCJ_QYID;
    }
 
    /** 
     * 设置证件编号
     *
     * @param K03_ZJBH 证件编号
     */
    public void setK03_zjbh(String K03_ZJBH) {
        this.k03_zjbh = K03_ZJBH;
    }
 
    /** 
     * 设置证件类别
     *
     * @param K04_ZJLB 证件类别
     */
    public void setK04_zjlb(String K04_ZJLB) {
        this.k04_zjlb = K04_ZJLB;
    }
 
    /** 
     * 设置发证单位名称
     *
     * @param F01_FZDWMC 发证单位名称
     */
    public void setF01_fzdwmc(String F01_FZDWMC) {
        this.f01_fzdwmc = F01_FZDWMC;
    }
 
    /** 
     * 设置发证日期
     *
     * @param F02_FZRQ 发证日期
     */
    public void setF02_fzrq(String F02_FZRQ) {
        this.f02_fzrq = F02_FZRQ;
    }
 
    /** 
     * 设置有效年限
     *
     * @param F03_YXNX 有效年限
     */
    public void setF03_yxnx(String F03_YXNX) {
        this.f03_yxnx = F03_YXNX;
    }
 
    /** 
     * 设置有效开始日期
     *
     * @param F04_YXKSRQ 有效开始日期
     */
    public void setF04_yxksrq(String F04_YXKSRQ) {
        this.f04_yxksrq = F04_YXKSRQ;
    }
 
    /** 
     * 设置有效终止日期
     *
     * @param F05_YXZZRQ 有效终止日期
     */
    public void setF05_yxzzrq(String F05_YXZZRQ) {
        this.f05_yxzzrq = F05_YXZZRQ;
    }
 
    /** 
     * 设置审核状态
     *
     * @param F06_SHZT 审核状态
     */
    public void setF06_shzt(String F06_SHZT) {
        this.f06_shzt = F06_SHZT;
    }
 
    /** 
     * 设置字段7
     *
     * @param F07 字段7
     */
    public void setF07(String F07) {
        this.f07 = F07;
    }
 
    /** 
     * 设置字段8
     *
     * @param F08 字段8
     */
    public void setF08(String F08) {
        this.f08 = F08;
    }
 
    /** 
     * 设置字段9
     *
     * @param F09 字段9
     */
    public void setF09(String F09) {
        this.f09 = F09;
    }
 
    /** 
     * 设置字段10
     *
     * @param F10 字段10
     */
    public void setF10(String F10) {
        this.f10 = F10;
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
     * 设置物理文件保存位置
     *
     * @param F19_WLWJBCWZ 物理文件保存位置
     */
    public void setF19_wlwjbcwz(String F19_WLWJBCWZ) {
        this.f19_wlwjbcwz = F19_WLWJBCWZ;
    }
 
    /** 
     * 设置是否停用
     *
     * @param F20_SFTY 是否停用
     */
    public void setF20_sfty(String F20_SFTY) {
        this.f20_sfty = F20_SFTY;
    }
 
}
