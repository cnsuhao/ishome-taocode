package org.jfpc.beans.common.MSSUU;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 用户基本信息*/
public class MSSUUDBO extends MyDataBaseObjectSupport
{
    /** 
     * 所属企业ID
     */
    private String k01_ssqyid = "";
 
    /** 
     * 工号
     */
    private String k02_gh = "";
 
    /** 
     * 登录用户名
     */
    private String k03_dlyhm = "";
 
    /** 
     * 登录密码
     */
    private String k04_dlmm = "";
 
    /** 
     * 用户姓名
     */
    private String f01_yhxm = "";
 
    /** 
     * 用户类别
     */
    private String f02_yhlb = "";
 
    /** 
     * 激活状态
     */
    private String f03_jhzt = "";
 
    /** 
     * 用户等级
     */
    private String f04_yhdj = "";
 
    /** 
     * VIP等级
     */
    private String f05_vipdj = "";
 
    /** 
     * 积分
     */
    private String f06_jf = "";
 
    /** 
     * 登录来源
     */
    private String f07_dlly = "";
 
    /** 
     * 最后登录日时
     */
    private String f08_zhdlrs = "";
 
    /** 
     * 最后登录IP
     */
    private String f09_zhdlip = "";
 
    /** 
     * 获取所属企业ID
     *
     * @return K01_SSQYID 所属企业ID
     */
    public String getK01_ssqyid() {
        return this.k01_ssqyid;
    }
 
    /** 
     * 获取工号
     *
     * @return K02_GH 工号
     */
    public String getK02_gh() {
        return this.k02_gh;
    }
 
    /** 
     * 获取登录用户名
     *
     * @return K03_DLYHM 登录用户名
     */
    public String getK03_dlyhm() {
        return this.k03_dlyhm;
    }
 
    /** 
     * 获取登录密码
     *
     * @return K04_DLMM 登录密码
     */
    public String getK04_dlmm() {
        return this.k04_dlmm;
    }
 
    /** 
     * 获取用户姓名
     *
     * @return F01_YHXM 用户姓名
     */
    public String getF01_yhxm() {
        return this.f01_yhxm;
    }
 
    /** 
     * 获取用户类别
     *
     * @return F02_YHLB 用户类别
     */
    public String getF02_yhlb() {
        return this.f02_yhlb;
    }
 
    /** 
     * 获取激活状态
     *
     * @return F03_JHZT 激活状态
     */
    public String getF03_jhzt() {
        return this.f03_jhzt;
    }
 
    /** 
     * 获取用户等级
     *
     * @return F04_YHDJ 用户等级
     */
    public String getF04_yhdj() {
        return this.f04_yhdj;
    }
 
    /** 
     * 获取VIP等级
     *
     * @return F05_VIPDJ VIP等级
     */
    public String getF05_vipdj() {
        return this.f05_vipdj;
    }
 
    /** 
     * 获取积分
     *
     * @return F06_JF 积分
     */
    public String getF06_jf() {
        return this.f06_jf;
    }
 
    /** 
     * 获取登录来源
     *
     * @return F07_DLLY 登录来源
     */
    public String getF07_dlly() {
        return this.f07_dlly;
    }
 
    /** 
     * 获取最后登录日时
     *
     * @return F08_ZHDLRS 最后登录日时
     */
    public String getF08_zhdlrs() {
        return this.f08_zhdlrs;
    }
 
    /** 
     * 获取最后登录IP
     *
     * @return F09_ZHDLIP 最后登录IP
     */
    public String getF09_zhdlip() {
        return this.f09_zhdlip;
    }
 
    /** 
     * 设置所属企业ID
     *
     * @param K01_SSQYID 所属企业ID
     */
    public void setK01_ssqyid(String K01_SSQYID) {
        this.k01_ssqyid = K01_SSQYID;
    }
 
    /** 
     * 设置工号
     *
     * @param K02_GH 工号
     */
    public void setK02_gh(String K02_GH) {
        this.k02_gh = K02_GH;
    }
 
    /** 
     * 设置登录用户名
     *
     * @param K03_DLYHM 登录用户名
     */
    public void setK03_dlyhm(String K03_DLYHM) {
        this.k03_dlyhm = K03_DLYHM;
    }
 
    /** 
     * 设置登录密码
     *
     * @param K04_DLMM 登录密码
     */
    public void setK04_dlmm(String K04_DLMM) {
        this.k04_dlmm = K04_DLMM;
    }
 
    /** 
     * 设置用户姓名
     *
     * @param F01_YHXM 用户姓名
     */
    public void setF01_yhxm(String F01_YHXM) {
        this.f01_yhxm = F01_YHXM;
    }
 
    /** 
     * 设置用户类别
     *
     * @param F02_YHLB 用户类别
     */
    public void setF02_yhlb(String F02_YHLB) {
        this.f02_yhlb = F02_YHLB;
    }
 
    /** 
     * 设置激活状态
     *
     * @param F03_JHZT 激活状态
     */
    public void setF03_jhzt(String F03_JHZT) {
        this.f03_jhzt = F03_JHZT;
    }
 
    /** 
     * 设置用户等级
     *
     * @param F04_YHDJ 用户等级
     */
    public void setF04_yhdj(String F04_YHDJ) {
        this.f04_yhdj = F04_YHDJ;
    }
 
    /** 
     * 设置VIP等级
     *
     * @param F05_VIPDJ VIP等级
     */
    public void setF05_vipdj(String F05_VIPDJ) {
        this.f05_vipdj = F05_VIPDJ;
    }
 
    /** 
     * 设置积分
     *
     * @param F06_JF 积分
     */
    public void setF06_jf(String F06_JF) {
        this.f06_jf = F06_JF;
    }
 
    /** 
     * 设置登录来源
     *
     * @param F07_DLLY 登录来源
     */
    public void setF07_dlly(String F07_DLLY) {
        this.f07_dlly = F07_DLLY;
    }
 
    /** 
     * 设置最后登录日时
     *
     * @param F08_ZHDLRS 最后登录日时
     */
    public void setF08_zhdlrs(String F08_ZHDLRS) {
        this.f08_zhdlrs = F08_ZHDLRS;
    }
 
    /** 
     * 设置最后登录IP
     *
     * @param F09_ZHDLIP 最后登录IP
     */
    public void setF09_zhdlip(String F09_ZHDLIP) {
        this.f09_zhdlip = F09_ZHDLIP;
    }
 
}
