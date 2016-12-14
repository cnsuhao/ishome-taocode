package org.jfpc.beans.platform.MS0M2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 帮助信息表*/
public class MS0M2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 标题
     */
    private String f01_bt = "";
 
    /** 
     * 排列顺序
     */
    private String f02_plsx = "";
 
    /** 
     * 分类
     */
    private String f03_fl = "";
 
    /** 
     * 获取标题
     *
     * @return F01_BT 标题
     */
    public String getF01_bt() {
        return this.f01_bt;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F02_PLSX 排列顺序
     */
    public String getF02_plsx() {
        return this.f02_plsx;
    }
 
    /** 
     * 获取分类
     *
     * @return F03_FL 分类
     */
    public String getF03_fl() {
        return this.f03_fl;
    }
 
    /** 
     * 设置标题
     *
     * @param F01_BT 标题
     */
    public void setF01_bt(String F01_BT) {
        this.f01_bt = F01_BT;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F02_PLSX 排列顺序
     */
    public void setF02_plsx(String F02_PLSX) {
        this.f02_plsx = F02_PLSX;
    }
 
    /** 
     * 设置分类
     *
     * @param F03_FL 分类
     */
    public void setF03_fl(String F03_FL) {
        this.f03_fl = F03_FL;
    }
 
}
