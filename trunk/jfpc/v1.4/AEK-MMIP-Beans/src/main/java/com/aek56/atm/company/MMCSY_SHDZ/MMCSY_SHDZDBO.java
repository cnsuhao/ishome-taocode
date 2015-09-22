package com.aek56.atm.company.MMCSY_SHDZ;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 厂商收货地址*/
public class MMCSY_SHDZDBO extends MyDataBaseObjectSupport
{
    /** 
     * 厂商ID
     */
    private String k01_csid = "";
 
    /** 
     * 默认收货地址
     */
    private String k02_mrshdz = "";
 
    /** 
     * 收货人姓名
     */
    private String f01_shrxm = "";
 
    /** 
     * 固定电话
     */
    private String f02_gddh = "";
 
    /** 
     * 移动电话
     */
    private String f03_yddh = "";
 
    /** 
     * 所在省
     */
    private String f04_szs = "";
 
    /** 
     * 所在地/市
     */
    private String f05_szs = "";
 
    /** 
     * 所在区/县
     */
    private String f06_szq = "";
 
    /** 
     * 所在详细地址
     */
    private String f07_szxxdz = "";
 
    /** 
     * 获取厂商ID
     *
     * @return K01_CSID 厂商ID
     */
    public String getK01_csid() {
        return this.k01_csid;
    }
 
    /** 
     * 获取默认收货地址
     *
     * @return K02_MRSHDZ 默认收货地址
     */
    public String getK02_mrshdz() {
        return this.k02_mrshdz;
    }
 
    /** 
     * 获取收货人姓名
     *
     * @return F01_SHRXM 收货人姓名
     */
    public String getF01_shrxm() {
        return this.f01_shrxm;
    }
 
    /** 
     * 获取固定电话
     *
     * @return F02_GDDH 固定电话
     */
    public String getF02_gddh() {
        return this.f02_gddh;
    }
 
    /** 
     * 获取移动电话
     *
     * @return F03_YDDH 移动电话
     */
    public String getF03_yddh() {
        return this.f03_yddh;
    }
 
    /** 
     * 获取所在省
     *
     * @return F04_SZS 所在省
     */
    public String getF04_szs() {
        return this.f04_szs;
    }
 
    /** 
     * 获取所在地/市
     *
     * @return F05_SZS 所在地/市
     */
    public String getF05_szs() {
        return this.f05_szs;
    }
 
    /** 
     * 获取所在区/县
     *
     * @return F06_SZQ 所在区/县
     */
    public String getF06_szq() {
        return this.f06_szq;
    }
 
    /** 
     * 获取所在详细地址
     *
     * @return F07_SZXXDZ 所在详细地址
     */
    public String getF07_szxxdz() {
        return this.f07_szxxdz;
    }
 
    /** 
     * 设置厂商ID
     *
     * @param K01_CSID 厂商ID
     */
    public void setK01_csid(String K01_CSID) {
        this.k01_csid = K01_CSID;
    }
 
    /** 
     * 设置默认收货地址
     *
     * @param K02_MRSHDZ 默认收货地址
     */
    public void setK02_mrshdz(String K02_MRSHDZ) {
        this.k02_mrshdz = K02_MRSHDZ;
    }
 
    /** 
     * 设置收货人姓名
     *
     * @param F01_SHRXM 收货人姓名
     */
    public void setF01_shrxm(String F01_SHRXM) {
        this.f01_shrxm = F01_SHRXM;
    }
 
    /** 
     * 设置固定电话
     *
     * @param F02_GDDH 固定电话
     */
    public void setF02_gddh(String F02_GDDH) {
        this.f02_gddh = F02_GDDH;
    }
 
    /** 
     * 设置移动电话
     *
     * @param F03_YDDH 移动电话
     */
    public void setF03_yddh(String F03_YDDH) {
        this.f03_yddh = F03_YDDH;
    }
 
    /** 
     * 设置所在省
     *
     * @param F04_SZS 所在省
     */
    public void setF04_szs(String F04_SZS) {
        this.f04_szs = F04_SZS;
    }
 
    /** 
     * 设置所在地/市
     *
     * @param F05_SZS 所在地/市
     */
    public void setF05_szs(String F05_SZS) {
        this.f05_szs = F05_SZS;
    }
 
    /** 
     * 设置所在区/县
     *
     * @param F06_SZQ 所在区/县
     */
    public void setF06_szq(String F06_SZQ) {
        this.f06_szq = F06_SZQ;
    }
 
    /** 
     * 设置所在详细地址
     *
     * @param F07_SZXXDZ 所在详细地址
     */
    public void setF07_szxxdz(String F07_SZXXDZ) {
        this.f07_szxxdz = F07_SZXXDZ;
    }
 
}
