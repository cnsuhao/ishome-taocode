package org.jfpc.beans.platform.MS0AA;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 动态页面定义*/
public class MS0AADBO extends MyDataBaseObjectSupport
{
    /** 
     * 对应数据表名称
     */
    private String k01_dysjbmc = "";
 
    /** 
     * 页面种类
     */
    private String k02_ymzl = "";
 
    /** 
     * 控件英文名称
     */
    private String f01_kjywmc = "";
 
    /** 
     * 控件中文名称
     */
    private String f02_kjzwmc = "";
 
    /** 
     * 控件种类
     */
    private String f03_kjzl = "";
 
    /** 
     * 对应数据存放Model名称(中文解释)
     */
    private String f04_model_mc = "";
 
    /** 
     * 对应数据存放Model属性名称(英文标识)
     */
    private String f05_model_id = "";
 
    /** 
     * 排列顺序
     */
    private String f06_pxsx = "";
 
    /** 
     * 获取对应数据表名称
     *
     * @return K01_DYSJBMC 对应数据表名称
     */
    public String getK01_dysjbmc() {
        return this.k01_dysjbmc;
    }
 
    /** 
     * 获取页面种类
     *
     * @return K02_YMZL 页面种类
     */
    public String getK02_ymzl() {
        return this.k02_ymzl;
    }
 
    /** 
     * 获取控件英文名称
     *
     * @return F01_KJYWMC 控件英文名称
     */
    public String getF01_kjywmc() {
        return this.f01_kjywmc;
    }
 
    /** 
     * 获取控件中文名称
     *
     * @return F02_KJZWMC 控件中文名称
     */
    public String getF02_kjzwmc() {
        return this.f02_kjzwmc;
    }
 
    /** 
     * 获取控件种类
     *
     * @return F03_KJZL 控件种类
     */
    public String getF03_kjzl() {
        return this.f03_kjzl;
    }
 
    /** 
     * 获取对应数据存放Model名称(中文解释)
     *
     * @return F04_MODEL_MC 对应数据存放Model名称(中文解释)
     */
    public String getF04_model_mc() {
        return this.f04_model_mc;
    }
 
    /** 
     * 获取对应数据存放Model属性名称(英文标识)
     *
     * @return F05_MODEL_ID 对应数据存放Model属性名称(英文标识)
     */
    public String getF05_model_id() {
        return this.f05_model_id;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F06_PXSX 排列顺序
     */
    public String getF06_pxsx() {
        return this.f06_pxsx;
    }
 
    /** 
     * 设置对应数据表名称
     *
     * @param K01_DYSJBMC 对应数据表名称
     */
    public void setK01_dysjbmc(String K01_DYSJBMC) {
        this.k01_dysjbmc = K01_DYSJBMC;
    }
 
    /** 
     * 设置页面种类
     *
     * @param K02_YMZL 页面种类
     */
    public void setK02_ymzl(String K02_YMZL) {
        this.k02_ymzl = K02_YMZL;
    }
 
    /** 
     * 设置控件英文名称
     *
     * @param F01_KJYWMC 控件英文名称
     */
    public void setF01_kjywmc(String F01_KJYWMC) {
        this.f01_kjywmc = F01_KJYWMC;
    }
 
    /** 
     * 设置控件中文名称
     *
     * @param F02_KJZWMC 控件中文名称
     */
    public void setF02_kjzwmc(String F02_KJZWMC) {
        this.f02_kjzwmc = F02_KJZWMC;
    }
 
    /** 
     * 设置控件种类
     *
     * @param F03_KJZL 控件种类
     */
    public void setF03_kjzl(String F03_KJZL) {
        this.f03_kjzl = F03_KJZL;
    }
 
    /** 
     * 设置对应数据存放Model名称(中文解释)
     *
     * @param F04_MODEL_MC 对应数据存放Model名称(中文解释)
     */
    public void setF04_model_mc(String F04_MODEL_MC) {
        this.f04_model_mc = F04_MODEL_MC;
    }
 
    /** 
     * 设置对应数据存放Model属性名称(英文标识)
     *
     * @param F05_MODEL_ID 对应数据存放Model属性名称(英文标识)
     */
    public void setF05_model_id(String F05_MODEL_ID) {
        this.f05_model_id = F05_MODEL_ID;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F06_PXSX 排列顺序
     */
    public void setF06_pxsx(String F06_PXSX) {
        this.f06_pxsx = F06_PXSX;
    }
 
}
