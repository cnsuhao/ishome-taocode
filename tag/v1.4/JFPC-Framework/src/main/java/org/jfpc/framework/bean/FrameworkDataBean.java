package org.jfpc.framework.bean;

import javax.inject.Named;

/**
 * 基底共通
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Named
public class FrameworkDataBean extends ObjectBean {

    /** 
     * 系统数据唯一识别ID（固定主键）
     */
    private String puk = "";
 
    /** 
     * 备注说明
     */
    private String bbb = "";
 
    /** 
     * 备用1
     */
    private String fb1 = "";
 
    /** 
     * 备用2
     */
    private String fb2 = "";
 
    /** 
     * 备用3
     */
    private String fb3 = "";
 
    /** 
     * 备用4
     */
    private String fb4 = "";
 
    /** 
     * 备用5
     */
    private String fb5 = "";
 
    /** 
     * 扩展1
     */
    private String eb1 = "";
 
    /** 
     * 扩展2
     */
    private String eb2 = "";
 
    /** 
     * 扩展3
     */
    private String eb3 = "";
 
    /** 
     * 扩展4
     */
    private String eb4 = "";
 
    /** 
     * 扩展5
     */
    private String eb5 = "";
 
    /** 
     * 所在组织
     */
    private String ggg = "";
 
    /** 
     * 企业ID
     */
    private String ppp = "";
 
    /** 
     * 有效标识
     */
    private String ddd = "";
 
    /** 
     * 创建时间
     */
    private String cc1 = "";
 
    /** 
     * 创建者
     */
    private String cc2 = "";
 
    /** 
     * 最后更新时间
     */
    private String uu1 = "";
 
    /** 
     * 最后更新者
     */
    private String uu2 = "";
 
    /** 
     * 获取系统数据唯一识别ID（固定主键）
     *
     * @return PUK 系统数据唯一识别ID（固定主键）
     */
    public String getPuk() {
        return this.puk;
    }
 
    /** 
     * 获取备注说明
     *
     * @return BBB 备注说明
     */
    public String getBbb() {
        return this.bbb;
    }
 
    /** 
     * 获取备用1
     *
     * @return FB1 备用1
     */
    public String getFb1() {
        return this.fb1;
    }
 
    /** 
     * 获取备用2
     *
     * @return FB2 备用2
     */
    public String getFb2() {
        return this.fb2;
    }
 
    /** 
     * 获取备用3
     *
     * @return FB3 备用3
     */
    public String getFb3() {
        return this.fb3;
    }
 
    /** 
     * 获取备用4
     *
     * @return FB4 备用4
     */
    public String getFb4() {
        return this.fb4;
    }
 
    /** 
     * 获取备用5
     *
     * @return FB5 备用5
     */
    public String getFb5() {
        return this.fb5;
    }
 
    /** 
     * 获取扩展1
     *
     * @return EB1 扩展1
     */
    public String getEb1() {
        return this.eb1;
    }
 
    /** 
     * 获取扩展2
     *
     * @return EB2 扩展2
     */
    public String getEb2() {
        return this.eb2;
    }
 
    /** 
     * 获取扩展3
     *
     * @return EB3 扩展3
     */
    public String getEb3() {
        return this.eb3;
    }
 
    /** 
     * 获取扩展4
     *
     * @return EB4 扩展4
     */
    public String getEb4() {
        return this.eb4;
    }
 
    /** 
     * 获取扩展5
     *
     * @return EB5 扩展5
     */
    public String getEb5() {
        return this.eb5;
    }
 
    /** 
     * 获取所在组织
     *
     * @return GGG 所在组织
     */
    public String getGgg() {
        return this.ggg;
    }
 
    /** 
     * 获取企业ID
     *
     * @return PPP 企业ID
     */
    public String getPpp() {
        return this.ppp;
    }
 
    /** 
     * 获取有效标识
     *
     * @return DDD 有效标识
     */
    public String getDdd() {
        return this.ddd;
    }
 
    /** 
     * 获取创建时间
     *
     * @return CC1 创建时间
     */
    public String getCc1() {
        return this.cc1;
    }
 
    /** 
     * 获取创建者
     *
     * @return CC2 创建者
     */
    public String getCc2() {
        return this.cc2;
    }
 
    /** 
     * 获取最后更新时间
     *
     * @return UU1 最后更新时间
     */
    public String getUu1() {
        return this.uu1;
    }
 
    /** 
     * 获取最后更新者
     *
     * @return UU2 最后更新者
     */
    public String getUu2() {
        return this.uu2;
    }
 
    /** 
     * 设置系统数据唯一识别ID（固定主键）
     *
     * @param PUK 系统数据唯一识别ID（固定主键）
     */
    public void setPuk(String PUK) {
        this.puk = PUK;
    }
 
    /** 
     * 设置备注说明
     *
     * @param BBB 备注说明
     */
    public void setBbb(String BBB) {
        this.bbb = BBB;
    }
 
    /** 
     * 设置备用1
     *
     * @param FB1 备用1
     */
    public void setFb1(String FB1) {
        this.fb1 = FB1;
    }
 
    /** 
     * 设置备用2
     *
     * @param FB2 备用2
     */
    public void setFb2(String FB2) {
        this.fb2 = FB2;
    }
 
    /** 
     * 设置备用3
     *
     * @param FB3 备用3
     */
    public void setFb3(String FB3) {
        this.fb3 = FB3;
    }
 
    /** 
     * 设置备用4
     *
     * @param FB4 备用4
     */
    public void setFb4(String FB4) {
        this.fb4 = FB4;
    }
 
    /** 
     * 设置备用5
     *
     * @param FB5 备用5
     */
    public void setFb5(String FB5) {
        this.fb5 = FB5;
    }
 
    /** 
     * 设置扩展1
     *
     * @param EB1 扩展1
     */
    public void setEb1(String EB1) {
        this.eb1 = EB1;
    }
 
    /** 
     * 设置扩展2
     *
     * @param EB2 扩展2
     */
    public void setEb2(String EB2) {
        this.eb2 = EB2;
    }
 
    /** 
     * 设置扩展3
     *
     * @param EB3 扩展3
     */
    public void setEb3(String EB3) {
        this.eb3 = EB3;
    }
 
    /** 
     * 设置扩展4
     *
     * @param EB4 扩展4
     */
    public void setEb4(String EB4) {
        this.eb4 = EB4;
    }
 
    /** 
     * 设置扩展5
     *
     * @param EB5 扩展5
     */
    public void setEb5(String EB5) {
        this.eb5 = EB5;
    }
 
    /** 
     * 设置所在组织
     *
     * @param GGG 所在组织
     */
    public void setGgg(String GGG) {
        this.ggg = GGG;
    }
 
    /** 
     * 设置企业ID
     *
     * @param PPP 企业ID
     */
    public void setPpp(String PPP) {
        this.ppp = PPP;
    }
 
    /** 
     * 设置有效标识
     *
     * @param DDD 有效标识
     */
    public void setDdd(String DDD) {
        this.ddd = DDD;
    }
 
    /** 
     * 设置创建时间
     *
     * @param CC1 创建时间
     */
    public void setCc1(String CC1) {
        this.cc1 = CC1;
    }
 
    /** 
     * 设置创建者
     *
     * @param CC2 创建者
     */
    public void setCc2(String CC2) {
        this.cc2 = CC2;
    }
 
    /** 
     * 设置最后更新时间
     *
     * @param UU1 最后更新时间
     */
    public void setUu1(String UU1) {
        this.uu1 = UU1;
    }
 
    /** 
     * 设置最后更新者
     *
     * @param UU2 最后更新者
     */
    public void setUu2(String UU2) {
        this.uu2 = UU2;
    }
}
