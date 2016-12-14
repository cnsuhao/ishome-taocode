package com.aek56.atm.credentials.MGTA_CJYLQXZCZ;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商提供医院厂家医疗器械注册证*/
public class MGTA_CJYLQXZCZDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String p01_gysid = "";
 
    /** 
     * 证件标识（换证原因）
     */
    private String p02_zjbs = "";
 
    /** 
     * 医院ID
     */
    private String p03_yyid = "";
 
    /** 
     * 注册证ID（业务主键）
     */
    private String k01_zczid = "";
 
    /** 
     * 证件68码
     */
    private String k02_zj68bh = "";
 
    /** 
     * 证件类别（I\II\III）
     */
    private String k03_zj123lb = "";
 
    /** 
     * 生产企业ID
     */
    private String k04_scqyid = "";
 
    /** 
     * 注册号
     */
    private String f01_zczzwmc = "";
 
    /** 
     * 注册证英文名称（REG.NO）
     */
    private String f02_zczywmc = "";
 
    /** 
     * 产品中文名称
     */
    private String f03_cpzwmc = "";
 
    /** 
     * 产品英文名称
     */
    private String f04_cpywmc = "";
 
    /** 
     * 发证国家名称
     */
    private String f05_fzgjmc = "";
 
    /** 
     * 发证单位名称
     */
    private String f06_fzdwmc = "";
 
    /** 
     * 发证日期
     */
    private String f07_fzrq = "";
 
    /** 
     * 有效年限
     */
    private String f08_yxnx = "";
 
    /** 
     * 有效开始日期
     */
    private String f09_yxksrq = "";
 
    /** 
     * 有效终止日期
     */
    private String f10_yxzzrq = "";
 
    /** 
     * 审核状态
     */
    private String f11_shzt = "";
 
    /** 
     * 延期标志
     */
    private String f12_yqbz = "";
 
    /** 
     * 延期证号
     */
    private String f13_yqzh = "";
 
    /** 
     * 延期时间
     */
    private String f14_yqsj = "";
 
    /** 
     * 是否停用
     */
    private String f15_sfty = "";
 
    /** 
     * 进口标志
     */
    private String f16_jkbz = "";
 
    /** 
     * 产品执行标准编号
     */
    private String f17_cpzxbzbh = "";
 
    /** 
     * 产品性能结构及组成
     */
    private String f18_cpxnjgjzc = "";
 
    /** 
     * 产品适用范围
     */
    private String f19_cpsyfw = "";
 
    /** 
     * 产品禁忌症
     */
    private String f20_cpjjz = "";
 
    /** 
     * 注册地址所在国家
     */
    private String f21_zcdzszgj = "";
 
    /** 
     * 注册代理机构ID
     */
    private String f22_zcdljgid = "";
 
    /** 
     * 注册代理机构名称
     */
    private String f23_zcdljgmc = "";
 
    /** 
     * 售后服务机构ID
     */
    private String f24_shfwjgid = "";
 
    /** 
     * 售后服务机构名称
     */
    private String f25_shfwjgmc = "";
 
    /** 
     * 售后服务热线
     */
    private String f26_shfwrx = "";
 
    /** 
     * 售后服务地址
     */
    private String f27_shfwdz = "";
 
    /** 
     * 物理文件保存位置
     */
    private String f28_wlwjbcwz = "";
 
    /** 
     * 注意事项
     */
    private String f29_zysx = "";
 
    /** 
     * 备注
     */
    private String f30_bz = "";
 
    /** 
     * 生产企业组织机构代码
     */
    private String f31_scqyzzjgdm = "";
 
    /** 
     * 生产企业中文名称
     */
    private String f32_scqyzwmc = "";
 
    /** 
     * 生产企业地址
     */
    private String f33_scqydz = "";
 
    /** 
     * 生产企业邮编
     */
    private String f34_scqyyb = "";
 
    /** 
     * 生产企业电话号码
     */
    private String f35_scqydhhm = "";
 
    /** 
     * 生产企业英文名称
     */
    private String f36_scqyywmc = "";
 
    /** 
     * 生产企业拼音码
     */
    private String f37_scqypym = "";
 
    /** 
     * 生产企业联系人姓名
     */
    private String f38_scqylxrxm = "";
 
    /** 
     * 生产企业联系人安全邮箱
     */
    private String f39_scqylxraqyx = "";
 
    /** 
     * 生产企业联系人电话
     */
    private String f40_scqylxrdh = "";
 
    /** 
     * 是否过期
     */
    private String f41_sfgq = "";
 
    /** 
     * 发证单位ID
     */
    private String f42_fzdwid = "";
 
    /** 
     * 字段43
     */
    private String f43 = "";
 
    /** 
     * 字段44
     */
    private String f44 = "";
 
    /** 
     * 字段45
     */
    private String f45 = "";
 
    /** 
     * 获取供应商ID
     *
     * @return P01_GYSID 供应商ID
     */
    public String getP01_gysid() {
        return this.p01_gysid;
    }
 
    /** 
     * 获取证件标识（换证原因）
     *
     * @return P02_ZJBS 证件标识（换证原因）
     */
    public String getP02_zjbs() {
        return this.p02_zjbs;
    }
 
    /** 
     * 获取医院ID
     *
     * @return P03_YYID 医院ID
     */
    public String getP03_yyid() {
        return this.p03_yyid;
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
     * 获取证件68码
     *
     * @return K02_ZJ68BH 证件68码
     */
    public String getK02_zj68bh() {
        return this.k02_zj68bh;
    }
 
    /** 
     * 获取证件类别（I\II\III）
     *
     * @return K03_ZJ123LB 证件类别（I\II\III）
     */
    public String getK03_zj123lb() {
        return this.k03_zj123lb;
    }
 
    /** 
     * 获取生产企业ID
     *
     * @return K04_SCQYID 生产企业ID
     */
    public String getK04_scqyid() {
        return this.k04_scqyid;
    }
 
    /** 
     * 获取注册号
     *
     * @return F01_ZCZZWMC 注册号
     */
    public String getF01_zczzwmc() {
        return this.f01_zczzwmc;
    }
 
    /** 
     * 获取注册证英文名称（REG.NO）
     *
     * @return F02_ZCZYWMC 注册证英文名称（REG.NO）
     */
    public String getF02_zczywmc() {
        return this.f02_zczywmc;
    }
 
    /** 
     * 获取产品中文名称
     *
     * @return F03_CPZWMC 产品中文名称
     */
    public String getF03_cpzwmc() {
        return this.f03_cpzwmc;
    }
 
    /** 
     * 获取产品英文名称
     *
     * @return F04_CPYWMC 产品英文名称
     */
    public String getF04_cpywmc() {
        return this.f04_cpywmc;
    }
 
    /** 
     * 获取发证国家名称
     *
     * @return F05_FZGJMC 发证国家名称
     */
    public String getF05_fzgjmc() {
        return this.f05_fzgjmc;
    }
 
    /** 
     * 获取发证单位名称
     *
     * @return F06_FZDWMC 发证单位名称
     */
    public String getF06_fzdwmc() {
        return this.f06_fzdwmc;
    }
 
    /** 
     * 获取发证日期
     *
     * @return F07_FZRQ 发证日期
     */
    public String getF07_fzrq() {
        return this.f07_fzrq;
    }
 
    /** 
     * 获取有效年限
     *
     * @return F08_YXNX 有效年限
     */
    public String getF08_yxnx() {
        return this.f08_yxnx;
    }
 
    /** 
     * 获取有效开始日期
     *
     * @return F09_YXKSRQ 有效开始日期
     */
    public String getF09_yxksrq() {
        return this.f09_yxksrq;
    }
 
    /** 
     * 获取有效终止日期
     *
     * @return F10_YXZZRQ 有效终止日期
     */
    public String getF10_yxzzrq() {
        return this.f10_yxzzrq;
    }
 
    /** 
     * 获取审核状态
     *
     * @return F11_SHZT 审核状态
     */
    public String getF11_shzt() {
        return this.f11_shzt;
    }
 
    /** 
     * 获取延期标志
     *
     * @return F12_YQBZ 延期标志
     */
    public String getF12_yqbz() {
        return this.f12_yqbz;
    }
 
    /** 
     * 获取延期证号
     *
     * @return F13_YQZH 延期证号
     */
    public String getF13_yqzh() {
        return this.f13_yqzh;
    }
 
    /** 
     * 获取延期时间
     *
     * @return F14_YQSJ 延期时间
     */
    public String getF14_yqsj() {
        return this.f14_yqsj;
    }
 
    /** 
     * 获取是否停用
     *
     * @return F15_SFTY 是否停用
     */
    public String getF15_sfty() {
        return this.f15_sfty;
    }
 
    /** 
     * 获取进口标志
     *
     * @return F16_JKBZ 进口标志
     */
    public String getF16_jkbz() {
        return this.f16_jkbz;
    }
 
    /** 
     * 获取产品执行标准编号
     *
     * @return F17_CPZXBZBH 产品执行标准编号
     */
    public String getF17_cpzxbzbh() {
        return this.f17_cpzxbzbh;
    }
 
    /** 
     * 获取产品性能结构及组成
     *
     * @return F18_CPXNJGJZC 产品性能结构及组成
     */
    public String getF18_cpxnjgjzc() {
        return this.f18_cpxnjgjzc;
    }
 
    /** 
     * 获取产品适用范围
     *
     * @return F19_CPSYFW 产品适用范围
     */
    public String getF19_cpsyfw() {
        return this.f19_cpsyfw;
    }
 
    /** 
     * 获取产品禁忌症
     *
     * @return F20_CPJJZ 产品禁忌症
     */
    public String getF20_cpjjz() {
        return this.f20_cpjjz;
    }
 
    /** 
     * 获取注册地址所在国家
     *
     * @return F21_ZCDZSZGJ 注册地址所在国家
     */
    public String getF21_zcdzszgj() {
        return this.f21_zcdzszgj;
    }
 
    /** 
     * 获取注册代理机构ID
     *
     * @return F22_ZCDLJGID 注册代理机构ID
     */
    public String getF22_zcdljgid() {
        return this.f22_zcdljgid;
    }
 
    /** 
     * 获取注册代理机构名称
     *
     * @return F23_ZCDLJGMC 注册代理机构名称
     */
    public String getF23_zcdljgmc() {
        return this.f23_zcdljgmc;
    }
 
    /** 
     * 获取售后服务机构ID
     *
     * @return F24_SHFWJGID 售后服务机构ID
     */
    public String getF24_shfwjgid() {
        return this.f24_shfwjgid;
    }
 
    /** 
     * 获取售后服务机构名称
     *
     * @return F25_SHFWJGMC 售后服务机构名称
     */
    public String getF25_shfwjgmc() {
        return this.f25_shfwjgmc;
    }
 
    /** 
     * 获取售后服务热线
     *
     * @return F26_SHFWRX 售后服务热线
     */
    public String getF26_shfwrx() {
        return this.f26_shfwrx;
    }
 
    /** 
     * 获取售后服务地址
     *
     * @return F27_SHFWDZ 售后服务地址
     */
    public String getF27_shfwdz() {
        return this.f27_shfwdz;
    }
 
    /** 
     * 获取物理文件保存位置
     *
     * @return F28_WLWJBCWZ 物理文件保存位置
     */
    public String getF28_wlwjbcwz() {
        return this.f28_wlwjbcwz;
    }
 
    /** 
     * 获取注意事项
     *
     * @return F29_ZYSX 注意事项
     */
    public String getF29_zysx() {
        return this.f29_zysx;
    }
 
    /** 
     * 获取备注
     *
     * @return F30_BZ 备注
     */
    public String getF30_bz() {
        return this.f30_bz;
    }
 
    /** 
     * 获取生产企业组织机构代码
     *
     * @return F31_SCQYZZJGDM 生产企业组织机构代码
     */
    public String getF31_scqyzzjgdm() {
        return this.f31_scqyzzjgdm;
    }
 
    /** 
     * 获取生产企业中文名称
     *
     * @return F32_SCQYZWMC 生产企业中文名称
     */
    public String getF32_scqyzwmc() {
        return this.f32_scqyzwmc;
    }
 
    /** 
     * 获取生产企业地址
     *
     * @return F33_SCQYDZ 生产企业地址
     */
    public String getF33_scqydz() {
        return this.f33_scqydz;
    }
 
    /** 
     * 获取生产企业邮编
     *
     * @return F34_SCQYYB 生产企业邮编
     */
    public String getF34_scqyyb() {
        return this.f34_scqyyb;
    }
 
    /** 
     * 获取生产企业电话号码
     *
     * @return F35_SCQYDHHM 生产企业电话号码
     */
    public String getF35_scqydhhm() {
        return this.f35_scqydhhm;
    }
 
    /** 
     * 获取生产企业英文名称
     *
     * @return F36_SCQYYWMC 生产企业英文名称
     */
    public String getF36_scqyywmc() {
        return this.f36_scqyywmc;
    }
 
    /** 
     * 获取生产企业拼音码
     *
     * @return F37_SCQYPYM 生产企业拼音码
     */
    public String getF37_scqypym() {
        return this.f37_scqypym;
    }
 
    /** 
     * 获取生产企业联系人姓名
     *
     * @return F38_SCQYLXRXM 生产企业联系人姓名
     */
    public String getF38_scqylxrxm() {
        return this.f38_scqylxrxm;
    }
 
    /** 
     * 获取生产企业联系人安全邮箱
     *
     * @return F39_SCQYLXRAQYX 生产企业联系人安全邮箱
     */
    public String getF39_scqylxraqyx() {
        return this.f39_scqylxraqyx;
    }
 
    /** 
     * 获取生产企业联系人电话
     *
     * @return F40_SCQYLXRDH 生产企业联系人电话
     */
    public String getF40_scqylxrdh() {
        return this.f40_scqylxrdh;
    }
 
    /** 
     * 获取是否过期
     *
     * @return F41_SFGQ 是否过期
     */
    public String getF41_sfgq() {
        return this.f41_sfgq;
    }
 
    /** 
     * 获取发证单位ID
     *
     * @return F42_FZDWID 发证单位ID
     */
    public String getF42_fzdwid() {
        return this.f42_fzdwid;
    }
 
    /** 
     * 获取字段43
     *
     * @return F43 字段43
     */
    public String getF43() {
        return this.f43;
    }
 
    /** 
     * 获取字段44
     *
     * @return F44 字段44
     */
    public String getF44() {
        return this.f44;
    }
 
    /** 
     * 获取字段45
     *
     * @return F45 字段45
     */
    public String getF45() {
        return this.f45;
    }
 
    /** 
     * 设置供应商ID
     *
     * @param P01_GYSID 供应商ID
     */
    public void setP01_gysid(String P01_GYSID) {
        this.p01_gysid = P01_GYSID;
    }
 
    /** 
     * 设置证件标识（换证原因）
     *
     * @param P02_ZJBS 证件标识（换证原因）
     */
    public void setP02_zjbs(String P02_ZJBS) {
        this.p02_zjbs = P02_ZJBS;
    }
 
    /** 
     * 设置医院ID
     *
     * @param P03_YYID 医院ID
     */
    public void setP03_yyid(String P03_YYID) {
        this.p03_yyid = P03_YYID;
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
     * 设置证件68码
     *
     * @param K02_ZJ68BH 证件68码
     */
    public void setK02_zj68bh(String K02_ZJ68BH) {
        this.k02_zj68bh = K02_ZJ68BH;
    }
 
    /** 
     * 设置证件类别（I\II\III）
     *
     * @param K03_ZJ123LB 证件类别（I\II\III）
     */
    public void setK03_zj123lb(String K03_ZJ123LB) {
        this.k03_zj123lb = K03_ZJ123LB;
    }
 
    /** 
     * 设置生产企业ID
     *
     * @param K04_SCQYID 生产企业ID
     */
    public void setK04_scqyid(String K04_SCQYID) {
        this.k04_scqyid = K04_SCQYID;
    }
 
    /** 
     * 设置注册号
     *
     * @param F01_ZCZZWMC 注册号
     */
    public void setF01_zczzwmc(String F01_ZCZZWMC) {
        this.f01_zczzwmc = F01_ZCZZWMC;
    }
 
    /** 
     * 设置注册证英文名称（REG.NO）
     *
     * @param F02_ZCZYWMC 注册证英文名称（REG.NO）
     */
    public void setF02_zczywmc(String F02_ZCZYWMC) {
        this.f02_zczywmc = F02_ZCZYWMC;
    }
 
    /** 
     * 设置产品中文名称
     *
     * @param F03_CPZWMC 产品中文名称
     */
    public void setF03_cpzwmc(String F03_CPZWMC) {
        this.f03_cpzwmc = F03_CPZWMC;
    }
 
    /** 
     * 设置产品英文名称
     *
     * @param F04_CPYWMC 产品英文名称
     */
    public void setF04_cpywmc(String F04_CPYWMC) {
        this.f04_cpywmc = F04_CPYWMC;
    }
 
    /** 
     * 设置发证国家名称
     *
     * @param F05_FZGJMC 发证国家名称
     */
    public void setF05_fzgjmc(String F05_FZGJMC) {
        this.f05_fzgjmc = F05_FZGJMC;
    }
 
    /** 
     * 设置发证单位名称
     *
     * @param F06_FZDWMC 发证单位名称
     */
    public void setF06_fzdwmc(String F06_FZDWMC) {
        this.f06_fzdwmc = F06_FZDWMC;
    }
 
    /** 
     * 设置发证日期
     *
     * @param F07_FZRQ 发证日期
     */
    public void setF07_fzrq(String F07_FZRQ) {
        this.f07_fzrq = F07_FZRQ;
    }
 
    /** 
     * 设置有效年限
     *
     * @param F08_YXNX 有效年限
     */
    public void setF08_yxnx(String F08_YXNX) {
        this.f08_yxnx = F08_YXNX;
    }
 
    /** 
     * 设置有效开始日期
     *
     * @param F09_YXKSRQ 有效开始日期
     */
    public void setF09_yxksrq(String F09_YXKSRQ) {
        this.f09_yxksrq = F09_YXKSRQ;
    }
 
    /** 
     * 设置有效终止日期
     *
     * @param F10_YXZZRQ 有效终止日期
     */
    public void setF10_yxzzrq(String F10_YXZZRQ) {
        this.f10_yxzzrq = F10_YXZZRQ;
    }
 
    /** 
     * 设置审核状态
     *
     * @param F11_SHZT 审核状态
     */
    public void setF11_shzt(String F11_SHZT) {
        this.f11_shzt = F11_SHZT;
    }
 
    /** 
     * 设置延期标志
     *
     * @param F12_YQBZ 延期标志
     */
    public void setF12_yqbz(String F12_YQBZ) {
        this.f12_yqbz = F12_YQBZ;
    }
 
    /** 
     * 设置延期证号
     *
     * @param F13_YQZH 延期证号
     */
    public void setF13_yqzh(String F13_YQZH) {
        this.f13_yqzh = F13_YQZH;
    }
 
    /** 
     * 设置延期时间
     *
     * @param F14_YQSJ 延期时间
     */
    public void setF14_yqsj(String F14_YQSJ) {
        this.f14_yqsj = F14_YQSJ;
    }
 
    /** 
     * 设置是否停用
     *
     * @param F15_SFTY 是否停用
     */
    public void setF15_sfty(String F15_SFTY) {
        this.f15_sfty = F15_SFTY;
    }
 
    /** 
     * 设置进口标志
     *
     * @param F16_JKBZ 进口标志
     */
    public void setF16_jkbz(String F16_JKBZ) {
        this.f16_jkbz = F16_JKBZ;
    }
 
    /** 
     * 设置产品执行标准编号
     *
     * @param F17_CPZXBZBH 产品执行标准编号
     */
    public void setF17_cpzxbzbh(String F17_CPZXBZBH) {
        this.f17_cpzxbzbh = F17_CPZXBZBH;
    }
 
    /** 
     * 设置产品性能结构及组成
     *
     * @param F18_CPXNJGJZC 产品性能结构及组成
     */
    public void setF18_cpxnjgjzc(String F18_CPXNJGJZC) {
        this.f18_cpxnjgjzc = F18_CPXNJGJZC;
    }
 
    /** 
     * 设置产品适用范围
     *
     * @param F19_CPSYFW 产品适用范围
     */
    public void setF19_cpsyfw(String F19_CPSYFW) {
        this.f19_cpsyfw = F19_CPSYFW;
    }
 
    /** 
     * 设置产品禁忌症
     *
     * @param F20_CPJJZ 产品禁忌症
     */
    public void setF20_cpjjz(String F20_CPJJZ) {
        this.f20_cpjjz = F20_CPJJZ;
    }
 
    /** 
     * 设置注册地址所在国家
     *
     * @param F21_ZCDZSZGJ 注册地址所在国家
     */
    public void setF21_zcdzszgj(String F21_ZCDZSZGJ) {
        this.f21_zcdzszgj = F21_ZCDZSZGJ;
    }
 
    /** 
     * 设置注册代理机构ID
     *
     * @param F22_ZCDLJGID 注册代理机构ID
     */
    public void setF22_zcdljgid(String F22_ZCDLJGID) {
        this.f22_zcdljgid = F22_ZCDLJGID;
    }
 
    /** 
     * 设置注册代理机构名称
     *
     * @param F23_ZCDLJGMC 注册代理机构名称
     */
    public void setF23_zcdljgmc(String F23_ZCDLJGMC) {
        this.f23_zcdljgmc = F23_ZCDLJGMC;
    }
 
    /** 
     * 设置售后服务机构ID
     *
     * @param F24_SHFWJGID 售后服务机构ID
     */
    public void setF24_shfwjgid(String F24_SHFWJGID) {
        this.f24_shfwjgid = F24_SHFWJGID;
    }
 
    /** 
     * 设置售后服务机构名称
     *
     * @param F25_SHFWJGMC 售后服务机构名称
     */
    public void setF25_shfwjgmc(String F25_SHFWJGMC) {
        this.f25_shfwjgmc = F25_SHFWJGMC;
    }
 
    /** 
     * 设置售后服务热线
     *
     * @param F26_SHFWRX 售后服务热线
     */
    public void setF26_shfwrx(String F26_SHFWRX) {
        this.f26_shfwrx = F26_SHFWRX;
    }
 
    /** 
     * 设置售后服务地址
     *
     * @param F27_SHFWDZ 售后服务地址
     */
    public void setF27_shfwdz(String F27_SHFWDZ) {
        this.f27_shfwdz = F27_SHFWDZ;
    }
 
    /** 
     * 设置物理文件保存位置
     *
     * @param F28_WLWJBCWZ 物理文件保存位置
     */
    public void setF28_wlwjbcwz(String F28_WLWJBCWZ) {
        this.f28_wlwjbcwz = F28_WLWJBCWZ;
    }
 
    /** 
     * 设置注意事项
     *
     * @param F29_ZYSX 注意事项
     */
    public void setF29_zysx(String F29_ZYSX) {
        this.f29_zysx = F29_ZYSX;
    }
 
    /** 
     * 设置备注
     *
     * @param F30_BZ 备注
     */
    public void setF30_bz(String F30_BZ) {
        this.f30_bz = F30_BZ;
    }
 
    /** 
     * 设置生产企业组织机构代码
     *
     * @param F31_SCQYZZJGDM 生产企业组织机构代码
     */
    public void setF31_scqyzzjgdm(String F31_SCQYZZJGDM) {
        this.f31_scqyzzjgdm = F31_SCQYZZJGDM;
    }
 
    /** 
     * 设置生产企业中文名称
     *
     * @param F32_SCQYZWMC 生产企业中文名称
     */
    public void setF32_scqyzwmc(String F32_SCQYZWMC) {
        this.f32_scqyzwmc = F32_SCQYZWMC;
    }
 
    /** 
     * 设置生产企业地址
     *
     * @param F33_SCQYDZ 生产企业地址
     */
    public void setF33_scqydz(String F33_SCQYDZ) {
        this.f33_scqydz = F33_SCQYDZ;
    }
 
    /** 
     * 设置生产企业邮编
     *
     * @param F34_SCQYYB 生产企业邮编
     */
    public void setF34_scqyyb(String F34_SCQYYB) {
        this.f34_scqyyb = F34_SCQYYB;
    }
 
    /** 
     * 设置生产企业电话号码
     *
     * @param F35_SCQYDHHM 生产企业电话号码
     */
    public void setF35_scqydhhm(String F35_SCQYDHHM) {
        this.f35_scqydhhm = F35_SCQYDHHM;
    }
 
    /** 
     * 设置生产企业英文名称
     *
     * @param F36_SCQYYWMC 生产企业英文名称
     */
    public void setF36_scqyywmc(String F36_SCQYYWMC) {
        this.f36_scqyywmc = F36_SCQYYWMC;
    }
 
    /** 
     * 设置生产企业拼音码
     *
     * @param F37_SCQYPYM 生产企业拼音码
     */
    public void setF37_scqypym(String F37_SCQYPYM) {
        this.f37_scqypym = F37_SCQYPYM;
    }
 
    /** 
     * 设置生产企业联系人姓名
     *
     * @param F38_SCQYLXRXM 生产企业联系人姓名
     */
    public void setF38_scqylxrxm(String F38_SCQYLXRXM) {
        this.f38_scqylxrxm = F38_SCQYLXRXM;
    }
 
    /** 
     * 设置生产企业联系人安全邮箱
     *
     * @param F39_SCQYLXRAQYX 生产企业联系人安全邮箱
     */
    public void setF39_scqylxraqyx(String F39_SCQYLXRAQYX) {
        this.f39_scqylxraqyx = F39_SCQYLXRAQYX;
    }
 
    /** 
     * 设置生产企业联系人电话
     *
     * @param F40_SCQYLXRDH 生产企业联系人电话
     */
    public void setF40_scqylxrdh(String F40_SCQYLXRDH) {
        this.f40_scqylxrdh = F40_SCQYLXRDH;
    }
 
    /** 
     * 设置是否过期
     *
     * @param F41_SFGQ 是否过期
     */
    public void setF41_sfgq(String F41_SFGQ) {
        this.f41_sfgq = F41_SFGQ;
    }
 
    /** 
     * 设置发证单位ID
     *
     * @param F42_FZDWID 发证单位ID
     */
    public void setF42_fzdwid(String F42_FZDWID) {
        this.f42_fzdwid = F42_FZDWID;
    }
 
    /** 
     * 设置字段43
     *
     * @param F43 字段43
     */
    public void setF43(String F43) {
        this.f43 = F43;
    }
 
    /** 
     * 设置字段44
     *
     * @param F44 字段44
     */
    public void setF44(String F44) {
        this.f44 = F44;
    }
 
    /** 
     * 设置字段45
     *
     * @param F45 字段45
     */
    public void setF45(String F45) {
        this.f45 = F45;
    }
 
}
