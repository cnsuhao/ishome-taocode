package org.jfpc.beans.platform.CS1B2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业人员基本信息*/
public class CS1B2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 姓名
     */
    private String f01_xm = "";
 
    /** 
     * 性别
     */
    private String f02_xb = "";
 
    /** 
     * 出生日期
     */
    private String f03_csrq = "";
 
    /** 
     * 实名认证结果状态
     */
    private String f04_smrz_jg = "";
 
    /** 
     * 实名认证（姓名）
     */
    private String f05_smrz_xm = "";
 
    /** 
     * 实名认证（身份证ID）
     */
    private String f06_smrz_sfz = "";
 
    /** 
     * 身体状况
     */
    private String f07_stzk = "";
 
    /** 
     * 工作状况
     */
    private String f08_gzzk = "";
 
    /** 
     * 婚姻状况
     */
    private String f09_hyzk = "";
 
    /** 
     * 政治面貌
     */
    private String f10_zzmm = "";
 
    /** 
     * 当前居住地址
     */
    private String f11_dqjzdz = "";
 
    /** 
     * 户口所在地
     */
    private String f12_hkszd = "";
 
    /** 
     * 紧急联系人姓名
     */
    private String f13_jjlxr_xm = "";
 
    /** 
     * 紧急联系人电话
     */
    private String f14_jjlxr_dh = "";
 
    /** 
     * 移动电话
     */
    private String f15_yddh = "";
 
    /** 
     * 办公电话
     */
    private String f16_bgdh = "";
 
    /** 
     * 住宅电话
     */
    private String f17_zzdh = "";
 
    /** 
     * 学历
     */
    private String f18_xl = "";
 
    /** 
     * QQ
     */
    private String f19_qq = "";
 
    /** 
     * 邮件
     */
    private String f20_mail = "";
 
    /** 
     * 获取姓名
     *
     * @return F01_XM 姓名
     */
    public String getF01_xm() {
        return this.f01_xm;
    }
 
    /** 
     * 获取性别
     *
     * @return F02_XB 性别
     */
    public String getF02_xb() {
        return this.f02_xb;
    }
 
    /** 
     * 获取出生日期
     *
     * @return F03_CSRQ 出生日期
     */
    public String getF03_csrq() {
        return this.f03_csrq;
    }
 
    /** 
     * 获取实名认证结果状态
     *
     * @return F04_SMRZ_JG 实名认证结果状态
     */
    public String getF04_smrz_jg() {
        return this.f04_smrz_jg;
    }
 
    /** 
     * 获取实名认证（姓名）
     *
     * @return F05_SMRZ_XM 实名认证（姓名）
     */
    public String getF05_smrz_xm() {
        return this.f05_smrz_xm;
    }
 
    /** 
     * 获取实名认证（身份证ID）
     *
     * @return F06_SMRZ_SFZ 实名认证（身份证ID）
     */
    public String getF06_smrz_sfz() {
        return this.f06_smrz_sfz;
    }
 
    /** 
     * 获取身体状况
     *
     * @return F07_STZK 身体状况
     */
    public String getF07_stzk() {
        return this.f07_stzk;
    }
 
    /** 
     * 获取工作状况
     *
     * @return F08_GZZK 工作状况
     */
    public String getF08_gzzk() {
        return this.f08_gzzk;
    }
 
    /** 
     * 获取婚姻状况
     *
     * @return F09_HYZK 婚姻状况
     */
    public String getF09_hyzk() {
        return this.f09_hyzk;
    }
 
    /** 
     * 获取政治面貌
     *
     * @return F10_ZZMM 政治面貌
     */
    public String getF10_zzmm() {
        return this.f10_zzmm;
    }
 
    /** 
     * 获取当前居住地址
     *
     * @return F11_DQJZDZ 当前居住地址
     */
    public String getF11_dqjzdz() {
        return this.f11_dqjzdz;
    }
 
    /** 
     * 获取户口所在地
     *
     * @return F12_HKSZD 户口所在地
     */
    public String getF12_hkszd() {
        return this.f12_hkszd;
    }
 
    /** 
     * 获取紧急联系人姓名
     *
     * @return F13_JJLXR_XM 紧急联系人姓名
     */
    public String getF13_jjlxr_xm() {
        return this.f13_jjlxr_xm;
    }
 
    /** 
     * 获取紧急联系人电话
     *
     * @return F14_JJLXR_DH 紧急联系人电话
     */
    public String getF14_jjlxr_dh() {
        return this.f14_jjlxr_dh;
    }
 
    /** 
     * 获取移动电话
     *
     * @return F15_YDDH 移动电话
     */
    public String getF15_yddh() {
        return this.f15_yddh;
    }
 
    /** 
     * 获取办公电话
     *
     * @return F16_BGDH 办公电话
     */
    public String getF16_bgdh() {
        return this.f16_bgdh;
    }
 
    /** 
     * 获取住宅电话
     *
     * @return F17_ZZDH 住宅电话
     */
    public String getF17_zzdh() {
        return this.f17_zzdh;
    }
 
    /** 
     * 获取学历
     *
     * @return F18_XL 学历
     */
    public String getF18_xl() {
        return this.f18_xl;
    }
 
    /** 
     * 获取QQ
     *
     * @return F19_QQ QQ
     */
    public String getF19_qq() {
        return this.f19_qq;
    }
 
    /** 
     * 获取邮件
     *
     * @return F20_MAIL 邮件
     */
    public String getF20_mail() {
        return this.f20_mail;
    }
 
    /** 
     * 设置姓名
     *
     * @param F01_XM 姓名
     */
    public void setF01_xm(String F01_XM) {
        this.f01_xm = F01_XM;
    }
 
    /** 
     * 设置性别
     *
     * @param F02_XB 性别
     */
    public void setF02_xb(String F02_XB) {
        this.f02_xb = F02_XB;
    }
 
    /** 
     * 设置出生日期
     *
     * @param F03_CSRQ 出生日期
     */
    public void setF03_csrq(String F03_CSRQ) {
        this.f03_csrq = F03_CSRQ;
    }
 
    /** 
     * 设置实名认证结果状态
     *
     * @param F04_SMRZ_JG 实名认证结果状态
     */
    public void setF04_smrz_jg(String F04_SMRZ_JG) {
        this.f04_smrz_jg = F04_SMRZ_JG;
    }
 
    /** 
     * 设置实名认证（姓名）
     *
     * @param F05_SMRZ_XM 实名认证（姓名）
     */
    public void setF05_smrz_xm(String F05_SMRZ_XM) {
        this.f05_smrz_xm = F05_SMRZ_XM;
    }
 
    /** 
     * 设置实名认证（身份证ID）
     *
     * @param F06_SMRZ_SFZ 实名认证（身份证ID）
     */
    public void setF06_smrz_sfz(String F06_SMRZ_SFZ) {
        this.f06_smrz_sfz = F06_SMRZ_SFZ;
    }
 
    /** 
     * 设置身体状况
     *
     * @param F07_STZK 身体状况
     */
    public void setF07_stzk(String F07_STZK) {
        this.f07_stzk = F07_STZK;
    }
 
    /** 
     * 设置工作状况
     *
     * @param F08_GZZK 工作状况
     */
    public void setF08_gzzk(String F08_GZZK) {
        this.f08_gzzk = F08_GZZK;
    }
 
    /** 
     * 设置婚姻状况
     *
     * @param F09_HYZK 婚姻状况
     */
    public void setF09_hyzk(String F09_HYZK) {
        this.f09_hyzk = F09_HYZK;
    }
 
    /** 
     * 设置政治面貌
     *
     * @param F10_ZZMM 政治面貌
     */
    public void setF10_zzmm(String F10_ZZMM) {
        this.f10_zzmm = F10_ZZMM;
    }
 
    /** 
     * 设置当前居住地址
     *
     * @param F11_DQJZDZ 当前居住地址
     */
    public void setF11_dqjzdz(String F11_DQJZDZ) {
        this.f11_dqjzdz = F11_DQJZDZ;
    }
 
    /** 
     * 设置户口所在地
     *
     * @param F12_HKSZD 户口所在地
     */
    public void setF12_hkszd(String F12_HKSZD) {
        this.f12_hkszd = F12_HKSZD;
    }
 
    /** 
     * 设置紧急联系人姓名
     *
     * @param F13_JJLXR_XM 紧急联系人姓名
     */
    public void setF13_jjlxr_xm(String F13_JJLXR_XM) {
        this.f13_jjlxr_xm = F13_JJLXR_XM;
    }
 
    /** 
     * 设置紧急联系人电话
     *
     * @param F14_JJLXR_DH 紧急联系人电话
     */
    public void setF14_jjlxr_dh(String F14_JJLXR_DH) {
        this.f14_jjlxr_dh = F14_JJLXR_DH;
    }
 
    /** 
     * 设置移动电话
     *
     * @param F15_YDDH 移动电话
     */
    public void setF15_yddh(String F15_YDDH) {
        this.f15_yddh = F15_YDDH;
    }
 
    /** 
     * 设置办公电话
     *
     * @param F16_BGDH 办公电话
     */
    public void setF16_bgdh(String F16_BGDH) {
        this.f16_bgdh = F16_BGDH;
    }
 
    /** 
     * 设置住宅电话
     *
     * @param F17_ZZDH 住宅电话
     */
    public void setF17_zzdh(String F17_ZZDH) {
        this.f17_zzdh = F17_ZZDH;
    }
 
    /** 
     * 设置学历
     *
     * @param F18_XL 学历
     */
    public void setF18_xl(String F18_XL) {
        this.f18_xl = F18_XL;
    }
 
    /** 
     * 设置QQ
     *
     * @param F19_QQ QQ
     */
    public void setF19_qq(String F19_QQ) {
        this.f19_qq = F19_QQ;
    }
 
    /** 
     * 设置邮件
     *
     * @param F20_MAIL 邮件
     */
    public void setF20_mail(String F20_MAIL) {
        this.f20_mail = F20_MAIL;
    }
 
}
