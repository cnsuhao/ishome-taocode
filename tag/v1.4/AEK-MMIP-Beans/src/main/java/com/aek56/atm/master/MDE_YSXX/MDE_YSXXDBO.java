package com.aek56.atm.master.MDE_YSXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 医生信息*/
public class MDE_YSXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 医院ID
     */
    private String k01_yyid = "";
 
    /** 
     * 科室ID
     */
    private String k02_ksid = "";
 
    /** 
     * 身份证号码
     */
    private String k03_sfzhm = "";
 
    /** 
     * 医生姓名
     */
    private String f01_yyxm = "";
 
    /** 
     * 医生姓名简拼
     */
    private String f02_yyxmpym = "";
 
    /** 
     * 出生年月
     */
    private String f03_csnyr = "";
 
    /** 
     * 血型
     */
    private String f04_xx = "";
 
    /** 
     * 医生类别
     */
    private String f05_yslb = "";
 
    /** 
     * 性别
     */
    private String f06_xb = "";
 
    /** 
     * 科室名称
     */
    private String f07_ksmc = "";
 
    /** 
     * 医院名称
     */
    private String f08_yymc = "";
 
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
     * 字段19
     */
    private String f19 = "";
 
    /** 
     * 字段20
     */
    private String f20 = "";
 
    /** 
     * 获取医院ID
     *
     * @return K01_YYID 医院ID
     */
    public String getK01_yyid() {
        return this.k01_yyid;
    }
 
    /** 
     * 获取科室ID
     *
     * @return K02_KSID 科室ID
     */
    public String getK02_ksid() {
        return this.k02_ksid;
    }
 
    /** 
     * 获取身份证号码
     *
     * @return K03_SFZHM 身份证号码
     */
    public String getK03_sfzhm() {
        return this.k03_sfzhm;
    }
 
    /** 
     * 获取医生姓名
     *
     * @return F01_YYXM 医生姓名
     */
    public String getF01_yyxm() {
        return this.f01_yyxm;
    }
 
    /** 
     * 获取医生姓名简拼
     *
     * @return F02_YYXMPYM 医生姓名简拼
     */
    public String getF02_yyxmpym() {
        return this.f02_yyxmpym;
    }
 
    /** 
     * 获取出生年月
     *
     * @return F03_CSNYR 出生年月
     */
    public String getF03_csnyr() {
        return this.f03_csnyr;
    }
 
    /** 
     * 获取血型
     *
     * @return F04_XX 血型
     */
    public String getF04_xx() {
        return this.f04_xx;
    }
 
    /** 
     * 获取医生类别
     *
     * @return F05_YSLB 医生类别
     */
    public String getF05_yslb() {
        return this.f05_yslb;
    }
 
    /** 
     * 获取性别
     *
     * @return F06_XB 性别
     */
    public String getF06_xb() {
        return this.f06_xb;
    }
 
    /** 
     * 获取科室名称
     *
     * @return F07_KSMC 科室名称
     */
    public String getF07_ksmc() {
        return this.f07_ksmc;
    }
 
    /** 
     * 获取医院名称
     *
     * @return F08_YYMC 医院名称
     */
    public String getF08_yymc() {
        return this.f08_yymc;
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
     * 设置医院ID
     *
     * @param K01_YYID 医院ID
     */
    public void setK01_yyid(String K01_YYID) {
        this.k01_yyid = K01_YYID;
    }
 
    /** 
     * 设置科室ID
     *
     * @param K02_KSID 科室ID
     */
    public void setK02_ksid(String K02_KSID) {
        this.k02_ksid = K02_KSID;
    }
 
    /** 
     * 设置身份证号码
     *
     * @param K03_SFZHM 身份证号码
     */
    public void setK03_sfzhm(String K03_SFZHM) {
        this.k03_sfzhm = K03_SFZHM;
    }
 
    /** 
     * 设置医生姓名
     *
     * @param F01_YYXM 医生姓名
     */
    public void setF01_yyxm(String F01_YYXM) {
        this.f01_yyxm = F01_YYXM;
    }
 
    /** 
     * 设置医生姓名简拼
     *
     * @param F02_YYXMPYM 医生姓名简拼
     */
    public void setF02_yyxmpym(String F02_YYXMPYM) {
        this.f02_yyxmpym = F02_YYXMPYM;
    }
 
    /** 
     * 设置出生年月
     *
     * @param F03_CSNYR 出生年月
     */
    public void setF03_csnyr(String F03_CSNYR) {
        this.f03_csnyr = F03_CSNYR;
    }
 
    /** 
     * 设置血型
     *
     * @param F04_XX 血型
     */
    public void setF04_xx(String F04_XX) {
        this.f04_xx = F04_XX;
    }
 
    /** 
     * 设置医生类别
     *
     * @param F05_YSLB 医生类别
     */
    public void setF05_yslb(String F05_YSLB) {
        this.f05_yslb = F05_YSLB;
    }
 
    /** 
     * 设置性别
     *
     * @param F06_XB 性别
     */
    public void setF06_xb(String F06_XB) {
        this.f06_xb = F06_XB;
    }
 
    /** 
     * 设置科室名称
     *
     * @param F07_KSMC 科室名称
     */
    public void setF07_ksmc(String F07_KSMC) {
        this.f07_ksmc = F07_KSMC;
    }
 
    /** 
     * 设置医院名称
     *
     * @param F08_YYMC 医院名称
     */
    public void setF08_yymc(String F08_YYMC) {
        this.f08_yymc = F08_YYMC;
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
