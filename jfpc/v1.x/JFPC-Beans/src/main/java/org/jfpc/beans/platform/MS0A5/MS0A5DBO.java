package org.jfpc.beans.platform.MS0A5;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 页面功能按钮定义*/
public class MS0A5DBO extends MyDataBaseObjectSupport
{
    /** 
     * 产品ID
     */
    private String k01_cpid = "";
 
    /** 
     * 页面ID
     */
    private String k02_ymid = "";
 
    /** 
     * 按钮编号
     */
    private String f01_anbh = "";
 
    /** 
     * 按钮显示文字
     */
    private String f02_anxswz = "";
 
    /** 
     * 按钮动作属性
     */
    private String f03_andzsx = "";
 
    /** 
     * 实现功能编号
     */
    private String f04_sxgnbh = "";
 
    /** 
     * 实现功能名称
     */
    private String f05_sxgnmc = "";
 
    /** 
     * 获取产品ID
     *
     * @return K01_CPID 产品ID
     */
    public String getK01_cpid() {
        return this.k01_cpid;
    }
 
    /** 
     * 获取页面ID
     *
     * @return K02_YMID 页面ID
     */
    public String getK02_ymid() {
        return this.k02_ymid;
    }
 
    /** 
     * 获取按钮编号
     *
     * @return F01_ANBH 按钮编号
     */
    public String getF01_anbh() {
        return this.f01_anbh;
    }
 
    /** 
     * 获取按钮显示文字
     *
     * @return F02_ANXSWZ 按钮显示文字
     */
    public String getF02_anxswz() {
        return this.f02_anxswz;
    }
 
    /** 
     * 获取按钮动作属性
     *
     * @return F03_ANDZSX 按钮动作属性
     */
    public String getF03_andzsx() {
        return this.f03_andzsx;
    }
 
    /** 
     * 获取实现功能编号
     *
     * @return F04_SXGNBH 实现功能编号
     */
    public String getF04_sxgnbh() {
        return this.f04_sxgnbh;
    }
 
    /** 
     * 获取实现功能名称
     *
     * @return F05_SXGNMC 实现功能名称
     */
    public String getF05_sxgnmc() {
        return this.f05_sxgnmc;
    }
 
    /** 
     * 设置产品ID
     *
     * @param K01_CPID 产品ID
     */
    public void setK01_cpid(String K01_CPID) {
        this.k01_cpid = K01_CPID;
    }
 
    /** 
     * 设置页面ID
     *
     * @param K02_YMID 页面ID
     */
    public void setK02_ymid(String K02_YMID) {
        this.k02_ymid = K02_YMID;
    }
 
    /** 
     * 设置按钮编号
     *
     * @param F01_ANBH 按钮编号
     */
    public void setF01_anbh(String F01_ANBH) {
        this.f01_anbh = F01_ANBH;
    }
 
    /** 
     * 设置按钮显示文字
     *
     * @param F02_ANXSWZ 按钮显示文字
     */
    public void setF02_anxswz(String F02_ANXSWZ) {
        this.f02_anxswz = F02_ANXSWZ;
    }
 
    /** 
     * 设置按钮动作属性
     *
     * @param F03_ANDZSX 按钮动作属性
     */
    public void setF03_andzsx(String F03_ANDZSX) {
        this.f03_andzsx = F03_ANDZSX;
    }
 
    /** 
     * 设置实现功能编号
     *
     * @param F04_SXGNBH 实现功能编号
     */
    public void setF04_sxgnbh(String F04_SXGNBH) {
        this.f04_sxgnbh = F04_SXGNBH;
    }
 
    /** 
     * 设置实现功能名称
     *
     * @param F05_SXGNMC 实现功能名称
     */
    public void setF05_sxgnmc(String F05_SXGNMC) {
        this.f05_sxgnmc = F05_SXGNMC;
    }
 
}
