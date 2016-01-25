package com.aek56.atm.auditing.CSMA_PPXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 品牌信息(审核)*/
public class CSMA_PPXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 品牌ID
     */
    private String p01_puk = "";
 
    /** 
     * 数据类别
     */
    private String p02_sjlb = "";
 
    /** 
     * 厂商ID
     */
    private String k01_csid = "";
 
    /** 
     * 主键2
     */
    private String k02 = "";
 
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
     * 品牌全称
     */
    private String f01_ppqc = "";
 
    /** 
     * 品牌全称拼音码
     */
    private String f02_ppqcpym = "";
 
    /** 
     * 品牌种类
     */
    private String f03_ppzl = "";
 
    /** 
     * 品牌知名度
     */
    private String f04_ppzmd = "";
 
    /** 
     * 品牌所属环节
     */
    private String f05_ppsshj = "";
 
    /** 
     * 品牌来源
     */
    private String f06_pply = "";
 
    /** 
     * 品牌生命周期
     */
    private String f07_ppsmzq = "";
 
    /** 
     * 品牌内外销
     */
    private String f08_ppnwx = "";
 
    /** 
     * 品牌所属行业
     */
    private String f09_ppssxy = "";
 
    /** 
     * 品牌负责人
     */
    private String f10_ppfzr = "";
 
    /** 
     * 售后服务热线
     */
    private String f11_shfwrx = "";
 
    /** 
     * 所属国家
     */
    private String f12_ssgj = "";
 
    /** 
     * 品牌LOGO
     */
    private String f13_logo_url = "";
 
    /** 
     * 厂商全名
     */
    private String f14_csqm = "";
 
    /** 
     * 厂商全名拼音码
     */
    private String f15_csqmpym = "";
 
    /** 
     * 厂家地址
     */
    private String f16 = "";
 
    /** 
     * 厂家负责人
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
     * 获取品牌ID
     *
     * @return P01_PUK 品牌ID
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
     * 获取厂商ID
     *
     * @return K01_CSID 厂商ID
     */
    public String getK01_csid() {
        return this.k01_csid;
    }
 
    /** 
     * 获取主键2
     *
     * @return K02 主键2
     */
    public String getK02() {
        return this.k02;
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
     * 获取品牌全称
     *
     * @return F01_PPQC 品牌全称
     */
    public String getF01_ppqc() {
        return this.f01_ppqc;
    }
 
    /** 
     * 获取品牌全称拼音码
     *
     * @return F02_PPQCPYM 品牌全称拼音码
     */
    public String getF02_ppqcpym() {
        return this.f02_ppqcpym;
    }
 
    /** 
     * 获取品牌种类
     *
     * @return F03_PPZL 品牌种类
     */
    public String getF03_ppzl() {
        return this.f03_ppzl;
    }
 
    /** 
     * 获取品牌知名度
     *
     * @return F04_PPZMD 品牌知名度
     */
    public String getF04_ppzmd() {
        return this.f04_ppzmd;
    }
 
    /** 
     * 获取品牌所属环节
     *
     * @return F05_PPSSHJ 品牌所属环节
     */
    public String getF05_ppsshj() {
        return this.f05_ppsshj;
    }
 
    /** 
     * 获取品牌来源
     *
     * @return F06_PPLY 品牌来源
     */
    public String getF06_pply() {
        return this.f06_pply;
    }
 
    /** 
     * 获取品牌生命周期
     *
     * @return F07_PPSMZQ 品牌生命周期
     */
    public String getF07_ppsmzq() {
        return this.f07_ppsmzq;
    }
 
    /** 
     * 获取品牌内外销
     *
     * @return F08_PPNWX 品牌内外销
     */
    public String getF08_ppnwx() {
        return this.f08_ppnwx;
    }
 
    /** 
     * 获取品牌所属行业
     *
     * @return F09_PPSSXY 品牌所属行业
     */
    public String getF09_ppssxy() {
        return this.f09_ppssxy;
    }
 
    /** 
     * 获取品牌负责人
     *
     * @return F10_PPFZR 品牌负责人
     */
    public String getF10_ppfzr() {
        return this.f10_ppfzr;
    }
 
    /** 
     * 获取售后服务热线
     *
     * @return F11_SHFWRX 售后服务热线
     */
    public String getF11_shfwrx() {
        return this.f11_shfwrx;
    }
 
    /** 
     * 获取所属国家
     *
     * @return F12_SSGJ 所属国家
     */
    public String getF12_ssgj() {
        return this.f12_ssgj;
    }
 
    /** 
     * 获取品牌LOGO
     *
     * @return F13_LOGO_URL 品牌LOGO
     */
    public String getF13_logo_url() {
        return this.f13_logo_url;
    }
 
    /** 
     * 获取厂商全名
     *
     * @return F14_CSQM 厂商全名
     */
    public String getF14_csqm() {
        return this.f14_csqm;
    }
 
    /** 
     * 获取厂商全名拼音码
     *
     * @return F15_CSQMPYM 厂商全名拼音码
     */
    public String getF15_csqmpym() {
        return this.f15_csqmpym;
    }
 
    /** 
     * 获取厂家地址
     *
     * @return F16 厂家地址
     */
    public String getF16() {
        return this.f16;
    }
 
    /** 
     * 获取厂家负责人
     *
     * @return F17 厂家负责人
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
     * 设置品牌ID
     *
     * @param P01_PUK 品牌ID
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
     * 设置厂商ID
     *
     * @param K01_CSID 厂商ID
     */
    public void setK01_csid(String K01_CSID) {
        this.k01_csid = K01_CSID;
    }
 
    /** 
     * 设置主键2
     *
     * @param K02 主键2
     */
    public void setK02(String K02) {
        this.k02 = K02;
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
     * 设置品牌全称
     *
     * @param F01_PPQC 品牌全称
     */
    public void setF01_ppqc(String F01_PPQC) {
        this.f01_ppqc = F01_PPQC;
    }
 
    /** 
     * 设置品牌全称拼音码
     *
     * @param F02_PPQCPYM 品牌全称拼音码
     */
    public void setF02_ppqcpym(String F02_PPQCPYM) {
        this.f02_ppqcpym = F02_PPQCPYM;
    }
 
    /** 
     * 设置品牌种类
     *
     * @param F03_PPZL 品牌种类
     */
    public void setF03_ppzl(String F03_PPZL) {
        this.f03_ppzl = F03_PPZL;
    }
 
    /** 
     * 设置品牌知名度
     *
     * @param F04_PPZMD 品牌知名度
     */
    public void setF04_ppzmd(String F04_PPZMD) {
        this.f04_ppzmd = F04_PPZMD;
    }
 
    /** 
     * 设置品牌所属环节
     *
     * @param F05_PPSSHJ 品牌所属环节
     */
    public void setF05_ppsshj(String F05_PPSSHJ) {
        this.f05_ppsshj = F05_PPSSHJ;
    }
 
    /** 
     * 设置品牌来源
     *
     * @param F06_PPLY 品牌来源
     */
    public void setF06_pply(String F06_PPLY) {
        this.f06_pply = F06_PPLY;
    }
 
    /** 
     * 设置品牌生命周期
     *
     * @param F07_PPSMZQ 品牌生命周期
     */
    public void setF07_ppsmzq(String F07_PPSMZQ) {
        this.f07_ppsmzq = F07_PPSMZQ;
    }
 
    /** 
     * 设置品牌内外销
     *
     * @param F08_PPNWX 品牌内外销
     */
    public void setF08_ppnwx(String F08_PPNWX) {
        this.f08_ppnwx = F08_PPNWX;
    }
 
    /** 
     * 设置品牌所属行业
     *
     * @param F09_PPSSXY 品牌所属行业
     */
    public void setF09_ppssxy(String F09_PPSSXY) {
        this.f09_ppssxy = F09_PPSSXY;
    }
 
    /** 
     * 设置品牌负责人
     *
     * @param F10_PPFZR 品牌负责人
     */
    public void setF10_ppfzr(String F10_PPFZR) {
        this.f10_ppfzr = F10_PPFZR;
    }
 
    /** 
     * 设置售后服务热线
     *
     * @param F11_SHFWRX 售后服务热线
     */
    public void setF11_shfwrx(String F11_SHFWRX) {
        this.f11_shfwrx = F11_SHFWRX;
    }
 
    /** 
     * 设置所属国家
     *
     * @param F12_SSGJ 所属国家
     */
    public void setF12_ssgj(String F12_SSGJ) {
        this.f12_ssgj = F12_SSGJ;
    }
 
    /** 
     * 设置品牌LOGO
     *
     * @param F13_LOGO_URL 品牌LOGO
     */
    public void setF13_logo_url(String F13_LOGO_URL) {
        this.f13_logo_url = F13_LOGO_URL;
    }
 
    /** 
     * 设置厂商全名
     *
     * @param F14_CSQM 厂商全名
     */
    public void setF14_csqm(String F14_CSQM) {
        this.f14_csqm = F14_CSQM;
    }
 
    /** 
     * 设置厂商全名拼音码
     *
     * @param F15_CSQMPYM 厂商全名拼音码
     */
    public void setF15_csqmpym(String F15_CSQMPYM) {
        this.f15_csqmpym = F15_CSQMPYM;
    }
 
    /** 
     * 设置厂家地址
     *
     * @param F16 厂家地址
     */
    public void setF16(String F16) {
        this.f16 = F16;
    }
 
    /** 
     * 设置厂家负责人
     *
     * @param F17 厂家负责人
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
