package com.aek56.atm.material.MGYS7_GYSTJZCZGGXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商添加商品证规格信息*/
public class MGYS7_GYSTJZCZGGXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 产品ID
     */
    private String p01_cpid = "";
 
    /** 
     * 供应商ID
     */
    private String p02_gysid = "";
 
    /** 
     * 规格简称
     */
    private String n01_ggjc = "";
 
    /** 
     * 规格简拼
     */
    private String n02_ggjp = "";
 
    /** 
     * 注册证ID（业务主键）
     */
    private String k01_zczid = "";
 
    /** 
     * 证件唯一码（数据ID）
     */
    private String k02_zjwym = "";
 
    /** 
     * 规格条码
     */
    private String k03_ggtm = "";
 
    /** 
     * 规格全称
     */
    private String f01_ggqc = "";
 
    /** 
     * 规格全称拼音码
     */
    private String f02_ggqcpym = "";
 
    /** 
     * 产品主编号
     */
    private String f03_cpzbh = "";
 
    /** 
     * 产品次编号
     */
    private String f04_cpcbh = "";
 
    /** 
     * 产品型号
     */
    private String f05_cpxh = "";
 
    /** 
     * 中文规格描述
     */
    private String f06_zwggms = "";
 
    /** 
     * 英文规格描述
     */
    private String f07_ywggms = "";
 
    /** 
     * 材料
     */
    private String f08_cl = "";
 
    /** 
     * 颜色
     */
    private String f09_ys = "";
 
    /** 
     * 误差
     */
    private String f10_wc = "";
 
    /** 
     * 计量单位
     */
    private String f11_jldw = "";
 
    /** 
     * 长度
     */
    private String f12_cd = "";
 
    /** 
     * 宽度
     */
    private String f13_kd = "";
 
    /** 
     * 高度
     */
    private String f14_gd = "";
 
    /** 
     * 厚度
     */
    private String f15_hd = "";
 
    /** 
     * 内直径
     */
    private String f16_nzj = "";
 
    /** 
     * 外直径
     */
    private String f17_wzj = "";
 
    /** 
     * 孔数
     */
    private String f18_ks = "";
 
    /** 
     * 重量
     */
    private String f19_zl = "";
 
    /** 
     * 形状
     */
    private String f20_xz = "";
 
    /** 
     * 使用患处
     */
    private String f21_syhc = "";
 
    /** 
     * 适用范围
     */
    private String f22_syfw = "";
 
    /** 
     * （备用字段）1
     */
    private String f23 = "";
 
    /** 
     * （备用字段）2
     */
    private String f24 = "";
 
    /** 
     * （备用字段）3
     */
    private String f25 = "";
 
    /** 
     * 获取产品ID
     *
     * @return P01_CPID 产品ID
     */
    public String getP01_cpid() {
        return this.p01_cpid;
    }
 
    /** 
     * 获取供应商ID
     *
     * @return P02_GYSID 供应商ID
     */
    public String getP02_gysid() {
        return this.p02_gysid;
    }
 
    /** 
     * 获取规格简称
     *
     * @return N01_GGJC 规格简称
     */
    public String getN01_ggjc() {
        return this.n01_ggjc;
    }
 
    /** 
     * 获取规格简拼
     *
     * @return N02_GGJP 规格简拼
     */
    public String getN02_ggjp() {
        return this.n02_ggjp;
    }
 
    /** 
     * 获取注册证ID（业务主键）
     *
     * @return K01_ZCZID 注册证ID（业务主键）
     */
    public String getK01_zczid() {
        return this.k01_zczid;
    }
 
    /** 
     * 获取证件唯一码（数据ID）
     *
     * @return K02_ZJWYM 证件唯一码（数据ID）
     */
    public String getK02_zjwym() {
        return this.k02_zjwym;
    }
 
    /** 
     * 获取规格条码
     *
     * @return K03_GGTM 规格条码
     */
    public String getK03_ggtm() {
        return this.k03_ggtm;
    }
 
    /** 
     * 获取规格全称
     *
     * @return F01_GGQC 规格全称
     */
    public String getF01_ggqc() {
        return this.f01_ggqc;
    }
 
    /** 
     * 获取规格全称拼音码
     *
     * @return F02_GGQCPYM 规格全称拼音码
     */
    public String getF02_ggqcpym() {
        return this.f02_ggqcpym;
    }
 
    /** 
     * 获取产品主编号
     *
     * @return F03_CPZBH 产品主编号
     */
    public String getF03_cpzbh() {
        return this.f03_cpzbh;
    }
 
    /** 
     * 获取产品次编号
     *
     * @return F04_CPCBH 产品次编号
     */
    public String getF04_cpcbh() {
        return this.f04_cpcbh;
    }
 
    /** 
     * 获取产品型号
     *
     * @return F05_CPXH 产品型号
     */
    public String getF05_cpxh() {
        return this.f05_cpxh;
    }
 
    /** 
     * 获取中文规格描述
     *
     * @return F06_ZWGGMS 中文规格描述
     */
    public String getF06_zwggms() {
        return this.f06_zwggms;
    }
 
    /** 
     * 获取英文规格描述
     *
     * @return F07_YWGGMS 英文规格描述
     */
    public String getF07_ywggms() {
        return this.f07_ywggms;
    }
 
    /** 
     * 获取材料
     *
     * @return F08_CL 材料
     */
    public String getF08_cl() {
        return this.f08_cl;
    }
 
    /** 
     * 获取颜色
     *
     * @return F09_YS 颜色
     */
    public String getF09_ys() {
        return this.f09_ys;
    }
 
    /** 
     * 获取误差
     *
     * @return F10_WC 误差
     */
    public String getF10_wc() {
        return this.f10_wc;
    }
 
    /** 
     * 获取计量单位
     *
     * @return F11_JLDW 计量单位
     */
    public String getF11_jldw() {
        return this.f11_jldw;
    }
 
    /** 
     * 获取长度
     *
     * @return F12_CD 长度
     */
    public String getF12_cd() {
        return this.f12_cd;
    }
 
    /** 
     * 获取宽度
     *
     * @return F13_KD 宽度
     */
    public String getF13_kd() {
        return this.f13_kd;
    }
 
    /** 
     * 获取高度
     *
     * @return F14_GD 高度
     */
    public String getF14_gd() {
        return this.f14_gd;
    }
 
    /** 
     * 获取厚度
     *
     * @return F15_HD 厚度
     */
    public String getF15_hd() {
        return this.f15_hd;
    }
 
    /** 
     * 获取内直径
     *
     * @return F16_NZJ 内直径
     */
    public String getF16_nzj() {
        return this.f16_nzj;
    }
 
    /** 
     * 获取外直径
     *
     * @return F17_WZJ 外直径
     */
    public String getF17_wzj() {
        return this.f17_wzj;
    }
 
    /** 
     * 获取孔数
     *
     * @return F18_KS 孔数
     */
    public String getF18_ks() {
        return this.f18_ks;
    }
 
    /** 
     * 获取重量
     *
     * @return F19_ZL 重量
     */
    public String getF19_zl() {
        return this.f19_zl;
    }
 
    /** 
     * 获取形状
     *
     * @return F20_XZ 形状
     */
    public String getF20_xz() {
        return this.f20_xz;
    }
 
    /** 
     * 获取使用患处
     *
     * @return F21_SYHC 使用患处
     */
    public String getF21_syhc() {
        return this.f21_syhc;
    }
 
    /** 
     * 获取适用范围
     *
     * @return F22_SYFW 适用范围
     */
    public String getF22_syfw() {
        return this.f22_syfw;
    }
 
    /** 
     * 获取（备用字段）1
     *
     * @return F23 （备用字段）1
     */
    public String getF23() {
        return this.f23;
    }
 
    /** 
     * 获取（备用字段）2
     *
     * @return F24 （备用字段）2
     */
    public String getF24() {
        return this.f24;
    }
 
    /** 
     * 获取（备用字段）3
     *
     * @return F25 （备用字段）3
     */
    public String getF25() {
        return this.f25;
    }
 
    /** 
     * 设置产品ID
     *
     * @param P01_CPID 产品ID
     */
    public void setP01_cpid(String P01_CPID) {
        this.p01_cpid = P01_CPID;
    }
 
    /** 
     * 设置供应商ID
     *
     * @param P02_GYSID 供应商ID
     */
    public void setP02_gysid(String P02_GYSID) {
        this.p02_gysid = P02_GYSID;
    }
 
    /** 
     * 设置规格简称
     *
     * @param N01_GGJC 规格简称
     */
    public void setN01_ggjc(String N01_GGJC) {
        this.n01_ggjc = N01_GGJC;
    }
 
    /** 
     * 设置规格简拼
     *
     * @param N02_GGJP 规格简拼
     */
    public void setN02_ggjp(String N02_GGJP) {
        this.n02_ggjp = N02_GGJP;
    }
 
    /** 
     * 设置注册证ID（业务主键）
     *
     * @param K01_ZCZID 注册证ID（业务主键）
     */
    public void setK01_zczid(String K01_ZCZID) {
        this.k01_zczid = K01_ZCZID;
    }
 
    /** 
     * 设置证件唯一码（数据ID）
     *
     * @param K02_ZJWYM 证件唯一码（数据ID）
     */
    public void setK02_zjwym(String K02_ZJWYM) {
        this.k02_zjwym = K02_ZJWYM;
    }
 
    /** 
     * 设置规格条码
     *
     * @param K03_GGTM 规格条码
     */
    public void setK03_ggtm(String K03_GGTM) {
        this.k03_ggtm = K03_GGTM;
    }
 
    /** 
     * 设置规格全称
     *
     * @param F01_GGQC 规格全称
     */
    public void setF01_ggqc(String F01_GGQC) {
        this.f01_ggqc = F01_GGQC;
    }
 
    /** 
     * 设置规格全称拼音码
     *
     * @param F02_GGQCPYM 规格全称拼音码
     */
    public void setF02_ggqcpym(String F02_GGQCPYM) {
        this.f02_ggqcpym = F02_GGQCPYM;
    }
 
    /** 
     * 设置产品主编号
     *
     * @param F03_CPZBH 产品主编号
     */
    public void setF03_cpzbh(String F03_CPZBH) {
        this.f03_cpzbh = F03_CPZBH;
    }
 
    /** 
     * 设置产品次编号
     *
     * @param F04_CPCBH 产品次编号
     */
    public void setF04_cpcbh(String F04_CPCBH) {
        this.f04_cpcbh = F04_CPCBH;
    }
 
    /** 
     * 设置产品型号
     *
     * @param F05_CPXH 产品型号
     */
    public void setF05_cpxh(String F05_CPXH) {
        this.f05_cpxh = F05_CPXH;
    }
 
    /** 
     * 设置中文规格描述
     *
     * @param F06_ZWGGMS 中文规格描述
     */
    public void setF06_zwggms(String F06_ZWGGMS) {
        this.f06_zwggms = F06_ZWGGMS;
    }
 
    /** 
     * 设置英文规格描述
     *
     * @param F07_YWGGMS 英文规格描述
     */
    public void setF07_ywggms(String F07_YWGGMS) {
        this.f07_ywggms = F07_YWGGMS;
    }
 
    /** 
     * 设置材料
     *
     * @param F08_CL 材料
     */
    public void setF08_cl(String F08_CL) {
        this.f08_cl = F08_CL;
    }
 
    /** 
     * 设置颜色
     *
     * @param F09_YS 颜色
     */
    public void setF09_ys(String F09_YS) {
        this.f09_ys = F09_YS;
    }
 
    /** 
     * 设置误差
     *
     * @param F10_WC 误差
     */
    public void setF10_wc(String F10_WC) {
        this.f10_wc = F10_WC;
    }
 
    /** 
     * 设置计量单位
     *
     * @param F11_JLDW 计量单位
     */
    public void setF11_jldw(String F11_JLDW) {
        this.f11_jldw = F11_JLDW;
    }
 
    /** 
     * 设置长度
     *
     * @param F12_CD 长度
     */
    public void setF12_cd(String F12_CD) {
        this.f12_cd = F12_CD;
    }
 
    /** 
     * 设置宽度
     *
     * @param F13_KD 宽度
     */
    public void setF13_kd(String F13_KD) {
        this.f13_kd = F13_KD;
    }
 
    /** 
     * 设置高度
     *
     * @param F14_GD 高度
     */
    public void setF14_gd(String F14_GD) {
        this.f14_gd = F14_GD;
    }
 
    /** 
     * 设置厚度
     *
     * @param F15_HD 厚度
     */
    public void setF15_hd(String F15_HD) {
        this.f15_hd = F15_HD;
    }
 
    /** 
     * 设置内直径
     *
     * @param F16_NZJ 内直径
     */
    public void setF16_nzj(String F16_NZJ) {
        this.f16_nzj = F16_NZJ;
    }
 
    /** 
     * 设置外直径
     *
     * @param F17_WZJ 外直径
     */
    public void setF17_wzj(String F17_WZJ) {
        this.f17_wzj = F17_WZJ;
    }
 
    /** 
     * 设置孔数
     *
     * @param F18_KS 孔数
     */
    public void setF18_ks(String F18_KS) {
        this.f18_ks = F18_KS;
    }
 
    /** 
     * 设置重量
     *
     * @param F19_ZL 重量
     */
    public void setF19_zl(String F19_ZL) {
        this.f19_zl = F19_ZL;
    }
 
    /** 
     * 设置形状
     *
     * @param F20_XZ 形状
     */
    public void setF20_xz(String F20_XZ) {
        this.f20_xz = F20_XZ;
    }
 
    /** 
     * 设置使用患处
     *
     * @param F21_SYHC 使用患处
     */
    public void setF21_syhc(String F21_SYHC) {
        this.f21_syhc = F21_SYHC;
    }
 
    /** 
     * 设置适用范围
     *
     * @param F22_SYFW 适用范围
     */
    public void setF22_syfw(String F22_SYFW) {
        this.f22_syfw = F22_SYFW;
    }
 
    /** 
     * 设置（备用字段）1
     *
     * @param F23 （备用字段）1
     */
    public void setF23(String F23) {
        this.f23 = F23;
    }
 
    /** 
     * 设置（备用字段）2
     *
     * @param F24 （备用字段）2
     */
    public void setF24(String F24) {
        this.f24 = F24;
    }
 
    /** 
     * 设置（备用字段）3
     *
     * @param F25 （备用字段）3
     */
    public void setF25(String F25) {
        this.f25 = F25;
    }
 
}
