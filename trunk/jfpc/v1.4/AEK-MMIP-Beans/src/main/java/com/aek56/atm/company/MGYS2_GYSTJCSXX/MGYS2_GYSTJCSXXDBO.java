package com.aek56.atm.company.MGYS2_GYSTJCSXX;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商添加厂商信息*/
public class MGYS2_GYSTJCSXXDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String p01_gysid = "";
 
    /** 
     * 企业简称（厂商）
     */
    private String n01_qyjc = "";
 
    /** 
     * 简称拼音码（厂商）
     */
    private String n02_jcpym = "";
 
    /** 
     * LOGO关键字（厂商）
     */
    private String n03_logogjz = "";
 
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
     * （备用字段）
     */
    private String f36_byzd = "";
 
    /** 
     * 字段37
     */
    private String f37 = "";
 
    /** 
     * 字段38
     */
    private String f38 = "";
 
    /** 
     * 字段39
     */
    private String f39 = "";
 
    /** 
     * 字段40
     */
    private String f40 = "";
 
    /** 
     * 获取供应商ID
     *
     * @return P01_GYSID 供应商ID
     */
    public String getP01_gysid() {
        return this.p01_gysid;
    }
 
    /** 
     * 获取企业简称（厂商）
     *
     * @return N01_QYJC 企业简称（厂商）
     */
    public String getN01_qyjc() {
        return this.n01_qyjc;
    }
 
    /** 
     * 获取简称拼音码（厂商）
     *
     * @return N02_JCPYM 简称拼音码（厂商）
     */
    public String getN02_jcpym() {
        return this.n02_jcpym;
    }
 
    /** 
     * 获取LOGO关键字（厂商）
     *
     * @return N03_LOGOGJZ LOGO关键字（厂商）
     */
    public String getN03_logogjz() {
        return this.n03_logogjz;
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
     * 获取（备用字段）
     *
     * @return F36_BYZD （备用字段）
     */
    public String getF36_byzd() {
        return this.f36_byzd;
    }
 
    /** 
     * 获取字段37
     *
     * @return F37 字段37
     */
    public String getF37() {
        return this.f37;
    }
 
    /** 
     * 获取字段38
     *
     * @return F38 字段38
     */
    public String getF38() {
        return this.f38;
    }
 
    /** 
     * 获取字段39
     *
     * @return F39 字段39
     */
    public String getF39() {
        return this.f39;
    }
 
    /** 
     * 获取字段40
     *
     * @return F40 字段40
     */
    public String getF40() {
        return this.f40;
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
     * 设置企业简称（厂商）
     *
     * @param N01_QYJC 企业简称（厂商）
     */
    public void setN01_qyjc(String N01_QYJC) {
        this.n01_qyjc = N01_QYJC;
    }
 
    /** 
     * 设置简称拼音码（厂商）
     *
     * @param N02_JCPYM 简称拼音码（厂商）
     */
    public void setN02_jcpym(String N02_JCPYM) {
        this.n02_jcpym = N02_JCPYM;
    }
 
    /** 
     * 设置LOGO关键字（厂商）
     *
     * @param N03_LOGOGJZ LOGO关键字（厂商）
     */
    public void setN03_logogjz(String N03_LOGOGJZ) {
        this.n03_logogjz = N03_LOGOGJZ;
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
     * 设置（备用字段）
     *
     * @param F36_BYZD （备用字段）
     */
    public void setF36_byzd(String F36_BYZD) {
        this.f36_byzd = F36_BYZD;
    }
 
    /** 
     * 设置字段37
     *
     * @param F37 字段37
     */
    public void setF37(String F37) {
        this.f37 = F37;
    }
 
    /** 
     * 设置字段38
     *
     * @param F38 字段38
     */
    public void setF38(String F38) {
        this.f38 = F38;
    }
 
    /** 
     * 设置字段39
     *
     * @param F39 字段39
     */
    public void setF39(String F39) {
        this.f39 = F39;
    }
 
    /** 
     * 设置字段40
     *
     * @param F40 字段40
     */
    public void setF40(String F40) {
        this.f40 = F40;
    }
 
}
