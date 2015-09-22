package com.aek56.atm.auditing.CSM1_YYXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 医院信息(审核)*/
public class CSM1_YYXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 医院ID
     */
    private String p01_puk = "";
 
    /** 
     * 数据类别
     */
    private String p02_sjlb = "";
 
    /** 
     * 组织机构代码
     */
    private String k01_zjjgdm = "";
 
    /** 
     * 关键字2
     */
    private String k02 = "";
 
    /** 
     * 关键字3
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
     * 企业全称
     */
    private String f01_qyqc = "";
 
    /** 
     * 全称拼音码
     */
    private String f02_qcpym = "";
 
    /** 
     * 官网地址
     */
    private String f03_gwdz = "";
 
    /** 
     * 成立日期
     */
    private String f04_clrq = "";
 
    /** 
     * 注册资本（万元）
     */
    private String f05_zczbwy = "";
 
    /** 
     * 法人姓名
     */
    private String f06_frxm = "";
 
    /** 
     * 法人身份证ID
     */
    private String f07_frsfzid = "";
 
    /** 
     * 付款方式
     */
    private String f08_fkfs = "";
 
    /** 
     * 开户银行
     */
    private String f09_khyx = "";
 
    /** 
     * 开户银行账号
     */
    private String f10_khyxzh = "";
 
    /** 
     * 所在省
     */
    private String f11_szs = "";
 
    /** 
     * 所在地/市
     */
    private String f12_szds = "";
 
    /** 
     * 所在区/县
     */
    private String f13_szqx = "";
 
    /** 
     * 所在详细地址
     */
    private String f14_szxxdz = "";
 
    /** 
     * 办公详细地址
     */
    private String f15_bgxxdz = "";
 
    /** 
     * 联系电话
     */
    private String f16_lxdh = "";
 
    /** 
     * 传真号码
     */
    private String f17_czhm = "";
 
    /** 
     * 凭证编码
     */
    private String f18_pzbm = "";
 
    /** 
     * LOGO URL地址
     */
    private String f19_logo_url = "";
 
    /** 
     * 企业经营许可证编号
     */
    private String f20_qyjyxkzbh = "";
 
    /** 
     * 企业经营许可证开始日期
     */
    private String f21_qyjyxkzksrq = "";
 
    /** 
     * 企业经营许可证终了日期
     */
    private String f22_qyjyxkzzlrq = "";
 
    /** 
     * 企业工商税务登记证开始日期
     */
    private String f23_qygsswdjzksrq = "";
 
    /** 
     * 企业工商税务登记证终了日期
     */
    private String f24_qygsswdjzzlrq = "";
 
    /** 
     * 企业营业执照开始日期
     */
    private String f25_qyyyzzksrq = "";
 
    /** 
     * 企业营业执照终了日期
     */
    private String f26_qyyyzzzlrq = "";
 
    /** 
     * 企业所在国家
     */
    private String f27_qyszgj = "";
 
    /** 
     * 企业工商税务登记证编号
     */
    private String f28_qygsswdjzbh = "";
 
    /** 
     * 企业营业执照编号
     */
    private String f29_qyyyzzbh = "";
 
    /** 
     * 联系人姓名
     */
    private String f30_lxrxm = "";
 
    /** 
     * 联系人安全邮箱
     */
    private String f31_lxraqyx = "";
 
    /** 
     * 联系人电话
     */
    private String f32_lxrdh = "";
 
    /** 
     * 企业经营许可证URL
     */
    private String f33_qyjyxkz = "";
 
    /** 
     * 企业工商税务登记证URL
     */
    private String f34_qygsswdjz = "";
 
    /** 
     * 企业营业执照URL
     */
    private String f35_qyyyzz = "";
 
    /** 
     * 医院等级
     */
    private String f36_yydj = "";
 
    /** 
     * 医生数目
     */
    private String f37_yssm = "";
 
    /** 
     * 床位数目
     */
    private String f38_cwsm = "";
 
    /** 
     * 科室数目
     */
    private String f39_kssm = "";
 
    /** 
     * 职工人数
     */
    private String f40_zgrs = "";
 
    /** 
     * 床位数目
     */
    private String f41_cwsm = "";
 
    /** 
     * 字段42
     */
    private String f42 = "";
 
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
     * 获取医院ID
     *
     * @return P01_PUK 医院ID
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
     * 获取组织机构代码
     *
     * @return K01_ZJJGDM 组织机构代码
     */
    public String getK01_zjjgdm() {
        return this.k01_zjjgdm;
    }
 
    /** 
     * 获取关键字2
     *
     * @return K02 关键字2
     */
    public String getK02() {
        return this.k02;
    }
 
    /** 
     * 获取关键字3
     *
     * @return K03 关键字3
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
     * 获取企业全称
     *
     * @return F01_QYQC 企业全称
     */
    public String getF01_qyqc() {
        return this.f01_qyqc;
    }
 
    /** 
     * 获取全称拼音码
     *
     * @return F02_QCPYM 全称拼音码
     */
    public String getF02_qcpym() {
        return this.f02_qcpym;
    }
 
    /** 
     * 获取官网地址
     *
     * @return F03_GWDZ 官网地址
     */
    public String getF03_gwdz() {
        return this.f03_gwdz;
    }
 
    /** 
     * 获取成立日期
     *
     * @return F04_CLRQ 成立日期
     */
    public String getF04_clrq() {
        return this.f04_clrq;
    }
 
    /** 
     * 获取注册资本（万元）
     *
     * @return F05_ZCZBWY 注册资本（万元）
     */
    public String getF05_zczbwy() {
        return this.f05_zczbwy;
    }
 
    /** 
     * 获取法人姓名
     *
     * @return F06_FRXM 法人姓名
     */
    public String getF06_frxm() {
        return this.f06_frxm;
    }
 
    /** 
     * 获取法人身份证ID
     *
     * @return F07_FRSFZID 法人身份证ID
     */
    public String getF07_frsfzid() {
        return this.f07_frsfzid;
    }
 
    /** 
     * 获取付款方式
     *
     * @return F08_FKFS 付款方式
     */
    public String getF08_fkfs() {
        return this.f08_fkfs;
    }
 
    /** 
     * 获取开户银行
     *
     * @return F09_KHYX 开户银行
     */
    public String getF09_khyx() {
        return this.f09_khyx;
    }
 
    /** 
     * 获取开户银行账号
     *
     * @return F10_KHYXZH 开户银行账号
     */
    public String getF10_khyxzh() {
        return this.f10_khyxzh;
    }
 
    /** 
     * 获取所在省
     *
     * @return F11_SZS 所在省
     */
    public String getF11_szs() {
        return this.f11_szs;
    }
 
    /** 
     * 获取所在地/市
     *
     * @return F12_SZDS 所在地/市
     */
    public String getF12_szds() {
        return this.f12_szds;
    }
 
    /** 
     * 获取所在区/县
     *
     * @return F13_SZQX 所在区/县
     */
    public String getF13_szqx() {
        return this.f13_szqx;
    }
 
    /** 
     * 获取所在详细地址
     *
     * @return F14_SZXXDZ 所在详细地址
     */
    public String getF14_szxxdz() {
        return this.f14_szxxdz;
    }
 
    /** 
     * 获取办公详细地址
     *
     * @return F15_BGXXDZ 办公详细地址
     */
    public String getF15_bgxxdz() {
        return this.f15_bgxxdz;
    }
 
    /** 
     * 获取联系电话
     *
     * @return F16_LXDH 联系电话
     */
    public String getF16_lxdh() {
        return this.f16_lxdh;
    }
 
    /** 
     * 获取传真号码
     *
     * @return F17_CZHM 传真号码
     */
    public String getF17_czhm() {
        return this.f17_czhm;
    }
 
    /** 
     * 获取凭证编码
     *
     * @return F18_PZBM 凭证编码
     */
    public String getF18_pzbm() {
        return this.f18_pzbm;
    }
 
    /** 
     * 获取LOGO URL地址
     *
     * @return F19_LOGO_URL LOGO URL地址
     */
    public String getF19_logo_url() {
        return this.f19_logo_url;
    }
 
    /** 
     * 获取企业经营许可证编号
     *
     * @return F20_QYJYXKZBH 企业经营许可证编号
     */
    public String getF20_qyjyxkzbh() {
        return this.f20_qyjyxkzbh;
    }
 
    /** 
     * 获取企业经营许可证开始日期
     *
     * @return F21_QYJYXKZKSRQ 企业经营许可证开始日期
     */
    public String getF21_qyjyxkzksrq() {
        return this.f21_qyjyxkzksrq;
    }
 
    /** 
     * 获取企业经营许可证终了日期
     *
     * @return F22_QYJYXKZZLRQ 企业经营许可证终了日期
     */
    public String getF22_qyjyxkzzlrq() {
        return this.f22_qyjyxkzzlrq;
    }
 
    /** 
     * 获取企业工商税务登记证开始日期
     *
     * @return F23_QYGSSWDJZKSRQ 企业工商税务登记证开始日期
     */
    public String getF23_qygsswdjzksrq() {
        return this.f23_qygsswdjzksrq;
    }
 
    /** 
     * 获取企业工商税务登记证终了日期
     *
     * @return F24_QYGSSWDJZZLRQ 企业工商税务登记证终了日期
     */
    public String getF24_qygsswdjzzlrq() {
        return this.f24_qygsswdjzzlrq;
    }
 
    /** 
     * 获取企业营业执照开始日期
     *
     * @return F25_QYYYZZKSRQ 企业营业执照开始日期
     */
    public String getF25_qyyyzzksrq() {
        return this.f25_qyyyzzksrq;
    }
 
    /** 
     * 获取企业营业执照终了日期
     *
     * @return F26_QYYYZZZLRQ 企业营业执照终了日期
     */
    public String getF26_qyyyzzzlrq() {
        return this.f26_qyyyzzzlrq;
    }
 
    /** 
     * 获取企业所在国家
     *
     * @return F27_QYSZGJ 企业所在国家
     */
    public String getF27_qyszgj() {
        return this.f27_qyszgj;
    }
 
    /** 
     * 获取企业工商税务登记证编号
     *
     * @return F28_QYGSSWDJZBH 企业工商税务登记证编号
     */
    public String getF28_qygsswdjzbh() {
        return this.f28_qygsswdjzbh;
    }
 
    /** 
     * 获取企业营业执照编号
     *
     * @return F29_QYYYZZBH 企业营业执照编号
     */
    public String getF29_qyyyzzbh() {
        return this.f29_qyyyzzbh;
    }
 
    /** 
     * 获取联系人姓名
     *
     * @return F30_LXRXM 联系人姓名
     */
    public String getF30_lxrxm() {
        return this.f30_lxrxm;
    }
 
    /** 
     * 获取联系人安全邮箱
     *
     * @return F31_LXRAQYX 联系人安全邮箱
     */
    public String getF31_lxraqyx() {
        return this.f31_lxraqyx;
    }
 
    /** 
     * 获取联系人电话
     *
     * @return F32_LXRDH 联系人电话
     */
    public String getF32_lxrdh() {
        return this.f32_lxrdh;
    }
 
    /** 
     * 获取企业经营许可证URL
     *
     * @return F33_QYJYXKZ 企业经营许可证URL
     */
    public String getF33_qyjyxkz() {
        return this.f33_qyjyxkz;
    }
 
    /** 
     * 获取企业工商税务登记证URL
     *
     * @return F34_QYGSSWDJZ 企业工商税务登记证URL
     */
    public String getF34_qygsswdjz() {
        return this.f34_qygsswdjz;
    }
 
    /** 
     * 获取企业营业执照URL
     *
     * @return F35_QYYYZZ 企业营业执照URL
     */
    public String getF35_qyyyzz() {
        return this.f35_qyyyzz;
    }
 
    /** 
     * 获取医院等级
     *
     * @return F36_YYDJ 医院等级
     */
    public String getF36_yydj() {
        return this.f36_yydj;
    }
 
    /** 
     * 获取医生数目
     *
     * @return F37_YSSM 医生数目
     */
    public String getF37_yssm() {
        return this.f37_yssm;
    }
 
    /** 
     * 获取床位数目
     *
     * @return F38_CWSM 床位数目
     */
    public String getF38_cwsm() {
        return this.f38_cwsm;
    }
 
    /** 
     * 获取科室数目
     *
     * @return F39_KSSM 科室数目
     */
    public String getF39_kssm() {
        return this.f39_kssm;
    }
 
    /** 
     * 获取职工人数
     *
     * @return F40_ZGRS 职工人数
     */
    public String getF40_zgrs() {
        return this.f40_zgrs;
    }
 
    /** 
     * 获取床位数目
     *
     * @return F41_CWSM 床位数目
     */
    public String getF41_cwsm() {
        return this.f41_cwsm;
    }
 
    /** 
     * 获取字段42
     *
     * @return F42 字段42
     */
    public String getF42() {
        return this.f42;
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
     * 设置医院ID
     *
     * @param P01_PUK 医院ID
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
     * 设置组织机构代码
     *
     * @param K01_ZJJGDM 组织机构代码
     */
    public void setK01_zjjgdm(String K01_ZJJGDM) {
        this.k01_zjjgdm = K01_ZJJGDM;
    }
 
    /** 
     * 设置关键字2
     *
     * @param K02 关键字2
     */
    public void setK02(String K02) {
        this.k02 = K02;
    }
 
    /** 
     * 设置关键字3
     *
     * @param K03 关键字3
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
     * 设置企业全称
     *
     * @param F01_QYQC 企业全称
     */
    public void setF01_qyqc(String F01_QYQC) {
        this.f01_qyqc = F01_QYQC;
    }
 
    /** 
     * 设置全称拼音码
     *
     * @param F02_QCPYM 全称拼音码
     */
    public void setF02_qcpym(String F02_QCPYM) {
        this.f02_qcpym = F02_QCPYM;
    }
 
    /** 
     * 设置官网地址
     *
     * @param F03_GWDZ 官网地址
     */
    public void setF03_gwdz(String F03_GWDZ) {
        this.f03_gwdz = F03_GWDZ;
    }
 
    /** 
     * 设置成立日期
     *
     * @param F04_CLRQ 成立日期
     */
    public void setF04_clrq(String F04_CLRQ) {
        this.f04_clrq = F04_CLRQ;
    }
 
    /** 
     * 设置注册资本（万元）
     *
     * @param F05_ZCZBWY 注册资本（万元）
     */
    public void setF05_zczbwy(String F05_ZCZBWY) {
        this.f05_zczbwy = F05_ZCZBWY;
    }
 
    /** 
     * 设置法人姓名
     *
     * @param F06_FRXM 法人姓名
     */
    public void setF06_frxm(String F06_FRXM) {
        this.f06_frxm = F06_FRXM;
    }
 
    /** 
     * 设置法人身份证ID
     *
     * @param F07_FRSFZID 法人身份证ID
     */
    public void setF07_frsfzid(String F07_FRSFZID) {
        this.f07_frsfzid = F07_FRSFZID;
    }
 
    /** 
     * 设置付款方式
     *
     * @param F08_FKFS 付款方式
     */
    public void setF08_fkfs(String F08_FKFS) {
        this.f08_fkfs = F08_FKFS;
    }
 
    /** 
     * 设置开户银行
     *
     * @param F09_KHYX 开户银行
     */
    public void setF09_khyx(String F09_KHYX) {
        this.f09_khyx = F09_KHYX;
    }
 
    /** 
     * 设置开户银行账号
     *
     * @param F10_KHYXZH 开户银行账号
     */
    public void setF10_khyxzh(String F10_KHYXZH) {
        this.f10_khyxzh = F10_KHYXZH;
    }
 
    /** 
     * 设置所在省
     *
     * @param F11_SZS 所在省
     */
    public void setF11_szs(String F11_SZS) {
        this.f11_szs = F11_SZS;
    }
 
    /** 
     * 设置所在地/市
     *
     * @param F12_SZDS 所在地/市
     */
    public void setF12_szds(String F12_SZDS) {
        this.f12_szds = F12_SZDS;
    }
 
    /** 
     * 设置所在区/县
     *
     * @param F13_SZQX 所在区/县
     */
    public void setF13_szqx(String F13_SZQX) {
        this.f13_szqx = F13_SZQX;
    }
 
    /** 
     * 设置所在详细地址
     *
     * @param F14_SZXXDZ 所在详细地址
     */
    public void setF14_szxxdz(String F14_SZXXDZ) {
        this.f14_szxxdz = F14_SZXXDZ;
    }
 
    /** 
     * 设置办公详细地址
     *
     * @param F15_BGXXDZ 办公详细地址
     */
    public void setF15_bgxxdz(String F15_BGXXDZ) {
        this.f15_bgxxdz = F15_BGXXDZ;
    }
 
    /** 
     * 设置联系电话
     *
     * @param F16_LXDH 联系电话
     */
    public void setF16_lxdh(String F16_LXDH) {
        this.f16_lxdh = F16_LXDH;
    }
 
    /** 
     * 设置传真号码
     *
     * @param F17_CZHM 传真号码
     */
    public void setF17_czhm(String F17_CZHM) {
        this.f17_czhm = F17_CZHM;
    }
 
    /** 
     * 设置凭证编码
     *
     * @param F18_PZBM 凭证编码
     */
    public void setF18_pzbm(String F18_PZBM) {
        this.f18_pzbm = F18_PZBM;
    }
 
    /** 
     * 设置LOGO URL地址
     *
     * @param F19_LOGO_URL LOGO URL地址
     */
    public void setF19_logo_url(String F19_LOGO_URL) {
        this.f19_logo_url = F19_LOGO_URL;
    }
 
    /** 
     * 设置企业经营许可证编号
     *
     * @param F20_QYJYXKZBH 企业经营许可证编号
     */
    public void setF20_qyjyxkzbh(String F20_QYJYXKZBH) {
        this.f20_qyjyxkzbh = F20_QYJYXKZBH;
    }
 
    /** 
     * 设置企业经营许可证开始日期
     *
     * @param F21_QYJYXKZKSRQ 企业经营许可证开始日期
     */
    public void setF21_qyjyxkzksrq(String F21_QYJYXKZKSRQ) {
        this.f21_qyjyxkzksrq = F21_QYJYXKZKSRQ;
    }
 
    /** 
     * 设置企业经营许可证终了日期
     *
     * @param F22_QYJYXKZZLRQ 企业经营许可证终了日期
     */
    public void setF22_qyjyxkzzlrq(String F22_QYJYXKZZLRQ) {
        this.f22_qyjyxkzzlrq = F22_QYJYXKZZLRQ;
    }
 
    /** 
     * 设置企业工商税务登记证开始日期
     *
     * @param F23_QYGSSWDJZKSRQ 企业工商税务登记证开始日期
     */
    public void setF23_qygsswdjzksrq(String F23_QYGSSWDJZKSRQ) {
        this.f23_qygsswdjzksrq = F23_QYGSSWDJZKSRQ;
    }
 
    /** 
     * 设置企业工商税务登记证终了日期
     *
     * @param F24_QYGSSWDJZZLRQ 企业工商税务登记证终了日期
     */
    public void setF24_qygsswdjzzlrq(String F24_QYGSSWDJZZLRQ) {
        this.f24_qygsswdjzzlrq = F24_QYGSSWDJZZLRQ;
    }
 
    /** 
     * 设置企业营业执照开始日期
     *
     * @param F25_QYYYZZKSRQ 企业营业执照开始日期
     */
    public void setF25_qyyyzzksrq(String F25_QYYYZZKSRQ) {
        this.f25_qyyyzzksrq = F25_QYYYZZKSRQ;
    }
 
    /** 
     * 设置企业营业执照终了日期
     *
     * @param F26_QYYYZZZLRQ 企业营业执照终了日期
     */
    public void setF26_qyyyzzzlrq(String F26_QYYYZZZLRQ) {
        this.f26_qyyyzzzlrq = F26_QYYYZZZLRQ;
    }
 
    /** 
     * 设置企业所在国家
     *
     * @param F27_QYSZGJ 企业所在国家
     */
    public void setF27_qyszgj(String F27_QYSZGJ) {
        this.f27_qyszgj = F27_QYSZGJ;
    }
 
    /** 
     * 设置企业工商税务登记证编号
     *
     * @param F28_QYGSSWDJZBH 企业工商税务登记证编号
     */
    public void setF28_qygsswdjzbh(String F28_QYGSSWDJZBH) {
        this.f28_qygsswdjzbh = F28_QYGSSWDJZBH;
    }
 
    /** 
     * 设置企业营业执照编号
     *
     * @param F29_QYYYZZBH 企业营业执照编号
     */
    public void setF29_qyyyzzbh(String F29_QYYYZZBH) {
        this.f29_qyyyzzbh = F29_QYYYZZBH;
    }
 
    /** 
     * 设置联系人姓名
     *
     * @param F30_LXRXM 联系人姓名
     */
    public void setF30_lxrxm(String F30_LXRXM) {
        this.f30_lxrxm = F30_LXRXM;
    }
 
    /** 
     * 设置联系人安全邮箱
     *
     * @param F31_LXRAQYX 联系人安全邮箱
     */
    public void setF31_lxraqyx(String F31_LXRAQYX) {
        this.f31_lxraqyx = F31_LXRAQYX;
    }
 
    /** 
     * 设置联系人电话
     *
     * @param F32_LXRDH 联系人电话
     */
    public void setF32_lxrdh(String F32_LXRDH) {
        this.f32_lxrdh = F32_LXRDH;
    }
 
    /** 
     * 设置企业经营许可证URL
     *
     * @param F33_QYJYXKZ 企业经营许可证URL
     */
    public void setF33_qyjyxkz(String F33_QYJYXKZ) {
        this.f33_qyjyxkz = F33_QYJYXKZ;
    }
 
    /** 
     * 设置企业工商税务登记证URL
     *
     * @param F34_QYGSSWDJZ 企业工商税务登记证URL
     */
    public void setF34_qygsswdjz(String F34_QYGSSWDJZ) {
        this.f34_qygsswdjz = F34_QYGSSWDJZ;
    }
 
    /** 
     * 设置企业营业执照URL
     *
     * @param F35_QYYYZZ 企业营业执照URL
     */
    public void setF35_qyyyzz(String F35_QYYYZZ) {
        this.f35_qyyyzz = F35_QYYYZZ;
    }
 
    /** 
     * 设置医院等级
     *
     * @param F36_YYDJ 医院等级
     */
    public void setF36_yydj(String F36_YYDJ) {
        this.f36_yydj = F36_YYDJ;
    }
 
    /** 
     * 设置医生数目
     *
     * @param F37_YSSM 医生数目
     */
    public void setF37_yssm(String F37_YSSM) {
        this.f37_yssm = F37_YSSM;
    }
 
    /** 
     * 设置床位数目
     *
     * @param F38_CWSM 床位数目
     */
    public void setF38_cwsm(String F38_CWSM) {
        this.f38_cwsm = F38_CWSM;
    }
 
    /** 
     * 设置科室数目
     *
     * @param F39_KSSM 科室数目
     */
    public void setF39_kssm(String F39_KSSM) {
        this.f39_kssm = F39_KSSM;
    }
 
    /** 
     * 设置职工人数
     *
     * @param F40_ZGRS 职工人数
     */
    public void setF40_zgrs(String F40_ZGRS) {
        this.f40_zgrs = F40_ZGRS;
    }
 
    /** 
     * 设置床位数目
     *
     * @param F41_CWSM 床位数目
     */
    public void setF41_cwsm(String F41_CWSM) {
        this.f41_cwsm = F41_CWSM;
    }
 
    /** 
     * 设置字段42
     *
     * @param F42 字段42
     */
    public void setF42(String F42) {
        this.f42 = F42;
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
