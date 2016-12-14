package org.jfpc.beans.platform.MS1A1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业基本信息*/
public class MS1A1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 企业简称
     */
    private String f01_qyjc = "";
 
    /** 
     * 企业全称
     */
    private String f02_qyqc = "";
 
    /** 
     * 全称拼音码
     */
    private String f03_qcpym = "";
 
    /** 
     * 全称五笔码
     */
    private String f04_qcwbm = "";
 
    /** 
     * 官网地址
     */
    private String f05_gwdz = "";
 
    /** 
     * 成立日期
     */
    private String f06_clrq = "";
 
    /** 
     * 注册资本（万元）
     */
    private String f07_zczb = "";
 
    /** 
     * 法人姓名
     */
    private String f08_frxm = "";
 
    /** 
     * 法人身份证ID
     */
    private String f09_frsfz = "";
 
    /** 
     * 付款方式
     */
    private String f10_fkfs = "";
 
    /** 
     * 开户银行
     */
    private String f11_khyh = "";
 
    /** 
     * 开户银行账号
     */
    private String f12_khyhzh = "";
 
    /** 
     * 所在省
     */
    private String f13_szs = "";
 
    /** 
     * 所在地/市
     */
    private String f14_szs = "";
 
    /** 
     * 所在区/县
     */
    private String f15_szq = "";
 
    /** 
     * 所在详细地址
     */
    private String f16_szxxdz = "";
 
    /** 
     * 办公详细地址
     */
    private String f17_bgxxdz = "";
 
    /** 
     * 联系电话
     */
    private String f18_lxdh = "";
 
    /** 
     * 凭证编码
     */
    private String f19_pzbm = "";
 
    /** 
     * LOGO URL地址
     */
    private String f20_logo_url = "";
 
    /** 
     * 获取企业简称
     *
     * @return F01_QYJC 企业简称
     */
    public String getF01_qyjc() {
        return this.f01_qyjc;
    }
 
    /** 
     * 获取企业全称
     *
     * @return F02_QYQC 企业全称
     */
    public String getF02_qyqc() {
        return this.f02_qyqc;
    }
 
    /** 
     * 获取全称拼音码
     *
     * @return F03_QCPYM 全称拼音码
     */
    public String getF03_qcpym() {
        return this.f03_qcpym;
    }
 
    /** 
     * 获取全称五笔码
     *
     * @return F04_QCWBM 全称五笔码
     */
    public String getF04_qcwbm() {
        return this.f04_qcwbm;
    }
 
    /** 
     * 获取官网地址
     *
     * @return F05_GWDZ 官网地址
     */
    public String getF05_gwdz() {
        return this.f05_gwdz;
    }
 
    /** 
     * 获取成立日期
     *
     * @return F06_CLRQ 成立日期
     */
    public String getF06_clrq() {
        return this.f06_clrq;
    }
 
    /** 
     * 获取注册资本（万元）
     *
     * @return F07_ZCZB 注册资本（万元）
     */
    public String getF07_zczb() {
        return this.f07_zczb;
    }
 
    /** 
     * 获取法人姓名
     *
     * @return F08_FRXM 法人姓名
     */
    public String getF08_frxm() {
        return this.f08_frxm;
    }
 
    /** 
     * 获取法人身份证ID
     *
     * @return F09_FRSFZ 法人身份证ID
     */
    public String getF09_frsfz() {
        return this.f09_frsfz;
    }
 
    /** 
     * 获取付款方式
     *
     * @return F10_FKFS 付款方式
     */
    public String getF10_fkfs() {
        return this.f10_fkfs;
    }
 
    /** 
     * 获取开户银行
     *
     * @return F11_KHYH 开户银行
     */
    public String getF11_khyh() {
        return this.f11_khyh;
    }
 
    /** 
     * 获取开户银行账号
     *
     * @return F12_KHYHZH 开户银行账号
     */
    public String getF12_khyhzh() {
        return this.f12_khyhzh;
    }
 
    /** 
     * 获取所在省
     *
     * @return F13_SZS 所在省
     */
    public String getF13_szs() {
        return this.f13_szs;
    }
 
    /** 
     * 获取所在地/市
     *
     * @return F14_SZS 所在地/市
     */
    public String getF14_szs() {
        return this.f14_szs;
    }
 
    /** 
     * 获取所在区/县
     *
     * @return F15_SZQ 所在区/县
     */
    public String getF15_szq() {
        return this.f15_szq;
    }
 
    /** 
     * 获取所在详细地址
     *
     * @return F16_SZXXDZ 所在详细地址
     */
    public String getF16_szxxdz() {
        return this.f16_szxxdz;
    }
 
    /** 
     * 获取办公详细地址
     *
     * @return F17_BGXXDZ 办公详细地址
     */
    public String getF17_bgxxdz() {
        return this.f17_bgxxdz;
    }
 
    /** 
     * 获取联系电话
     *
     * @return F18_LXDH 联系电话
     */
    public String getF18_lxdh() {
        return this.f18_lxdh;
    }
 
    /** 
     * 获取凭证编码
     *
     * @return F19_PZBM 凭证编码
     */
    public String getF19_pzbm() {
        return this.f19_pzbm;
    }
 
    /** 
     * 获取LOGO URL地址
     *
     * @return F20_LOGO_URL LOGO URL地址
     */
    public String getF20_logo_url() {
        return this.f20_logo_url;
    }
 
    /** 
     * 设置企业简称
     *
     * @param F01_QYJC 企业简称
     */
    public void setF01_qyjc(String F01_QYJC) {
        this.f01_qyjc = F01_QYJC;
    }
 
    /** 
     * 设置企业全称
     *
     * @param F02_QYQC 企业全称
     */
    public void setF02_qyqc(String F02_QYQC) {
        this.f02_qyqc = F02_QYQC;
    }
 
    /** 
     * 设置全称拼音码
     *
     * @param F03_QCPYM 全称拼音码
     */
    public void setF03_qcpym(String F03_QCPYM) {
        this.f03_qcpym = F03_QCPYM;
    }
 
    /** 
     * 设置全称五笔码
     *
     * @param F04_QCWBM 全称五笔码
     */
    public void setF04_qcwbm(String F04_QCWBM) {
        this.f04_qcwbm = F04_QCWBM;
    }
 
    /** 
     * 设置官网地址
     *
     * @param F05_GWDZ 官网地址
     */
    public void setF05_gwdz(String F05_GWDZ) {
        this.f05_gwdz = F05_GWDZ;
    }
 
    /** 
     * 设置成立日期
     *
     * @param F06_CLRQ 成立日期
     */
    public void setF06_clrq(String F06_CLRQ) {
        this.f06_clrq = F06_CLRQ;
    }
 
    /** 
     * 设置注册资本（万元）
     *
     * @param F07_ZCZB 注册资本（万元）
     */
    public void setF07_zczb(String F07_ZCZB) {
        this.f07_zczb = F07_ZCZB;
    }
 
    /** 
     * 设置法人姓名
     *
     * @param F08_FRXM 法人姓名
     */
    public void setF08_frxm(String F08_FRXM) {
        this.f08_frxm = F08_FRXM;
    }
 
    /** 
     * 设置法人身份证ID
     *
     * @param F09_FRSFZ 法人身份证ID
     */
    public void setF09_frsfz(String F09_FRSFZ) {
        this.f09_frsfz = F09_FRSFZ;
    }
 
    /** 
     * 设置付款方式
     *
     * @param F10_FKFS 付款方式
     */
    public void setF10_fkfs(String F10_FKFS) {
        this.f10_fkfs = F10_FKFS;
    }
 
    /** 
     * 设置开户银行
     *
     * @param F11_KHYH 开户银行
     */
    public void setF11_khyh(String F11_KHYH) {
        this.f11_khyh = F11_KHYH;
    }
 
    /** 
     * 设置开户银行账号
     *
     * @param F12_KHYHZH 开户银行账号
     */
    public void setF12_khyhzh(String F12_KHYHZH) {
        this.f12_khyhzh = F12_KHYHZH;
    }
 
    /** 
     * 设置所在省
     *
     * @param F13_SZS 所在省
     */
    public void setF13_szs(String F13_SZS) {
        this.f13_szs = F13_SZS;
    }
 
    /** 
     * 设置所在地/市
     *
     * @param F14_SZS 所在地/市
     */
    public void setF14_szs(String F14_SZS) {
        this.f14_szs = F14_SZS;
    }
 
    /** 
     * 设置所在区/县
     *
     * @param F15_SZQ 所在区/县
     */
    public void setF15_szq(String F15_SZQ) {
        this.f15_szq = F15_SZQ;
    }
 
    /** 
     * 设置所在详细地址
     *
     * @param F16_SZXXDZ 所在详细地址
     */
    public void setF16_szxxdz(String F16_SZXXDZ) {
        this.f16_szxxdz = F16_SZXXDZ;
    }
 
    /** 
     * 设置办公详细地址
     *
     * @param F17_BGXXDZ 办公详细地址
     */
    public void setF17_bgxxdz(String F17_BGXXDZ) {
        this.f17_bgxxdz = F17_BGXXDZ;
    }
 
    /** 
     * 设置联系电话
     *
     * @param F18_LXDH 联系电话
     */
    public void setF18_lxdh(String F18_LXDH) {
        this.f18_lxdh = F18_LXDH;
    }
 
    /** 
     * 设置凭证编码
     *
     * @param F19_PZBM 凭证编码
     */
    public void setF19_pzbm(String F19_PZBM) {
        this.f19_pzbm = F19_PZBM;
    }
 
    /** 
     * 设置LOGO URL地址
     *
     * @param F20_LOGO_URL LOGO URL地址
     */
    public void setF20_logo_url(String F20_LOGO_URL) {
        this.f20_logo_url = F20_LOGO_URL;
    }
 
}
