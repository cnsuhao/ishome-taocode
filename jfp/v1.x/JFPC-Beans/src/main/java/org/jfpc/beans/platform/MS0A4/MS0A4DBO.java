package org.jfpc.beans.platform.MS0A4;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 页面定义*/
public class MS0A4DBO extends MyDataBaseObjectSupport
{
    /** 
     * 产品ID
     */
    private String k01_cpid = "";
 
    /** 
     * 所属机能模块ID
     */
    private String k02_ssjnmkid = "";
 
    /** 
     * 页面编号
     */
    private String f01_ymbh = "";
 
    /** 
     * 页面标题
     */
    private String f02_ymbt = "";
 
    /** 
     * 页面布局属性
     */
    private String f03_ymbj = "";
 
    /** 
     * 页面版本
     */
    private String f04_ymbb = "";
 
    /** 
     * 实现功能编号
     */
    private String f05_sxgnbh = "";
 
    /** 
     * 实现功能名称
     */
    private String f06_sxgnmc = "";
 
    /** 
     * 实现机能模块编号
     */
    private String f07_sxjnmkbh = "";
 
    /** 
     * 实现机能模块名称
     */
    private String f08_sxjnmkmc = "";
 
    /** 
     * 获取产品ID
     *
     * @return K01_CPID 产品ID
     */
    public String getK01_cpid() {
        return this.k01_cpid;
    }
 
    /** 
     * 获取所属机能模块ID
     *
     * @return K02_SSJNMKID 所属机能模块ID
     */
    public String getK02_ssjnmkid() {
        return this.k02_ssjnmkid;
    }
 
    /** 
     * 获取页面编号
     *
     * @return F01_YMBH 页面编号
     */
    public String getF01_ymbh() {
        return this.f01_ymbh;
    }
 
    /** 
     * 获取页面标题
     *
     * @return F02_YMBT 页面标题
     */
    public String getF02_ymbt() {
        return this.f02_ymbt;
    }
 
    /** 
     * 获取页面布局属性
     *
     * @return F03_YMBJ 页面布局属性
     */
    public String getF03_ymbj() {
        return this.f03_ymbj;
    }
 
    /** 
     * 获取页面版本
     *
     * @return F04_YMBB 页面版本
     */
    public String getF04_ymbb() {
        return this.f04_ymbb;
    }
 
    /** 
     * 获取实现功能编号
     *
     * @return F05_SXGNBH 实现功能编号
     */
    public String getF05_sxgnbh() {
        return this.f05_sxgnbh;
    }
 
    /** 
     * 获取实现功能名称
     *
     * @return F06_SXGNMC 实现功能名称
     */
    public String getF06_sxgnmc() {
        return this.f06_sxgnmc;
    }
 
    /** 
     * 获取实现机能模块编号
     *
     * @return F07_SXJNMKBH 实现机能模块编号
     */
    public String getF07_sxjnmkbh() {
        return this.f07_sxjnmkbh;
    }
 
    /** 
     * 获取实现机能模块名称
     *
     * @return F08_SXJNMKMC 实现机能模块名称
     */
    public String getF08_sxjnmkmc() {
        return this.f08_sxjnmkmc;
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
     * 设置所属机能模块ID
     *
     * @param K02_SSJNMKID 所属机能模块ID
     */
    public void setK02_ssjnmkid(String K02_SSJNMKID) {
        this.k02_ssjnmkid = K02_SSJNMKID;
    }
 
    /** 
     * 设置页面编号
     *
     * @param F01_YMBH 页面编号
     */
    public void setF01_ymbh(String F01_YMBH) {
        this.f01_ymbh = F01_YMBH;
    }
 
    /** 
     * 设置页面标题
     *
     * @param F02_YMBT 页面标题
     */
    public void setF02_ymbt(String F02_YMBT) {
        this.f02_ymbt = F02_YMBT;
    }
 
    /** 
     * 设置页面布局属性
     *
     * @param F03_YMBJ 页面布局属性
     */
    public void setF03_ymbj(String F03_YMBJ) {
        this.f03_ymbj = F03_YMBJ;
    }
 
    /** 
     * 设置页面版本
     *
     * @param F04_YMBB 页面版本
     */
    public void setF04_ymbb(String F04_YMBB) {
        this.f04_ymbb = F04_YMBB;
    }
 
    /** 
     * 设置实现功能编号
     *
     * @param F05_SXGNBH 实现功能编号
     */
    public void setF05_sxgnbh(String F05_SXGNBH) {
        this.f05_sxgnbh = F05_SXGNBH;
    }
 
    /** 
     * 设置实现功能名称
     *
     * @param F06_SXGNMC 实现功能名称
     */
    public void setF06_sxgnmc(String F06_SXGNMC) {
        this.f06_sxgnmc = F06_SXGNMC;
    }
 
    /** 
     * 设置实现机能模块编号
     *
     * @param F07_SXJNMKBH 实现机能模块编号
     */
    public void setF07_sxjnmkbh(String F07_SXJNMKBH) {
        this.f07_sxjnmkbh = F07_SXJNMKBH;
    }
 
    /** 
     * 设置实现机能模块名称
     *
     * @param F08_SXJNMKMC 实现机能模块名称
     */
    public void setF08_sxjnmkmc(String F08_SXJNMKMC) {
        this.f08_sxjnmkmc = F08_SXJNMKMC;
    }
 
}
