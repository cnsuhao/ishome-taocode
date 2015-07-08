package org.jfpc.platform.CS0D1.dbo;
import javax.inject.Named;

import org.jfpc.base.support.MyDataBaseObjectSupport;
 
@Named
/** 内部消息*/
public class CS0D1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 企业ID
     */
    private String k01_qyid = "";
 
    /** 
     * 发件人ID
     */
    private String k02_fjrid = "";
 
    /** 
     * 收件人ID
     */
    private String k03_sjrid = "";
 
    /** 
     * 发件人昵称
     */
    private String f01_fjrnc = "";
 
    /** 
     * 收件人昵称
     */
    private String f02_sjrnc = "";
 
    /** 
     * 标题
     */
    private String f03_bt = "";
 
    /** 
     * 紧急度
     */
    private String f04_jjd = "";
 
    /** 
     * 重要度
     */
    private String f05_zyd = "";
 
    /** 
     * 获取企业ID
     *
     * @return K01_QYID 企业ID
     */
    public String getK01_qyid() {
        return this.k01_qyid;
    }
 
    /** 
     * 获取发件人ID
     *
     * @return K02_FJRID 发件人ID
     */
    public String getK02_fjrid() {
        return this.k02_fjrid;
    }
 
    /** 
     * 获取收件人ID
     *
     * @return K03_SJRID 收件人ID
     */
    public String getK03_sjrid() {
        return this.k03_sjrid;
    }
 
    /** 
     * 获取发件人昵称
     *
     * @return F01_FJRNC 发件人昵称
     */
    public String getF01_fjrnc() {
        return this.f01_fjrnc;
    }
 
    /** 
     * 获取收件人昵称
     *
     * @return F02_SJRNC 收件人昵称
     */
    public String getF02_sjrnc() {
        return this.f02_sjrnc;
    }
 
    /** 
     * 获取标题
     *
     * @return F03_BT 标题
     */
    public String getF03_bt() {
        return this.f03_bt;
    }
 
    /** 
     * 获取紧急度
     *
     * @return F04_JJD 紧急度
     */
    public String getF04_jjd() {
        return this.f04_jjd;
    }
 
    /** 
     * 获取重要度
     *
     * @return F05_ZYD 重要度
     */
    public String getF05_zyd() {
        return this.f05_zyd;
    }
 
    /** 
     * 设置企业ID
     *
     * @param K01_QYID 企业ID
     */
    public void setK01_qyid(String K01_QYID) {
        this.k01_qyid = K01_QYID;
    }
 
    /** 
     * 设置发件人ID
     *
     * @param K02_FJRID 发件人ID
     */
    public void setK02_fjrid(String K02_FJRID) {
        this.k02_fjrid = K02_FJRID;
    }
 
    /** 
     * 设置收件人ID
     *
     * @param K03_SJRID 收件人ID
     */
    public void setK03_sjrid(String K03_SJRID) {
        this.k03_sjrid = K03_SJRID;
    }
 
    /** 
     * 设置发件人昵称
     *
     * @param F01_FJRNC 发件人昵称
     */
    public void setF01_fjrnc(String F01_FJRNC) {
        this.f01_fjrnc = F01_FJRNC;
    }
 
    /** 
     * 设置收件人昵称
     *
     * @param F02_SJRNC 收件人昵称
     */
    public void setF02_sjrnc(String F02_SJRNC) {
        this.f02_sjrnc = F02_SJRNC;
    }
 
    /** 
     * 设置标题
     *
     * @param F03_BT 标题
     */
    public void setF03_bt(String F03_BT) {
        this.f03_bt = F03_BT;
    }
 
    /** 
     * 设置紧急度
     *
     * @param F04_JJD 紧急度
     */
    public void setF04_jjd(String F04_JJD) {
        this.f04_jjd = F04_JJD;
    }
 
    /** 
     * 设置重要度
     *
     * @param F05_ZYD 重要度
     */
    public void setF05_zyd(String F05_ZYD) {
        this.f05_zyd = F05_ZYD;
    }
 
}
