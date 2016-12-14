package org.jfpc.beans.platform.MS1AA;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业扩展信息*/
public class MS1AADBO extends MyDataBaseObjectSupport
{
    /** 
     * 系统密钥
     */
    private String k01_xtmy = "";
 
    /** 
     * 组织机构编号
     */
    private String k02_zzjg = "";
 
    /** 
     * 超级管理员ID（默认）
     */
    private String k03_cjgly = "";
 
    /** 
     * 上级企业ID
     */
    private String k04_sjqyid = "";
 
    /** 
     * 营业执照编号
     */
    private String f01_yyzzbh = "";
 
    /** 
     * 经营许可证编号
     */
    private String f02_jyxkzbh = "";
 
    /** 
     * 工商税务登记证编号
     */
    private String f03_gsswdjzbh = "";
 
    /** 
     * 当前负责人ID
     */
    private String f04_dqfzrid = "";
 
    /** 
     * 企业类别
     */
    private String f04_qylb = "";
 
    /** 
     * 企业等级
     */
    private String f05_yedj = "";
 
    /** 
     * VIP等级
     */
    private String f06_vipdj = "";
 
    /** 
     * 积分
     */
    private String f07_jf = "";
 
    /** 
     * 在职员工数目
     */
    private String f08_zzygsm = "";
 
    /** 
     * 允许登录用户数
     */
    private String f09_yxdlyhs = "";
 
    /** 
     * 当前登录用户数
     */
    private String f10_dqdlyhs = "";
 
    /** 
     * 获取系统密钥
     *
     * @return K01_XTMY 系统密钥
     */
    public String getK01_xtmy() {
        return this.k01_xtmy;
    }
 
    /** 
     * 获取组织机构编号
     *
     * @return K02_ZZJG 组织机构编号
     */
    public String getK02_zzjg() {
        return this.k02_zzjg;
    }
 
    /** 
     * 获取超级管理员ID（默认）
     *
     * @return K03_CJGLY 超级管理员ID（默认）
     */
    public String getK03_cjgly() {
        return this.k03_cjgly;
    }
 
    /** 
     * 获取上级企业ID
     *
     * @return K04_SJQYID 上级企业ID
     */
    public String getK04_sjqyid() {
        return this.k04_sjqyid;
    }
 
    /** 
     * 获取营业执照编号
     *
     * @return F01_YYZZBH 营业执照编号
     */
    public String getF01_yyzzbh() {
        return this.f01_yyzzbh;
    }
 
    /** 
     * 获取经营许可证编号
     *
     * @return F02_JYXKZBH 经营许可证编号
     */
    public String getF02_jyxkzbh() {
        return this.f02_jyxkzbh;
    }
 
    /** 
     * 获取工商税务登记证编号
     *
     * @return F03_GSSWDJZBH 工商税务登记证编号
     */
    public String getF03_gsswdjzbh() {
        return this.f03_gsswdjzbh;
    }
 
    /** 
     * 获取当前负责人ID
     *
     * @return F04_DQFZRID 当前负责人ID
     */
    public String getF04_dqfzrid() {
        return this.f04_dqfzrid;
    }
 
    /** 
     * 获取企业类别
     *
     * @return F04_QYLB 企业类别
     */
    public String getF04_qylb() {
        return this.f04_qylb;
    }
 
    /** 
     * 获取企业等级
     *
     * @return F05_YEDJ 企业等级
     */
    public String getF05_yedj() {
        return this.f05_yedj;
    }
 
    /** 
     * 获取VIP等级
     *
     * @return F06_VIPDJ VIP等级
     */
    public String getF06_vipdj() {
        return this.f06_vipdj;
    }
 
    /** 
     * 获取积分
     *
     * @return F07_JF 积分
     */
    public String getF07_jf() {
        return this.f07_jf;
    }
 
    /** 
     * 获取在职员工数目
     *
     * @return F08_ZZYGSM 在职员工数目
     */
    public String getF08_zzygsm() {
        return this.f08_zzygsm;
    }
 
    /** 
     * 获取允许登录用户数
     *
     * @return F09_YXDLYHS 允许登录用户数
     */
    public String getF09_yxdlyhs() {
        return this.f09_yxdlyhs;
    }
 
    /** 
     * 获取当前登录用户数
     *
     * @return F10_DQDLYHS 当前登录用户数
     */
    public String getF10_dqdlyhs() {
        return this.f10_dqdlyhs;
    }
 
    /** 
     * 设置系统密钥
     *
     * @param K01_XTMY 系统密钥
     */
    public void setK01_xtmy(String K01_XTMY) {
        this.k01_xtmy = K01_XTMY;
    }
 
    /** 
     * 设置组织机构编号
     *
     * @param K02_ZZJG 组织机构编号
     */
    public void setK02_zzjg(String K02_ZZJG) {
        this.k02_zzjg = K02_ZZJG;
    }
 
    /** 
     * 设置超级管理员ID（默认）
     *
     * @param K03_CJGLY 超级管理员ID（默认）
     */
    public void setK03_cjgly(String K03_CJGLY) {
        this.k03_cjgly = K03_CJGLY;
    }
 
    /** 
     * 设置上级企业ID
     *
     * @param K04_SJQYID 上级企业ID
     */
    public void setK04_sjqyid(String K04_SJQYID) {
        this.k04_sjqyid = K04_SJQYID;
    }
 
    /** 
     * 设置营业执照编号
     *
     * @param F01_YYZZBH 营业执照编号
     */
    public void setF01_yyzzbh(String F01_YYZZBH) {
        this.f01_yyzzbh = F01_YYZZBH;
    }
 
    /** 
     * 设置经营许可证编号
     *
     * @param F02_JYXKZBH 经营许可证编号
     */
    public void setF02_jyxkzbh(String F02_JYXKZBH) {
        this.f02_jyxkzbh = F02_JYXKZBH;
    }
 
    /** 
     * 设置工商税务登记证编号
     *
     * @param F03_GSSWDJZBH 工商税务登记证编号
     */
    public void setF03_gsswdjzbh(String F03_GSSWDJZBH) {
        this.f03_gsswdjzbh = F03_GSSWDJZBH;
    }
 
    /** 
     * 设置当前负责人ID
     *
     * @param F04_DQFZRID 当前负责人ID
     */
    public void setF04_dqfzrid(String F04_DQFZRID) {
        this.f04_dqfzrid = F04_DQFZRID;
    }
 
    /** 
     * 设置企业类别
     *
     * @param F04_QYLB 企业类别
     */
    public void setF04_qylb(String F04_QYLB) {
        this.f04_qylb = F04_QYLB;
    }
 
    /** 
     * 设置企业等级
     *
     * @param F05_YEDJ 企业等级
     */
    public void setF05_yedj(String F05_YEDJ) {
        this.f05_yedj = F05_YEDJ;
    }
 
    /** 
     * 设置VIP等级
     *
     * @param F06_VIPDJ VIP等级
     */
    public void setF06_vipdj(String F06_VIPDJ) {
        this.f06_vipdj = F06_VIPDJ;
    }
 
    /** 
     * 设置积分
     *
     * @param F07_JF 积分
     */
    public void setF07_jf(String F07_JF) {
        this.f07_jf = F07_JF;
    }
 
    /** 
     * 设置在职员工数目
     *
     * @param F08_ZZYGSM 在职员工数目
     */
    public void setF08_zzygsm(String F08_ZZYGSM) {
        this.f08_zzygsm = F08_ZZYGSM;
    }
 
    /** 
     * 设置允许登录用户数
     *
     * @param F09_YXDLYHS 允许登录用户数
     */
    public void setF09_yxdlyhs(String F09_YXDLYHS) {
        this.f09_yxdlyhs = F09_YXDLYHS;
    }
 
    /** 
     * 设置当前登录用户数
     *
     * @param F10_DQDLYHS 当前登录用户数
     */
    public void setF10_dqdlyhs(String F10_DQDLYHS) {
        this.f10_dqdlyhs = F10_DQDLYHS;
    }
 
}
