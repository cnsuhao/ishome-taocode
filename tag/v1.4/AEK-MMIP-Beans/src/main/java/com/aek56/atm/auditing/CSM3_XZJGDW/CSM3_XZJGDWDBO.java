package com.aek56.atm.auditing.CSM3_XZJGDW;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 行政机构单位(审核)*/
public class CSM3_XZJGDWDBO extends MyDataBaseObjectSupport
{
    /** 
     * 国家机关ID
     */
    private String p01_puk = "";
 
    /** 
     * 数据类别
     */
    private String p02_sjlb = "";
 
    /** 
     * 国家机关编号
     */
    private String k01_gjjgbh = "";
 
    /** 
     * 机关行政类别
     */
    private String k02_jgxzlb = "";
 
    /** 
     * 所属国家
     */
    private String k03_ssgj = "";
 
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
     * 机关全称
     */
    private String f01_jgqc = "";
 
    /** 
     * 机关全称拼音码
     */
    private String f02_jgqcpym = "";
 
    /** 
     * 所在省
     */
    private String f03_szs = "";
 
    /** 
     * 所在地/市
     */
    private String f04_szds = "";
 
    /** 
     * 所在区/县
     */
    private String f05_szqx = "";
 
    /** 
     * 所在详细地址
     */
    private String f06_szxxdz = "";
 
    /** 
     * 联系电话
     */
    private String f07_lxdh = "";
 
    /** 
     * 服务热线
     */
    private String f08_fwrx = "";
 
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
     * LOGO URL地址
     */
    private String f20_logo_url = "";
 
    /** 
     * 获取国家机关ID
     *
     * @return P01_PUK 国家机关ID
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
     * 获取国家机关编号
     *
     * @return K01_GJJGBH 国家机关编号
     */
    public String getK01_gjjgbh() {
        return this.k01_gjjgbh;
    }
 
    /** 
     * 获取机关行政类别
     *
     * @return K02_JGXZLB 机关行政类别
     */
    public String getK02_jgxzlb() {
        return this.k02_jgxzlb;
    }
 
    /** 
     * 获取所属国家
     *
     * @return K03_SSGJ 所属国家
     */
    public String getK03_ssgj() {
        return this.k03_ssgj;
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
     * 获取机关全称
     *
     * @return F01_JGQC 机关全称
     */
    public String getF01_jgqc() {
        return this.f01_jgqc;
    }
 
    /** 
     * 获取机关全称拼音码
     *
     * @return F02_JGQCPYM 机关全称拼音码
     */
    public String getF02_jgqcpym() {
        return this.f02_jgqcpym;
    }
 
    /** 
     * 获取所在省
     *
     * @return F03_SZS 所在省
     */
    public String getF03_szs() {
        return this.f03_szs;
    }
 
    /** 
     * 获取所在地/市
     *
     * @return F04_SZDS 所在地/市
     */
    public String getF04_szds() {
        return this.f04_szds;
    }
 
    /** 
     * 获取所在区/县
     *
     * @return F05_SZQX 所在区/县
     */
    public String getF05_szqx() {
        return this.f05_szqx;
    }
 
    /** 
     * 获取所在详细地址
     *
     * @return F06_SZXXDZ 所在详细地址
     */
    public String getF06_szxxdz() {
        return this.f06_szxxdz;
    }
 
    /** 
     * 获取联系电话
     *
     * @return F07_LXDH 联系电话
     */
    public String getF07_lxdh() {
        return this.f07_lxdh;
    }
 
    /** 
     * 获取服务热线
     *
     * @return F08_FWRX 服务热线
     */
    public String getF08_fwrx() {
        return this.f08_fwrx;
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
     * 获取LOGO URL地址
     *
     * @return F20_LOGO_URL LOGO URL地址
     */
    public String getF20_logo_url() {
        return this.f20_logo_url;
    }
 
    /** 
     * 设置国家机关ID
     *
     * @param P01_PUK 国家机关ID
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
     * 设置国家机关编号
     *
     * @param K01_GJJGBH 国家机关编号
     */
    public void setK01_gjjgbh(String K01_GJJGBH) {
        this.k01_gjjgbh = K01_GJJGBH;
    }
 
    /** 
     * 设置机关行政类别
     *
     * @param K02_JGXZLB 机关行政类别
     */
    public void setK02_jgxzlb(String K02_JGXZLB) {
        this.k02_jgxzlb = K02_JGXZLB;
    }
 
    /** 
     * 设置所属国家
     *
     * @param K03_SSGJ 所属国家
     */
    public void setK03_ssgj(String K03_SSGJ) {
        this.k03_ssgj = K03_SSGJ;
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
     * 设置机关全称
     *
     * @param F01_JGQC 机关全称
     */
    public void setF01_jgqc(String F01_JGQC) {
        this.f01_jgqc = F01_JGQC;
    }
 
    /** 
     * 设置机关全称拼音码
     *
     * @param F02_JGQCPYM 机关全称拼音码
     */
    public void setF02_jgqcpym(String F02_JGQCPYM) {
        this.f02_jgqcpym = F02_JGQCPYM;
    }
 
    /** 
     * 设置所在省
     *
     * @param F03_SZS 所在省
     */
    public void setF03_szs(String F03_SZS) {
        this.f03_szs = F03_SZS;
    }
 
    /** 
     * 设置所在地/市
     *
     * @param F04_SZDS 所在地/市
     */
    public void setF04_szds(String F04_SZDS) {
        this.f04_szds = F04_SZDS;
    }
 
    /** 
     * 设置所在区/县
     *
     * @param F05_SZQX 所在区/县
     */
    public void setF05_szqx(String F05_SZQX) {
        this.f05_szqx = F05_SZQX;
    }
 
    /** 
     * 设置所在详细地址
     *
     * @param F06_SZXXDZ 所在详细地址
     */
    public void setF06_szxxdz(String F06_SZXXDZ) {
        this.f06_szxxdz = F06_SZXXDZ;
    }
 
    /** 
     * 设置联系电话
     *
     * @param F07_LXDH 联系电话
     */
    public void setF07_lxdh(String F07_LXDH) {
        this.f07_lxdh = F07_LXDH;
    }
 
    /** 
     * 设置服务热线
     *
     * @param F08_FWRX 服务热线
     */
    public void setF08_fwrx(String F08_FWRX) {
        this.f08_fwrx = F08_FWRX;
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
     * 设置LOGO URL地址
     *
     * @param F20_LOGO_URL LOGO URL地址
     */
    public void setF20_logo_url(String F20_LOGO_URL) {
        this.f20_logo_url = F20_LOGO_URL;
    }
 
}
