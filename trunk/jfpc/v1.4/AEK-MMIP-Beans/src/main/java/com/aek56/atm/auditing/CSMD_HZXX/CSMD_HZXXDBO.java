package com.aek56.atm.auditing.CSMD_HZXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 患者信息(审核)*/
public class CSMD_HZXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 患者ID
     */
    private String p01_puk = "";
 
    /** 
     * 数据类别
     */
    private String p02_sjlb = "";
 
    /** 
     * 主键1
     */
    private String k01_yyid = "";
 
    /** 
     * 身份证号码
     */
    private String k02_sfzhm = "";
 
    /** 
     * 主键3
     */
    private String k03 = "";
 
    /** 
     * 审核人ID
     */
    private String n01_shrid = "";
 
    /** 
     * 审核人姓名
     */
    private String n02_shrxm = "";
 
    /** 
     * 审核状态（结果）
     */
    private String n03_shzt = "";
 
    /** 
     * 审核理由（邮件短信内容）
     */
    private String n04_shly = "";
 
    /** 
     * 申请企业名称
     */
    private String n05_qymc = "";
 
    /** 
     * 申请人姓名
     */
    private String n06_sqrxm = "";
 
    /** 
     * 短信通知
     */
    private String n07_dxtz = "";
 
    /** 
     * 短信记录ID
     */
    private String n08_dxjlid = "";
 
    /** 
     * 审核时间
     */
    private String n09 = "";
 
    /** 
     * 字段10
     */
    private String n10 = "";
 
    /** 
     * 患者姓名
     */
    private String f01_hzxm = "";
 
    /** 
     * 患者姓名拼音码
     */
    private String f02_hzxmpym = "";
 
    /** 
     * 出生年月日
     */
    private String f03_csnyr = "";
 
    /** 
     * 血型
     */
    private String f04_xx = "";
 
    /** 
     * 性别
     */
    private String f05 = "";
 
    /** 
     * 字段6
     */
    private String f06 = "";
 
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
     * 字段19
     */
    private String f19 = "";
 
    /** 
     * 字段20
     */
    private String f20 = "";
 
    /** 
     * 获取患者ID
     *
     * @return P01_PUK 患者ID
     */
    public String getP01_puk() {
        return this.p01_puk;
    }
 
    /** 
     * 获取数据类别
     *
     * @return P02_SJLB 数据类别
     */
    public String getP02_sjlb() {
        return this.p02_sjlb;
    }
 
    /** 
     * 获取主键1
     *
     * @return K01_YYID 主键1
     */
    public String getK01_yyid() {
        return this.k01_yyid;
    }
 
    /** 
     * 获取身份证号码
     *
     * @return K02_SFZHM 身份证号码
     */
    public String getK02_sfzhm() {
        return this.k02_sfzhm;
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
     * 获取审核人ID
     *
     * @return N01_SHRID 审核人ID
     */
    public String getN01_shrid() {
        return this.n01_shrid;
    }
 
    /** 
     * 获取审核人姓名
     *
     * @return N02_SHRXM 审核人姓名
     */
    public String getN02_shrxm() {
        return this.n02_shrxm;
    }
 
    /** 
     * 获取审核状态（结果）
     *
     * @return N03_SHZT 审核状态（结果）
     */
    public String getN03_shzt() {
        return this.n03_shzt;
    }
 
    /** 
     * 获取审核理由（邮件短信内容）
     *
     * @return N04_SHLY 审核理由（邮件短信内容）
     */
    public String getN04_shly() {
        return this.n04_shly;
    }
 
    /** 
     * 获取申请企业名称
     *
     * @return N05_QYMC 申请企业名称
     */
    public String getN05_qymc() {
        return this.n05_qymc;
    }
 
    /** 
     * 获取申请人姓名
     *
     * @return N06_SQRXM 申请人姓名
     */
    public String getN06_sqrxm() {
        return this.n06_sqrxm;
    }
 
    /** 
     * 获取短信通知
     *
     * @return N07_DXTZ 短信通知
     */
    public String getN07_dxtz() {
        return this.n07_dxtz;
    }
 
    /** 
     * 获取短信记录ID
     *
     * @return N08_DXJLID 短信记录ID
     */
    public String getN08_dxjlid() {
        return this.n08_dxjlid;
    }
 
    /** 
     * 获取审核时间
     *
     * @return N09 审核时间
     */
    public String getN09() {
        return this.n09;
    }
 
    /** 
     * 获取字段10
     *
     * @return N10 字段10
     */
    public String getN10() {
        return this.n10;
    }
 
    /** 
     * 获取患者姓名
     *
     * @return F01_HZXM 患者姓名
     */
    public String getF01_hzxm() {
        return this.f01_hzxm;
    }
 
    /** 
     * 获取患者姓名拼音码
     *
     * @return F02_HZXMPYM 患者姓名拼音码
     */
    public String getF02_hzxmpym() {
        return this.f02_hzxmpym;
    }
 
    /** 
     * 获取出生年月日
     *
     * @return F03_CSNYR 出生年月日
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
     * 获取性别
     *
     * @return F05 性别
     */
    public String getF05() {
        return this.f05;
    }
 
    /** 
     * 获取字段6
     *
     * @return F06 字段6
     */
    public String getF06() {
        return this.f06;
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
     * 设置患者ID
     *
     * @param P01_PUK 患者ID
     */
    public void setP01_puk(String P01_PUK) {
        this.p01_puk = P01_PUK;
    }
 
    /** 
     * 设置数据类别
     *
     * @param P02_SJLB 数据类别
     */
    public void setP02_sjlb(String P02_SJLB) {
        this.p02_sjlb = P02_SJLB;
    }
 
    /** 
     * 设置主键1
     *
     * @param K01_YYID 主键1
     */
    public void setK01_yyid(String K01_YYID) {
        this.k01_yyid = K01_YYID;
    }
 
    /** 
     * 设置身份证号码
     *
     * @param K02_SFZHM 身份证号码
     */
    public void setK02_sfzhm(String K02_SFZHM) {
        this.k02_sfzhm = K02_SFZHM;
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
     * 设置审核人ID
     *
     * @param N01_SHRID 审核人ID
     */
    public void setN01_shrid(String N01_SHRID) {
        this.n01_shrid = N01_SHRID;
    }
 
    /** 
     * 设置审核人姓名
     *
     * @param N02_SHRXM 审核人姓名
     */
    public void setN02_shrxm(String N02_SHRXM) {
        this.n02_shrxm = N02_SHRXM;
    }
 
    /** 
     * 设置审核状态（结果）
     *
     * @param N03_SHZT 审核状态（结果）
     */
    public void setN03_shzt(String N03_SHZT) {
        this.n03_shzt = N03_SHZT;
    }
 
    /** 
     * 设置审核理由（邮件短信内容）
     *
     * @param N04_SHLY 审核理由（邮件短信内容）
     */
    public void setN04_shly(String N04_SHLY) {
        this.n04_shly = N04_SHLY;
    }
 
    /** 
     * 设置申请企业名称
     *
     * @param N05_QYMC 申请企业名称
     */
    public void setN05_qymc(String N05_QYMC) {
        this.n05_qymc = N05_QYMC;
    }
 
    /** 
     * 设置申请人姓名
     *
     * @param N06_SQRXM 申请人姓名
     */
    public void setN06_sqrxm(String N06_SQRXM) {
        this.n06_sqrxm = N06_SQRXM;
    }
 
    /** 
     * 设置短信通知
     *
     * @param N07_DXTZ 短信通知
     */
    public void setN07_dxtz(String N07_DXTZ) {
        this.n07_dxtz = N07_DXTZ;
    }
 
    /** 
     * 设置短信记录ID
     *
     * @param N08_DXJLID 短信记录ID
     */
    public void setN08_dxjlid(String N08_DXJLID) {
        this.n08_dxjlid = N08_DXJLID;
    }
 
    /** 
     * 设置审核时间
     *
     * @param N09 审核时间
     */
    public void setN09(String N09) {
        this.n09 = N09;
    }
 
    /** 
     * 设置字段10
     *
     * @param N10 字段10
     */
    public void setN10(String N10) {
        this.n10 = N10;
    }
 
    /** 
     * 设置患者姓名
     *
     * @param F01_HZXM 患者姓名
     */
    public void setF01_hzxm(String F01_HZXM) {
        this.f01_hzxm = F01_HZXM;
    }
 
    /** 
     * 设置患者姓名拼音码
     *
     * @param F02_HZXMPYM 患者姓名拼音码
     */
    public void setF02_hzxmpym(String F02_HZXMPYM) {
        this.f02_hzxmpym = F02_HZXMPYM;
    }
 
    /** 
     * 设置出生年月日
     *
     * @param F03_CSNYR 出生年月日
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
     * 设置性别
     *
     * @param F05 性别
     */
    public void setF05(String F05) {
        this.f05 = F05;
    }
 
    /** 
     * 设置字段6
     *
     * @param F06 字段6
     */
    public void setF06(String F06) {
        this.f06 = F06;
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
