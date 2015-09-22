package com.aek56.atm.master.MD1_QYJYFW;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业经营范围*/
public class MD1_QYJYFWDBO extends MyDataBaseObjectSupport
{
    /** 
     * 68码标识
     */
    private String k01_dflid = "";
 
    /** 
     * 序列标识
     */
    private String k02_zflid = "";
 
    /** 
     * 类别标识
     */
    private String k03_xflid = "";
 
    /** 
     * 字典名称
     */
    private String f01_zdmc = "";
 
    /** 
     * 排序位置
     */
    private String f02_pxwz = "";
 
    /** 
     * 大分类名称
     */
    private String n01_dflmc = "";
 
    /** 
     * 中分类名称
     */
    private String n02_zflmc = "";
 
    /** 
     * 小分类名称
     */
    private String n03_xflmc = "";
 
    /** 
     * 获取68码标识
     *
     * @return K01_DFLID 68码标识
     */
    public String getK01_dflid() {
        return this.k01_dflid;
    }
 
    /** 
     * 获取序列标识
     *
     * @return K02_ZFLID 序列标识
     */
    public String getK02_zflid() {
        return this.k02_zflid;
    }
 
    /** 
     * 获取类别标识
     *
     * @return K03_XFLID 类别标识
     */
    public String getK03_xflid() {
        return this.k03_xflid;
    }
 
    /** 
     * 获取字典名称
     *
     * @return F01_ZDMC 字典名称
     */
    public String getF01_zdmc() {
        return this.f01_zdmc;
    }
 
    /** 
     * 获取排序位置
     *
     * @return F02_PXWZ 排序位置
     */
    public String getF02_pxwz() {
        return this.f02_pxwz;
    }
 
    /** 
     * 获取大分类名称
     *
     * @return N01_DFLMC 大分类名称
     */
    public String getN01_dflmc() {
        return this.n01_dflmc;
    }
 
    /** 
     * 获取中分类名称
     *
     * @return N02_ZFLMC 中分类名称
     */
    public String getN02_zflmc() {
        return this.n02_zflmc;
    }
 
    /** 
     * 获取小分类名称
     *
     * @return N03_XFLMC 小分类名称
     */
    public String getN03_xflmc() {
        return this.n03_xflmc;
    }
 
    /** 
     * 设置68码标识
     *
     * @param K01_DFLID 68码标识
     */
    public void setK01_dflid(String K01_DFLID) {
        this.k01_dflid = K01_DFLID;
    }
 
    /** 
     * 设置序列标识
     *
     * @param K02_ZFLID 序列标识
     */
    public void setK02_zflid(String K02_ZFLID) {
        this.k02_zflid = K02_ZFLID;
    }
 
    /** 
     * 设置类别标识
     *
     * @param K03_XFLID 类别标识
     */
    public void setK03_xflid(String K03_XFLID) {
        this.k03_xflid = K03_XFLID;
    }
 
    /** 
     * 设置字典名称
     *
     * @param F01_ZDMC 字典名称
     */
    public void setF01_zdmc(String F01_ZDMC) {
        this.f01_zdmc = F01_ZDMC;
    }
 
    /** 
     * 设置排序位置
     *
     * @param F02_PXWZ 排序位置
     */
    public void setF02_pxwz(String F02_PXWZ) {
        this.f02_pxwz = F02_PXWZ;
    }
 
    /** 
     * 设置大分类名称
     *
     * @param N01_DFLMC 大分类名称
     */
    public void setN01_dflmc(String N01_DFLMC) {
        this.n01_dflmc = N01_DFLMC;
    }
 
    /** 
     * 设置中分类名称
     *
     * @param N02_ZFLMC 中分类名称
     */
    public void setN02_zflmc(String N02_ZFLMC) {
        this.n02_zflmc = N02_ZFLMC;
    }
 
    /** 
     * 设置小分类名称
     *
     * @param N03_XFLMC 小分类名称
     */
    public void setN03_xflmc(String N03_XFLMC) {
        this.n03_xflmc = N03_XFLMC;
    }
 
}
