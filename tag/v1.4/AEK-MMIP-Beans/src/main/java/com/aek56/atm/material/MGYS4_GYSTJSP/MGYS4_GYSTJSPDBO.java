package com.aek56.atm.material.MGYS4_GYSTJSP;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商添加商品*/
public class MGYS4_GYSTJSPDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String p11_gysid = "";
 
    /** 
     * 商品简称
     */
    private String n01_spjc = "";
 
    /** 
     * 规格简拼
     */
    private String n02_spjp = "";
 
    /** 
     * 产品编号
     */
    private String p01_cpbh = "";
 
    /** 
     * 商品条码（主码）
     */
    private String p02_tmzm = "";
 
    /** 
     * 产品类别（I\II\III）
     */
    private String p03_cplb123 = "";
 
    /** 
     * 产品唯一码
     */
    private String p04_cpwym = "";
 
    /** 
     * 品牌ID
     */
    private String k01_ppid = "";
 
    /** 
     * 全国总代理供应商ID
     */
    private String k02_qgzdlgysid = "";
 
    /** 
     * 厂商ID
     */
    private String k03_csid = "";
 
    /** 
     * 注册证ID
     */
    private String k04_zczid = "";
 
    /** 
     * 产品68码
     */
    private String k05_cp68m = "";
 
    /** 
     * 规格ID
     */
    private String k06_ggid = "";
 
    /** 
     * 产品线ID
     */
    private String k07_cpxid = "";
 
    /** 
     * 产品类别ID
     */
    private String k08_cplbid = "";
 
    /** 
     * 产品全称
     */
    private String f01_cpqc = "";
 
    /** 
     * 产品全称拼音码
     */
    private String f02_cpqcpym = "";
 
    /** 
     * 产品中文描述
     */
    private String f03_cpzwms = "";
 
    /** 
     * 产品英文描述
     */
    private String f04_cpywms = "";
 
    /** 
     * 品牌全称
     */
    private String f05_ppqc = "";
 
    /** 
     * 全国总代理供应商名称
     */
    private String f06_qgzdlgysmc = "";
 
    /** 
     * 注册证编号
     */
    private String f07_zczbh = "";
 
    /** 
     * 注册证名称
     */
    private String f08_zczmc = "";
 
    /** 
     * 注册证有效开始
     */
    private String f09_zczyxks = "";
 
    /** 
     * 注册证有效终结
     */
    private String f10_zczyxzj = "";
 
    /** 
     * 生产厂家名称
     */
    private String f11_sccjmc = "";
 
    /** 
     * 生产国家名称
     */
    private String f12_scgjmc = "";
 
    /** 
     * 生产地址
     */
    private String f13_scdz = "";
 
    /** 
     * 服务热线
     */
    private String f14_fwrx = "";
 
    /** 
     * 售后电话
     */
    private String f15_shdh = "";
 
    /** 
     * 规格名称
     */
    private String f16_ggmc = "";
 
    /** 
     * 产品线名称
     */
    private String f17_cpxmc = "";
 
    /** 
     * 产品属性
     */
    private String f18_cpsx = "";
 
    /** 
     * 包装内产品单位
     */
    private String f19_bzncpdw = "";
 
    /** 
     * 商品图片URL
     */
    private String f20_sptp = "";
 
    /** 
     * 灭菌方式
     */
    private String f21_mjfs = "";
 
    /** 
     * 灭菌状态
     */
    private String f22_mjzt = "";
 
    /** 
     * 包装数量
     */
    private String f23_bzsl = "";
 
    /** 
     * 包装单位
     */
    private String f24_bzdw = "";
 
    /** 
     * 当前使用规格数目
     */
    private String f25_dqsyggsm = "";
 
    /** 
     * 登记规格数目
     */
    private String f26_djggsm = "";
 
    /** 
     * 材料
     */
    private String f27_cl = "";
 
    /** 
     * 批发进价（商品）
     */
    private String f28_pfjj = "";
 
    /** 
     * 寄售进价（商品）
     */
    private String f29_jsjj = "";
 
    /** 
     * 市场售价（商品）
     */
    private String f30_scsj = "";
 
    /** 
     * 寄售售价（商品）
     */
    private String f31_jssj = "";
 
    /** 
     * 批发售价（商品）
     */
    private String f32_pfsj = "";
 
    /** 
     * 货币单位（商品）
     */
    private String f33_hbdw = "";
 
    /** 
     * 字段34
     */
    private String f34_byzd = "";
 
    /** 
     * 字段35
     */
    private String f35_byzd = "";
 
    /** 
     * 获取供应商ID
     *
     * @return P11_GYSID 供应商ID
     */
    public String getP11_gysid() {
        return this.p11_gysid;
    }
 
    /** 
     * 获取商品简称
     *
     * @return N01_SPJC 商品简称
     */
    public String getN01_spjc() {
        return this.n01_spjc;
    }
 
    /** 
     * 获取规格简拼
     *
     * @return N02_SPJP 规格简拼
     */
    public String getN02_spjp() {
        return this.n02_spjp;
    }
 
    /** 
     * 获取产品编号
     *
     * @return P01_CPBH 产品编号
     */
    public String getP01_cpbh() {
        return this.p01_cpbh;
    }
 
    /** 
     * 获取商品条码（主码）
     *
     * @return P02_TMZM 商品条码（主码）
     */
    public String getP02_tmzm() {
        return this.p02_tmzm;
    }
 
    /** 
     * 获取产品类别（I\II\III）
     *
     * @return P03_CPLB123 产品类别（I\II\III）
     */
    public String getP03_cplb123() {
        return this.p03_cplb123;
    }
 
    /** 
     * 获取产品唯一码
     *
     * @return P04_CPWYM 产品唯一码
     */
    public String getP04_cpwym() {
        return this.p04_cpwym;
    }
 
    /** 
     * 获取品牌ID
     *
     * @return K01_PPID 品牌ID
     */
    public String getK01_ppid() {
        return this.k01_ppid;
    }
 
    /** 
     * 获取全国总代理供应商ID
     *
     * @return K02_QGZDLGYSID 全国总代理供应商ID
     */
    public String getK02_qgzdlgysid() {
        return this.k02_qgzdlgysid;
    }
 
    /** 
     * 获取厂商ID
     *
     * @return K03_CSID 厂商ID
     */
    public String getK03_csid() {
        return this.k03_csid;
    }
 
    /** 
     * 获取注册证ID
     *
     * @return K04_ZCZID 注册证ID
     */
    public String getK04_zczid() {
        return this.k04_zczid;
    }
 
    /** 
     * 获取产品68码
     *
     * @return K05_CP68M 产品68码
     */
    public String getK05_cp68m() {
        return this.k05_cp68m;
    }
 
    /** 
     * 获取规格ID
     *
     * @return K06_GGID 规格ID
     */
    public String getK06_ggid() {
        return this.k06_ggid;
    }
 
    /** 
     * 获取产品线ID
     *
     * @return K07_CPXID 产品线ID
     */
    public String getK07_cpxid() {
        return this.k07_cpxid;
    }
 
    /** 
     * 获取产品类别ID
     *
     * @return K08_CPLBID 产品类别ID
     */
    public String getK08_cplbid() {
        return this.k08_cplbid;
    }
 
    /** 
     * 获取产品全称
     *
     * @return F01_CPQC 产品全称
     */
    public String getF01_cpqc() {
        return this.f01_cpqc;
    }
 
    /** 
     * 获取产品全称拼音码
     *
     * @return F02_CPQCPYM 产品全称拼音码
     */
    public String getF02_cpqcpym() {
        return this.f02_cpqcpym;
    }
 
    /** 
     * 获取产品中文描述
     *
     * @return F03_CPZWMS 产品中文描述
     */
    public String getF03_cpzwms() {
        return this.f03_cpzwms;
    }
 
    /** 
     * 获取产品英文描述
     *
     * @return F04_CPYWMS 产品英文描述
     */
    public String getF04_cpywms() {
        return this.f04_cpywms;
    }
 
    /** 
     * 获取品牌全称
     *
     * @return F05_PPQC 品牌全称
     */
    public String getF05_ppqc() {
        return this.f05_ppqc;
    }
 
    /** 
     * 获取全国总代理供应商名称
     *
     * @return F06_QGZDLGYSMC 全国总代理供应商名称
     */
    public String getF06_qgzdlgysmc() {
        return this.f06_qgzdlgysmc;
    }
 
    /** 
     * 获取注册证编号
     *
     * @return F07_ZCZBH 注册证编号
     */
    public String getF07_zczbh() {
        return this.f07_zczbh;
    }
 
    /** 
     * 获取注册证名称
     *
     * @return F08_ZCZMC 注册证名称
     */
    public String getF08_zczmc() {
        return this.f08_zczmc;
    }
 
    /** 
     * 获取注册证有效开始
     *
     * @return F09_ZCZYXKS 注册证有效开始
     */
    public String getF09_zczyxks() {
        return this.f09_zczyxks;
    }
 
    /** 
     * 获取注册证有效终结
     *
     * @return F10_ZCZYXZJ 注册证有效终结
     */
    public String getF10_zczyxzj() {
        return this.f10_zczyxzj;
    }
 
    /** 
     * 获取生产厂家名称
     *
     * @return F11_SCCJMC 生产厂家名称
     */
    public String getF11_sccjmc() {
        return this.f11_sccjmc;
    }
 
    /** 
     * 获取生产国家名称
     *
     * @return F12_SCGJMC 生产国家名称
     */
    public String getF12_scgjmc() {
        return this.f12_scgjmc;
    }
 
    /** 
     * 获取生产地址
     *
     * @return F13_SCDZ 生产地址
     */
    public String getF13_scdz() {
        return this.f13_scdz;
    }
 
    /** 
     * 获取服务热线
     *
     * @return F14_FWRX 服务热线
     */
    public String getF14_fwrx() {
        return this.f14_fwrx;
    }
 
    /** 
     * 获取售后电话
     *
     * @return F15_SHDH 售后电话
     */
    public String getF15_shdh() {
        return this.f15_shdh;
    }
 
    /** 
     * 获取规格名称
     *
     * @return F16_GGMC 规格名称
     */
    public String getF16_ggmc() {
        return this.f16_ggmc;
    }
 
    /** 
     * 获取产品线名称
     *
     * @return F17_CPXMC 产品线名称
     */
    public String getF17_cpxmc() {
        return this.f17_cpxmc;
    }
 
    /** 
     * 获取产品属性
     *
     * @return F18_CPSX 产品属性
     */
    public String getF18_cpsx() {
        return this.f18_cpsx;
    }
 
    /** 
     * 获取包装内产品单位
     *
     * @return F19_BZNCPDW 包装内产品单位
     */
    public String getF19_bzncpdw() {
        return this.f19_bzncpdw;
    }
 
    /** 
     * 获取商品图片URL
     *
     * @return F20_SPTP 商品图片URL
     */
    public String getF20_sptp() {
        return this.f20_sptp;
    }
 
    /** 
     * 获取灭菌方式
     *
     * @return F21_MJFS 灭菌方式
     */
    public String getF21_mjfs() {
        return this.f21_mjfs;
    }
 
    /** 
     * 获取灭菌状态
     *
     * @return F22_MJZT 灭菌状态
     */
    public String getF22_mjzt() {
        return this.f22_mjzt;
    }
 
    /** 
     * 获取包装数量
     *
     * @return F23_BZSL 包装数量
     */
    public String getF23_bzsl() {
        return this.f23_bzsl;
    }
 
    /** 
     * 获取包装单位
     *
     * @return F24_BZDW 包装单位
     */
    public String getF24_bzdw() {
        return this.f24_bzdw;
    }
 
    /** 
     * 获取当前使用规格数目
     *
     * @return F25_DQSYGGSM 当前使用规格数目
     */
    public String getF25_dqsyggsm() {
        return this.f25_dqsyggsm;
    }
 
    /** 
     * 获取登记规格数目
     *
     * @return F26_DJGGSM 登记规格数目
     */
    public String getF26_djggsm() {
        return this.f26_djggsm;
    }
 
    /** 
     * 获取材料
     *
     * @return F27_CL 材料
     */
    public String getF27_cl() {
        return this.f27_cl;
    }
 
    /** 
     * 获取批发进价（商品）
     *
     * @return F28_PFJJ 批发进价（商品）
     */
    public String getF28_pfjj() {
        return this.f28_pfjj;
    }
 
    /** 
     * 获取寄售进价（商品）
     *
     * @return F29_JSJJ 寄售进价（商品）
     */
    public String getF29_jsjj() {
        return this.f29_jsjj;
    }
 
    /** 
     * 获取市场售价（商品）
     *
     * @return F30_SCSJ 市场售价（商品）
     */
    public String getF30_scsj() {
        return this.f30_scsj;
    }
 
    /** 
     * 获取寄售售价（商品）
     *
     * @return F31_JSSJ 寄售售价（商品）
     */
    public String getF31_jssj() {
        return this.f31_jssj;
    }
 
    /** 
     * 获取批发售价（商品）
     *
     * @return F32_PFSJ 批发售价（商品）
     */
    public String getF32_pfsj() {
        return this.f32_pfsj;
    }
 
    /** 
     * 获取货币单位（商品）
     *
     * @return F33_HBDW 货币单位（商品）
     */
    public String getF33_hbdw() {
        return this.f33_hbdw;
    }
 
    /** 
     * 获取字段34
     *
     * @return F34_BYZD 字段34
     */
    public String getF34_byzd() {
        return this.f34_byzd;
    }
 
    /** 
     * 获取字段35
     *
     * @return F35_BYZD 字段35
     */
    public String getF35_byzd() {
        return this.f35_byzd;
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
     * 设置商品简称
     *
     * @param N01_SPJC 商品简称
     */
    public void setN01_spjc(String N01_SPJC) {
        this.n01_spjc = N01_SPJC;
    }
 
    /** 
     * 设置规格简拼
     *
     * @param N02_SPJP 规格简拼
     */
    public void setN02_spjp(String N02_SPJP) {
        this.n02_spjp = N02_SPJP;
    }
 
    /** 
     * 设置产品编号
     *
     * @param P01_CPBH 产品编号
     */
    public void setP01_cpbh(String P01_CPBH) {
        this.p01_cpbh = P01_CPBH;
    }
 
    /** 
     * 设置商品条码（主码）
     *
     * @param P02_TMZM 商品条码（主码）
     */
    public void setP02_tmzm(String P02_TMZM) {
        this.p02_tmzm = P02_TMZM;
    }
 
    /** 
     * 设置产品类别（I\II\III）
     *
     * @param P03_CPLB123 产品类别（I\II\III）
     */
    public void setP03_cplb123(String P03_CPLB123) {
        this.p03_cplb123 = P03_CPLB123;
    }
 
    /** 
     * 设置产品唯一码
     *
     * @param P04_CPWYM 产品唯一码
     */
    public void setP04_cpwym(String P04_CPWYM) {
        this.p04_cpwym = P04_CPWYM;
    }
 
    /** 
     * 设置品牌ID
     *
     * @param K01_PPID 品牌ID
     */
    public void setK01_ppid(String K01_PPID) {
        this.k01_ppid = K01_PPID;
    }
 
    /** 
     * 设置全国总代理供应商ID
     *
     * @param K02_QGZDLGYSID 全国总代理供应商ID
     */
    public void setK02_qgzdlgysid(String K02_QGZDLGYSID) {
        this.k02_qgzdlgysid = K02_QGZDLGYSID;
    }
 
    /** 
     * 设置厂商ID
     *
     * @param K03_CSID 厂商ID
     */
    public void setK03_csid(String K03_CSID) {
        this.k03_csid = K03_CSID;
    }
 
    /** 
     * 设置注册证ID
     *
     * @param K04_ZCZID 注册证ID
     */
    public void setK04_zczid(String K04_ZCZID) {
        this.k04_zczid = K04_ZCZID;
    }
 
    /** 
     * 设置产品68码
     *
     * @param K05_CP68M 产品68码
     */
    public void setK05_cp68m(String K05_CP68M) {
        this.k05_cp68m = K05_CP68M;
    }
 
    /** 
     * 设置规格ID
     *
     * @param K06_GGID 规格ID
     */
    public void setK06_ggid(String K06_GGID) {
        this.k06_ggid = K06_GGID;
    }
 
    /** 
     * 设置产品线ID
     *
     * @param K07_CPXID 产品线ID
     */
    public void setK07_cpxid(String K07_CPXID) {
        this.k07_cpxid = K07_CPXID;
    }
 
    /** 
     * 设置产品类别ID
     *
     * @param K08_CPLBID 产品类别ID
     */
    public void setK08_cplbid(String K08_CPLBID) {
        this.k08_cplbid = K08_CPLBID;
    }
 
    /** 
     * 设置产品全称
     *
     * @param F01_CPQC 产品全称
     */
    public void setF01_cpqc(String F01_CPQC) {
        this.f01_cpqc = F01_CPQC;
    }
 
    /** 
     * 设置产品全称拼音码
     *
     * @param F02_CPQCPYM 产品全称拼音码
     */
    public void setF02_cpqcpym(String F02_CPQCPYM) {
        this.f02_cpqcpym = F02_CPQCPYM;
    }
 
    /** 
     * 设置产品中文描述
     *
     * @param F03_CPZWMS 产品中文描述
     */
    public void setF03_cpzwms(String F03_CPZWMS) {
        this.f03_cpzwms = F03_CPZWMS;
    }
 
    /** 
     * 设置产品英文描述
     *
     * @param F04_CPYWMS 产品英文描述
     */
    public void setF04_cpywms(String F04_CPYWMS) {
        this.f04_cpywms = F04_CPYWMS;
    }
 
    /** 
     * 设置品牌全称
     *
     * @param F05_PPQC 品牌全称
     */
    public void setF05_ppqc(String F05_PPQC) {
        this.f05_ppqc = F05_PPQC;
    }
 
    /** 
     * 设置全国总代理供应商名称
     *
     * @param F06_QGZDLGYSMC 全国总代理供应商名称
     */
    public void setF06_qgzdlgysmc(String F06_QGZDLGYSMC) {
        this.f06_qgzdlgysmc = F06_QGZDLGYSMC;
    }
 
    /** 
     * 设置注册证编号
     *
     * @param F07_ZCZBH 注册证编号
     */
    public void setF07_zczbh(String F07_ZCZBH) {
        this.f07_zczbh = F07_ZCZBH;
    }
 
    /** 
     * 设置注册证名称
     *
     * @param F08_ZCZMC 注册证名称
     */
    public void setF08_zczmc(String F08_ZCZMC) {
        this.f08_zczmc = F08_ZCZMC;
    }
 
    /** 
     * 设置注册证有效开始
     *
     * @param F09_ZCZYXKS 注册证有效开始
     */
    public void setF09_zczyxks(String F09_ZCZYXKS) {
        this.f09_zczyxks = F09_ZCZYXKS;
    }
 
    /** 
     * 设置注册证有效终结
     *
     * @param F10_ZCZYXZJ 注册证有效终结
     */
    public void setF10_zczyxzj(String F10_ZCZYXZJ) {
        this.f10_zczyxzj = F10_ZCZYXZJ;
    }
 
    /** 
     * 设置生产厂家名称
     *
     * @param F11_SCCJMC 生产厂家名称
     */
    public void setF11_sccjmc(String F11_SCCJMC) {
        this.f11_sccjmc = F11_SCCJMC;
    }
 
    /** 
     * 设置生产国家名称
     *
     * @param F12_SCGJMC 生产国家名称
     */
    public void setF12_scgjmc(String F12_SCGJMC) {
        this.f12_scgjmc = F12_SCGJMC;
    }
 
    /** 
     * 设置生产地址
     *
     * @param F13_SCDZ 生产地址
     */
    public void setF13_scdz(String F13_SCDZ) {
        this.f13_scdz = F13_SCDZ;
    }
 
    /** 
     * 设置服务热线
     *
     * @param F14_FWRX 服务热线
     */
    public void setF14_fwrx(String F14_FWRX) {
        this.f14_fwrx = F14_FWRX;
    }
 
    /** 
     * 设置售后电话
     *
     * @param F15_SHDH 售后电话
     */
    public void setF15_shdh(String F15_SHDH) {
        this.f15_shdh = F15_SHDH;
    }
 
    /** 
     * 设置规格名称
     *
     * @param F16_GGMC 规格名称
     */
    public void setF16_ggmc(String F16_GGMC) {
        this.f16_ggmc = F16_GGMC;
    }
 
    /** 
     * 设置产品线名称
     *
     * @param F17_CPXMC 产品线名称
     */
    public void setF17_cpxmc(String F17_CPXMC) {
        this.f17_cpxmc = F17_CPXMC;
    }
 
    /** 
     * 设置产品属性
     *
     * @param F18_CPSX 产品属性
     */
    public void setF18_cpsx(String F18_CPSX) {
        this.f18_cpsx = F18_CPSX;
    }
 
    /** 
     * 设置包装内产品单位
     *
     * @param F19_BZNCPDW 包装内产品单位
     */
    public void setF19_bzncpdw(String F19_BZNCPDW) {
        this.f19_bzncpdw = F19_BZNCPDW;
    }
 
    /** 
     * 设置商品图片URL
     *
     * @param F20_SPTP 商品图片URL
     */
    public void setF20_sptp(String F20_SPTP) {
        this.f20_sptp = F20_SPTP;
    }
 
    /** 
     * 设置灭菌方式
     *
     * @param F21_MJFS 灭菌方式
     */
    public void setF21_mjfs(String F21_MJFS) {
        this.f21_mjfs = F21_MJFS;
    }
 
    /** 
     * 设置灭菌状态
     *
     * @param F22_MJZT 灭菌状态
     */
    public void setF22_mjzt(String F22_MJZT) {
        this.f22_mjzt = F22_MJZT;
    }
 
    /** 
     * 设置包装数量
     *
     * @param F23_BZSL 包装数量
     */
    public void setF23_bzsl(String F23_BZSL) {
        this.f23_bzsl = F23_BZSL;
    }
 
    /** 
     * 设置包装单位
     *
     * @param F24_BZDW 包装单位
     */
    public void setF24_bzdw(String F24_BZDW) {
        this.f24_bzdw = F24_BZDW;
    }
 
    /** 
     * 设置当前使用规格数目
     *
     * @param F25_DQSYGGSM 当前使用规格数目
     */
    public void setF25_dqsyggsm(String F25_DQSYGGSM) {
        this.f25_dqsyggsm = F25_DQSYGGSM;
    }
 
    /** 
     * 设置登记规格数目
     *
     * @param F26_DJGGSM 登记规格数目
     */
    public void setF26_djggsm(String F26_DJGGSM) {
        this.f26_djggsm = F26_DJGGSM;
    }
 
    /** 
     * 设置材料
     *
     * @param F27_CL 材料
     */
    public void setF27_cl(String F27_CL) {
        this.f27_cl = F27_CL;
    }
 
    /** 
     * 设置批发进价（商品）
     *
     * @param F28_PFJJ 批发进价（商品）
     */
    public void setF28_pfjj(String F28_PFJJ) {
        this.f28_pfjj = F28_PFJJ;
    }
 
    /** 
     * 设置寄售进价（商品）
     *
     * @param F29_JSJJ 寄售进价（商品）
     */
    public void setF29_jsjj(String F29_JSJJ) {
        this.f29_jsjj = F29_JSJJ;
    }
 
    /** 
     * 设置市场售价（商品）
     *
     * @param F30_SCSJ 市场售价（商品）
     */
    public void setF30_scsj(String F30_SCSJ) {
        this.f30_scsj = F30_SCSJ;
    }
 
    /** 
     * 设置寄售售价（商品）
     *
     * @param F31_JSSJ 寄售售价（商品）
     */
    public void setF31_jssj(String F31_JSSJ) {
        this.f31_jssj = F31_JSSJ;
    }
 
    /** 
     * 设置批发售价（商品）
     *
     * @param F32_PFSJ 批发售价（商品）
     */
    public void setF32_pfsj(String F32_PFSJ) {
        this.f32_pfsj = F32_PFSJ;
    }
 
    /** 
     * 设置货币单位（商品）
     *
     * @param F33_HBDW 货币单位（商品）
     */
    public void setF33_hbdw(String F33_HBDW) {
        this.f33_hbdw = F33_HBDW;
    }
 
    /** 
     * 设置字段34
     *
     * @param F34_BYZD 字段34
     */
    public void setF34_byzd(String F34_BYZD) {
        this.f34_byzd = F34_BYZD;
    }
 
    /** 
     * 设置字段35
     *
     * @param F35_BYZD 字段35
     */
    public void setF35_byzd(String F35_BYZD) {
        this.f35_byzd = F35_BYZD;
    }
 
}
