package org.jfpc.beans.common.MS0M1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 数据字典管理*/
public class MS0M1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 大分类ID
     */
    private String k01_dflid = "";
 
    /** 
     * 中分类ID
     */
    private String k02_zflid = "";
 
    /** 
     * 小分类ID
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
     * 获取大分类ID
     *
     * @return K01_DFLID 大分类ID
     */
    public String getK01_dflid() {
        return this.k01_dflid;
    }
 
    /** 
     * 获取中分类ID
     *
     * @return K02_ZFLID 中分类ID
     */
    public String getK02_zflid() {
        return this.k02_zflid;
    }
 
    /** 
     * 获取小分类ID
     *
     * @return K03_XFLID 小分类ID
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
     * 设置大分类ID
     *
     * @param K01_DFLID 大分类ID
     */
    public void setK01_dflid(String K01_DFLID) {
        this.k01_dflid = K01_DFLID;
    }
 
    /** 
     * 设置中分类ID
     *
     * @param K02_ZFLID 中分类ID
     */
    public void setK02_zflid(String K02_ZFLID) {
        this.k02_zflid = K02_ZFLID;
    }
 
    /** 
     * 设置小分类ID
     *
     * @param K03_XFLID 小分类ID
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
