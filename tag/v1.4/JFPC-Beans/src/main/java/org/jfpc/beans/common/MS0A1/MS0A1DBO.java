package org.jfpc.beans.common.MS0A1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 系统菜单定义*/
public class MS0A1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 显示父菜单ID
     */
    private String k01_xsfcdid = "";
 
    /** 
     * 产品ID
     */
    private String k02_cpid = "";
 
    /** 
     * 机能模块ID
     */
    private String k03_jnmkid = "";
 
    /** 
     * 页面ID
     */
    private String k04_ymid = "";
 
    /** 
     * 菜单编号
     */
    private String f01_cdbh = "";
 
    /** 
     * 菜单名称
     */
    private String f02_cdmc = "";
 
    /** 
     * 菜单版本号
     */
    private String f03_cdbbh = "";
 
    /** 
     * 菜单URL
     */
    private String f04_cdurl = "";
 
    /** 
     * 排列顺序
     */
    private String f05_plsx = "";
 
    /** 
     * 菜单等级
     */
    private String f06_cddj = "";
 
    /** 
     * 小图标URL
     */
    private String f07_xtburl = "";
 
    /** 
     * 获取显示父菜单ID
     *
     * @return K01_XSFCDID 显示父菜单ID
     */
    public String getK01_xsfcdid() {
        return this.k01_xsfcdid;
    }
 
    /** 
     * 获取产品ID
     *
     * @return K02_CPID 产品ID
     */
    public String getK02_cpid() {
        return this.k02_cpid;
    }
 
    /** 
     * 获取机能模块ID
     *
     * @return K03_JNMKID 机能模块ID
     */
    public String getK03_jnmkid() {
        return this.k03_jnmkid;
    }
 
    /** 
     * 获取页面ID
     *
     * @return K04_YMID 页面ID
     */
    public String getK04_ymid() {
        return this.k04_ymid;
    }
 
    /** 
     * 获取菜单编号
     *
     * @return F01_CDBH 菜单编号
     */
    public String getF01_cdbh() {
        return this.f01_cdbh;
    }
 
    /** 
     * 获取菜单名称
     *
     * @return F02_CDMC 菜单名称
     */
    public String getF02_cdmc() {
        return this.f02_cdmc;
    }
 
    /** 
     * 获取菜单版本号
     *
     * @return F03_CDBBH 菜单版本号
     */
    public String getF03_cdbbh() {
        return this.f03_cdbbh;
    }
 
    /** 
     * 获取菜单URL
     *
     * @return F04_CDURL 菜单URL
     */
    public String getF04_cdurl() {
        return this.f04_cdurl;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F05_PLSX 排列顺序
     */
    public String getF05_plsx() {
        return this.f05_plsx;
    }
 
    /** 
     * 获取菜单等级
     *
     * @return F06_CDDJ 菜单等级
     */
    public String getF06_cddj() {
        return this.f06_cddj;
    }
 
    /** 
     * 获取小图标URL
     *
     * @return F07_XTBURL 小图标URL
     */
    public String getF07_xtburl() {
        return this.f07_xtburl;
    }
 
    /** 
     * 设置显示父菜单ID
     *
     * @param K01_XSFCDID 显示父菜单ID
     */
    public void setK01_xsfcdid(String K01_XSFCDID) {
        this.k01_xsfcdid = K01_XSFCDID;
    }
 
    /** 
     * 设置产品ID
     *
     * @param K02_CPID 产品ID
     */
    public void setK02_cpid(String K02_CPID) {
        this.k02_cpid = K02_CPID;
    }
 
    /** 
     * 设置机能模块ID
     *
     * @param K03_JNMKID 机能模块ID
     */
    public void setK03_jnmkid(String K03_JNMKID) {
        this.k03_jnmkid = K03_JNMKID;
    }
 
    /** 
     * 设置页面ID
     *
     * @param K04_YMID 页面ID
     */
    public void setK04_ymid(String K04_YMID) {
        this.k04_ymid = K04_YMID;
    }
 
    /** 
     * 设置菜单编号
     *
     * @param F01_CDBH 菜单编号
     */
    public void setF01_cdbh(String F01_CDBH) {
        this.f01_cdbh = F01_CDBH;
    }
 
    /** 
     * 设置菜单名称
     *
     * @param F02_CDMC 菜单名称
     */
    public void setF02_cdmc(String F02_CDMC) {
        this.f02_cdmc = F02_CDMC;
    }
 
    /** 
     * 设置菜单版本号
     *
     * @param F03_CDBBH 菜单版本号
     */
    public void setF03_cdbbh(String F03_CDBBH) {
        this.f03_cdbbh = F03_CDBBH;
    }
 
    /** 
     * 设置菜单URL
     *
     * @param F04_CDURL 菜单URL
     */
    public void setF04_cdurl(String F04_CDURL) {
        this.f04_cdurl = F04_CDURL;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F05_PLSX 排列顺序
     */
    public void setF05_plsx(String F05_PLSX) {
        this.f05_plsx = F05_PLSX;
    }
 
    /** 
     * 设置菜单等级
     *
     * @param F06_CDDJ 菜单等级
     */
    public void setF06_cddj(String F06_CDDJ) {
        this.f06_cddj = F06_CDDJ;
    }
 
    /** 
     * 设置小图标URL
     *
     * @param F07_XTBURL 小图标URL
     */
    public void setF07_xtburl(String F07_XTBURL) {
        this.f07_xtburl = F07_XTBURL;
    }
 
}
