package com.aek56.atm.credentials.CGTZ_TSJL;

import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 供应商推送记录表*/
public class CGTZ_TSJLDBO extends MyDataBaseObjectSupport
{
    /** 
     * 供应商ID
     */
    private String k01_gysid = "";
 
    /** 
     * 医院ID
     */
    private String k02_yyid = "";
 
    /** 
     * 供应商提供医院营业执照
     */
    private String f01_yyzz = "";
 
    /** 
     * 供应商提供医院经营许可证
     */
    private String f02_jyxkz = "";
 
    /** 
     * 供应商提供医院工商税务登记证
     */
    private String f03_gsswdjz = "";
 
    /** 
     * 供应商提供医院经销授权书
     */
    private String f04_jxsqs = "";
 
    /** 
     * 供应商提供医院销售人员委托书
     */
    private String f05_xsrywts = "";
 
    /** 
     * 供应商提供医院售后服务承诺书
     */
    private String f06_shfwcns = "";
 
    /** 
     * 供应商提供医院进口商检报告
     */
    private String f07_jksjbg = "";
 
    /** 
     * 供应商提供医院厂家营业执照
     */
    private String f08_cjyyzz = "";
 
    /** 
     * 供应商提供医院厂家生产许可证
     */
    private String f09_cjscxkz = "";
 
    /** 
     * 供应商提供医院厂家医疗器械注册证
     */
    private String f10_cjylqxzcz = "";
 
    /** 
     * 供应商提供医院厂家耗材商品3C认证
     */
    private String f11_cjhcsprz = "";
 
    /** 
     * 供应商提供医院厂家消毒产品卫生许可证
     */
    private String f12_cjxdcpwsxkz = "";
 
    /** 
     * 审核状态
     */
    private String f13_shzt = "";
 
    /** 
     * 医院接收供应商提供营业执照
     */
    private String n01_yyzz = "";
 
    /** 
     * 医院接收供应商提供经营许可证
     */
    private String n02_jyxkz = "";
 
    /** 
     * 医院接收供应商提供工商税务登记证
     */
    private String n03_gsswdjz = "";
 
    /** 
     * 医院接收供应商提供经销授权书
     */
    private String n04_jxsqs = "";
 
    /** 
     * 医院接收供应商提供销售人员委托书
     */
    private String n05_xsrywts = "";
 
    /** 
     * 医院接收供应商提供售后服务承诺书
     */
    private String n06_shfwcns = "";
 
    /** 
     * 医院接收供应商提供进口商检报告
     */
    private String n07_jksjbg = "";
 
    /** 
     * 医院接收供应商提供厂家营业执照
     */
    private String n08_cjyyzz = "";
 
    /** 
     * 医院接收供应商提供厂家生产许可证
     */
    private String n09_cjscxkz = "";
 
    /** 
     * 医院接收供应商提供厂家医疗器械注册证
     */
    private String n10_cjylqxzcz = "";
 
    /** 
     * 医院接收供应商提供厂家耗材商品3C认证
     */
    private String n11_cjhcsprz = "";
 
    /** 
     * 医院接收供应商提供厂家消毒产品卫生许可证
     */
    private String n12_cjxdcpwsxkz = "";
 
    /** 
     * 获取供应商ID
     *
     * @return K01_GYSID 供应商ID
     */
    public String getK01_gysid() {
        return this.k01_gysid;
    }
 
    /** 
     * 获取医院ID
     *
     * @return K02_YYID 医院ID
     */
    public String getK02_yyid() {
        return this.k02_yyid;
    }
 
    /** 
     * 获取供应商提供医院营业执照
     *
     * @return F01_YYZZ 供应商提供医院营业执照
     */
    public String getF01_yyzz() {
        return this.f01_yyzz;
    }
 
    /** 
     * 获取供应商提供医院经营许可证
     *
     * @return F02_JYXKZ 供应商提供医院经营许可证
     */
    public String getF02_jyxkz() {
        return this.f02_jyxkz;
    }
 
    /** 
     * 获取供应商提供医院工商税务登记证
     *
     * @return F03_GSSWDJZ 供应商提供医院工商税务登记证
     */
    public String getF03_gsswdjz() {
        return this.f03_gsswdjz;
    }
 
    /** 
     * 获取供应商提供医院经销授权书
     *
     * @return F04_JXSQS 供应商提供医院经销授权书
     */
    public String getF04_jxsqs() {
        return this.f04_jxsqs;
    }
 
    /** 
     * 获取供应商提供医院销售人员委托书
     *
     * @return F05_XSRYWTS 供应商提供医院销售人员委托书
     */
    public String getF05_xsrywts() {
        return this.f05_xsrywts;
    }
 
    /** 
     * 获取供应商提供医院售后服务承诺书
     *
     * @return F06_SHFWCNS 供应商提供医院售后服务承诺书
     */
    public String getF06_shfwcns() {
        return this.f06_shfwcns;
    }
 
    /** 
     * 获取供应商提供医院进口商检报告
     *
     * @return F07_JKSJBG 供应商提供医院进口商检报告
     */
    public String getF07_jksjbg() {
        return this.f07_jksjbg;
    }
 
    /** 
     * 获取供应商提供医院厂家营业执照
     *
     * @return F08_CJYYZZ 供应商提供医院厂家营业执照
     */
    public String getF08_cjyyzz() {
        return this.f08_cjyyzz;
    }
 
    /** 
     * 获取供应商提供医院厂家生产许可证
     *
     * @return F09_CJSCXKZ 供应商提供医院厂家生产许可证
     */
    public String getF09_cjscxkz() {
        return this.f09_cjscxkz;
    }
 
    /** 
     * 获取供应商提供医院厂家医疗器械注册证
     *
     * @return F10_CJYLQXZCZ 供应商提供医院厂家医疗器械注册证
     */
    public String getF10_cjylqxzcz() {
        return this.f10_cjylqxzcz;
    }
 
    /** 
     * 获取供应商提供医院厂家耗材商品3C认证
     *
     * @return F11_CJHCSPRZ 供应商提供医院厂家耗材商品3C认证
     */
    public String getF11_cjhcsprz() {
        return this.f11_cjhcsprz;
    }
 
    /** 
     * 获取供应商提供医院厂家消毒产品卫生许可证
     *
     * @return F12_CJXDCPWSXKZ 供应商提供医院厂家消毒产品卫生许可证
     */
    public String getF12_cjxdcpwsxkz() {
        return this.f12_cjxdcpwsxkz;
    }
 
    /** 
     * 获取审核状态
     *
     * @return F13_SHZT 审核状态
     */
    public String getF13_shzt() {
        return this.f13_shzt;
    }
 
    /** 
     * 获取医院接收供应商提供营业执照
     *
     * @return N01_YYZZ 医院接收供应商提供营业执照
     */
    public String getN01_yyzz() {
        return this.n01_yyzz;
    }
 
    /** 
     * 获取医院接收供应商提供经营许可证
     *
     * @return N02_JYXKZ 医院接收供应商提供经营许可证
     */
    public String getN02_jyxkz() {
        return this.n02_jyxkz;
    }
 
    /** 
     * 获取医院接收供应商提供工商税务登记证
     *
     * @return N03_GSSWDJZ 医院接收供应商提供工商税务登记证
     */
    public String getN03_gsswdjz() {
        return this.n03_gsswdjz;
    }
 
    /** 
     * 获取医院接收供应商提供经销授权书
     *
     * @return N04_JXSQS 医院接收供应商提供经销授权书
     */
    public String getN04_jxsqs() {
        return this.n04_jxsqs;
    }
 
    /** 
     * 获取医院接收供应商提供销售人员委托书
     *
     * @return N05_XSRYWTS 医院接收供应商提供销售人员委托书
     */
    public String getN05_xsrywts() {
        return this.n05_xsrywts;
    }
 
    /** 
     * 获取医院接收供应商提供售后服务承诺书
     *
     * @return N06_SHFWCNS 医院接收供应商提供售后服务承诺书
     */
    public String getN06_shfwcns() {
        return this.n06_shfwcns;
    }
 
    /** 
     * 获取医院接收供应商提供进口商检报告
     *
     * @return N07_JKSJBG 医院接收供应商提供进口商检报告
     */
    public String getN07_jksjbg() {
        return this.n07_jksjbg;
    }
 
    /** 
     * 获取医院接收供应商提供厂家营业执照
     *
     * @return N08_CJYYZZ 医院接收供应商提供厂家营业执照
     */
    public String getN08_cjyyzz() {
        return this.n08_cjyyzz;
    }
 
    /** 
     * 获取医院接收供应商提供厂家生产许可证
     *
     * @return N09_CJSCXKZ 医院接收供应商提供厂家生产许可证
     */
    public String getN09_cjscxkz() {
        return this.n09_cjscxkz;
    }
 
    /** 
     * 获取医院接收供应商提供厂家医疗器械注册证
     *
     * @return N10_CJYLQXZCZ 医院接收供应商提供厂家医疗器械注册证
     */
    public String getN10_cjylqxzcz() {
        return this.n10_cjylqxzcz;
    }
 
    /** 
     * 获取医院接收供应商提供厂家耗材商品3C认证
     *
     * @return N11_CJHCSPRZ 医院接收供应商提供厂家耗材商品3C认证
     */
    public String getN11_cjhcsprz() {
        return this.n11_cjhcsprz;
    }
 
    /** 
     * 获取医院接收供应商提供厂家消毒产品卫生许可证
     *
     * @return N12_CJXDCPWSXKZ 医院接收供应商提供厂家消毒产品卫生许可证
     */
    public String getN12_cjxdcpwsxkz() {
        return this.n12_cjxdcpwsxkz;
    }
 
    /** 
     * 设置供应商ID
     *
     * @param K01_GYSID 供应商ID
     */
    public void setK01_gysid(String K01_GYSID) {
        this.k01_gysid = K01_GYSID;
    }
 
    /** 
     * 设置医院ID
     *
     * @param K02_YYID 医院ID
     */
    public void setK02_yyid(String K02_YYID) {
        this.k02_yyid = K02_YYID;
    }
 
    /** 
     * 设置供应商提供医院营业执照
     *
     * @param F01_YYZZ 供应商提供医院营业执照
     */
    public void setF01_yyzz(String F01_YYZZ) {
        this.f01_yyzz = F01_YYZZ;
    }
 
    /** 
     * 设置供应商提供医院经营许可证
     *
     * @param F02_JYXKZ 供应商提供医院经营许可证
     */
    public void setF02_jyxkz(String F02_JYXKZ) {
        this.f02_jyxkz = F02_JYXKZ;
    }
 
    /** 
     * 设置供应商提供医院工商税务登记证
     *
     * @param F03_GSSWDJZ 供应商提供医院工商税务登记证
     */
    public void setF03_gsswdjz(String F03_GSSWDJZ) {
        this.f03_gsswdjz = F03_GSSWDJZ;
    }
 
    /** 
     * 设置供应商提供医院经销授权书
     *
     * @param F04_JXSQS 供应商提供医院经销授权书
     */
    public void setF04_jxsqs(String F04_JXSQS) {
        this.f04_jxsqs = F04_JXSQS;
    }
 
    /** 
     * 设置供应商提供医院销售人员委托书
     *
     * @param F05_XSRYWTS 供应商提供医院销售人员委托书
     */
    public void setF05_xsrywts(String F05_XSRYWTS) {
        this.f05_xsrywts = F05_XSRYWTS;
    }
 
    /** 
     * 设置供应商提供医院售后服务承诺书
     *
     * @param F06_SHFWCNS 供应商提供医院售后服务承诺书
     */
    public void setF06_shfwcns(String F06_SHFWCNS) {
        this.f06_shfwcns = F06_SHFWCNS;
    }
 
    /** 
     * 设置供应商提供医院进口商检报告
     *
     * @param F07_JKSJBG 供应商提供医院进口商检报告
     */
    public void setF07_jksjbg(String F07_JKSJBG) {
        this.f07_jksjbg = F07_JKSJBG;
    }
 
    /** 
     * 设置供应商提供医院厂家营业执照
     *
     * @param F08_CJYYZZ 供应商提供医院厂家营业执照
     */
    public void setF08_cjyyzz(String F08_CJYYZZ) {
        this.f08_cjyyzz = F08_CJYYZZ;
    }
 
    /** 
     * 设置供应商提供医院厂家生产许可证
     *
     * @param F09_CJSCXKZ 供应商提供医院厂家生产许可证
     */
    public void setF09_cjscxkz(String F09_CJSCXKZ) {
        this.f09_cjscxkz = F09_CJSCXKZ;
    }
 
    /** 
     * 设置供应商提供医院厂家医疗器械注册证
     *
     * @param F10_CJYLQXZCZ 供应商提供医院厂家医疗器械注册证
     */
    public void setF10_cjylqxzcz(String F10_CJYLQXZCZ) {
        this.f10_cjylqxzcz = F10_CJYLQXZCZ;
    }
 
    /** 
     * 设置供应商提供医院厂家耗材商品3C认证
     *
     * @param F11_CJHCSPRZ 供应商提供医院厂家耗材商品3C认证
     */
    public void setF11_cjhcsprz(String F11_CJHCSPRZ) {
        this.f11_cjhcsprz = F11_CJHCSPRZ;
    }
 
    /** 
     * 设置供应商提供医院厂家消毒产品卫生许可证
     *
     * @param F12_CJXDCPWSXKZ 供应商提供医院厂家消毒产品卫生许可证
     */
    public void setF12_cjxdcpwsxkz(String F12_CJXDCPWSXKZ) {
        this.f12_cjxdcpwsxkz = F12_CJXDCPWSXKZ;
    }
 
    /** 
     * 设置审核状态
     *
     * @param F13_SHZT 审核状态
     */
    public void setF13_shzt(String F13_SHZT) {
        this.f13_shzt = F13_SHZT;
    }
 
    /** 
     * 设置医院接收供应商提供营业执照
     *
     * @param N01_YYZZ 医院接收供应商提供营业执照
     */
    public void setN01_yyzz(String N01_YYZZ) {
        this.n01_yyzz = N01_YYZZ;
    }
 
    /** 
     * 设置医院接收供应商提供经营许可证
     *
     * @param N02_JYXKZ 医院接收供应商提供经营许可证
     */
    public void setN02_jyxkz(String N02_JYXKZ) {
        this.n02_jyxkz = N02_JYXKZ;
    }
 
    /** 
     * 设置医院接收供应商提供工商税务登记证
     *
     * @param N03_GSSWDJZ 医院接收供应商提供工商税务登记证
     */
    public void setN03_gsswdjz(String N03_GSSWDJZ) {
        this.n03_gsswdjz = N03_GSSWDJZ;
    }
 
    /** 
     * 设置医院接收供应商提供经销授权书
     *
     * @param N04_JXSQS 医院接收供应商提供经销授权书
     */
    public void setN04_jxsqs(String N04_JXSQS) {
        this.n04_jxsqs = N04_JXSQS;
    }
 
    /** 
     * 设置医院接收供应商提供销售人员委托书
     *
     * @param N05_XSRYWTS 医院接收供应商提供销售人员委托书
     */
    public void setN05_xsrywts(String N05_XSRYWTS) {
        this.n05_xsrywts = N05_XSRYWTS;
    }
 
    /** 
     * 设置医院接收供应商提供售后服务承诺书
     *
     * @param N06_SHFWCNS 医院接收供应商提供售后服务承诺书
     */
    public void setN06_shfwcns(String N06_SHFWCNS) {
        this.n06_shfwcns = N06_SHFWCNS;
    }
 
    /** 
     * 设置医院接收供应商提供进口商检报告
     *
     * @param N07_JKSJBG 医院接收供应商提供进口商检报告
     */
    public void setN07_jksjbg(String N07_JKSJBG) {
        this.n07_jksjbg = N07_JKSJBG;
    }
 
    /** 
     * 设置医院接收供应商提供厂家营业执照
     *
     * @param N08_CJYYZZ 医院接收供应商提供厂家营业执照
     */
    public void setN08_cjyyzz(String N08_CJYYZZ) {
        this.n08_cjyyzz = N08_CJYYZZ;
    }
 
    /** 
     * 设置医院接收供应商提供厂家生产许可证
     *
     * @param N09_CJSCXKZ 医院接收供应商提供厂家生产许可证
     */
    public void setN09_cjscxkz(String N09_CJSCXKZ) {
        this.n09_cjscxkz = N09_CJSCXKZ;
    }
 
    /** 
     * 设置医院接收供应商提供厂家医疗器械注册证
     *
     * @param N10_CJYLQXZCZ 医院接收供应商提供厂家医疗器械注册证
     */
    public void setN10_cjylqxzcz(String N10_CJYLQXZCZ) {
        this.n10_cjylqxzcz = N10_CJYLQXZCZ;
    }
 
    /** 
     * 设置医院接收供应商提供厂家耗材商品3C认证
     *
     * @param N11_CJHCSPRZ 医院接收供应商提供厂家耗材商品3C认证
     */
    public void setN11_cjhcsprz(String N11_CJHCSPRZ) {
        this.n11_cjhcsprz = N11_CJHCSPRZ;
    }
 
    /** 
     * 设置医院接收供应商提供厂家消毒产品卫生许可证
     *
     * @param N12_CJXDCPWSXKZ 医院接收供应商提供厂家消毒产品卫生许可证
     */
    public void setN12_cjxdcpwsxkz(String N12_CJXDCPWSXKZ) {
        this.n12_cjxdcpwsxkz = N12_CJXDCPWSXKZ;
    }
 
}
