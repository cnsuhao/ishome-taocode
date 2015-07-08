package org.jfpc.beans.platform.MS1C2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业组织机构定义*/
public class MS1C2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 机构编号
     */
    private String k01_zzjgbh = "";
 
    /** 
     * 上级机构ID
     */
    private String k02_zzjgid = "";
 
    /** 
     * 机构全称
     */
    private String f01_jgqc = "";
 
    /** 
     * 机构简称
     */
    private String f02_jgjc = "";
 
    /** 
     * 负责人姓名
     */
    private String f03_fzr_xm = "";
 
    /** 
     * 负责人手机
     */
    private String f04_fzr_sj = "";
 
    /** 
     * 负责人座机
     */
    private String f05_fzr_zj = "";
 
    /** 
     * 成立日期
     */
    private String f06_clrq = "";
 
    /** 
     * 拥有员工数
     */
    private String f07_yyygs = "";
 
    /** 
     * 机构类别
     */
    private String f08_jglb = "";
 
    /** 
     * 排列顺序
     */
    private String f09_plsx = "";
 
    /** 
     * 获取机构编号
     *
     * @return K01_ZZJGBH 机构编号
     */
    public String getK01_zzjgbh() {
        return this.k01_zzjgbh;
    }
 
    /** 
     * 获取上级机构ID
     *
     * @return K02_ZZJGID 上级机构ID
     */
    public String getK02_zzjgid() {
        return this.k02_zzjgid;
    }
 
    /** 
     * 获取机构全称
     *
     * @return F01_JGQC 机构全称
     */
    public String getF01_jgqc() {
        return this.f01_jgqc;
    }
 
    /** 
     * 获取机构简称
     *
     * @return F02_JGJC 机构简称
     */
    public String getF02_jgjc() {
        return this.f02_jgjc;
    }
 
    /** 
     * 获取负责人姓名
     *
     * @return F03_FZR_XM 负责人姓名
     */
    public String getF03_fzr_xm() {
        return this.f03_fzr_xm;
    }
 
    /** 
     * 获取负责人手机
     *
     * @return F04_FZR_SJ 负责人手机
     */
    public String getF04_fzr_sj() {
        return this.f04_fzr_sj;
    }
 
    /** 
     * 获取负责人座机
     *
     * @return F05_FZR_ZJ 负责人座机
     */
    public String getF05_fzr_zj() {
        return this.f05_fzr_zj;
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
     * 获取拥有员工数
     *
     * @return F07_YYYGS 拥有员工数
     */
    public String getF07_yyygs() {
        return this.f07_yyygs;
    }
 
    /** 
     * 获取机构类别
     *
     * @return F08_JGLB 机构类别
     */
    public String getF08_jglb() {
        return this.f08_jglb;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F09_PLSX 排列顺序
     */
    public String getF09_plsx() {
        return this.f09_plsx;
    }
 
    /** 
     * 设置机构编号
     *
     * @param K01_ZZJGBH 机构编号
     */
    public void setK01_zzjgbh(String K01_ZZJGBH) {
        this.k01_zzjgbh = K01_ZZJGBH;
    }
 
    /** 
     * 设置上级机构ID
     *
     * @param K02_ZZJGID 上级机构ID
     */
    public void setK02_zzjgid(String K02_ZZJGID) {
        this.k02_zzjgid = K02_ZZJGID;
    }
 
    /** 
     * 设置机构全称
     *
     * @param F01_JGQC 机构全称
     */
    public void setF01_jgqc(String F01_JGQC) {
        this.f01_jgqc = F01_JGQC;
    }
 
    /** 
     * 设置机构简称
     *
     * @param F02_JGJC 机构简称
     */
    public void setF02_jgjc(String F02_JGJC) {
        this.f02_jgjc = F02_JGJC;
    }
 
    /** 
     * 设置负责人姓名
     *
     * @param F03_FZR_XM 负责人姓名
     */
    public void setF03_fzr_xm(String F03_FZR_XM) {
        this.f03_fzr_xm = F03_FZR_XM;
    }
 
    /** 
     * 设置负责人手机
     *
     * @param F04_FZR_SJ 负责人手机
     */
    public void setF04_fzr_sj(String F04_FZR_SJ) {
        this.f04_fzr_sj = F04_FZR_SJ;
    }
 
    /** 
     * 设置负责人座机
     *
     * @param F05_FZR_ZJ 负责人座机
     */
    public void setF05_fzr_zj(String F05_FZR_ZJ) {
        this.f05_fzr_zj = F05_FZR_ZJ;
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
     * 设置拥有员工数
     *
     * @param F07_YYYGS 拥有员工数
     */
    public void setF07_yyygs(String F07_YYYGS) {
        this.f07_yyygs = F07_YYYGS;
    }
 
    /** 
     * 设置机构类别
     *
     * @param F08_JGLB 机构类别
     */
    public void setF08_jglb(String F08_JGLB) {
        this.f08_jglb = F08_JGLB;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F09_PLSX 排列顺序
     */
    public void setF09_plsx(String F09_PLSX) {
        this.f09_plsx = F09_PLSX;
    }
 
}
