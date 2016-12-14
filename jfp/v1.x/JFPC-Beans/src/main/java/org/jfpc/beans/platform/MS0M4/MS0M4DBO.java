package org.jfpc.beans.platform.MS0M4;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 文章信息*/
public class MS0M4DBO extends MyDataBaseObjectSupport
{
    /** 
     * 标题
     */
    private String f01_bt = "";
 
    /** 
     * 分类
     */
    private String f02_fl = "";
 
    /** 
     * 获取标题
     *
     * @return F01_BT 标题
     */
    public String getF01_bt() {
        return this.f01_bt;
    }
 
    /** 
     * 获取分类
     *
     * @return F02_FL 分类
     */
    public String getF02_fl() {
        return this.f02_fl;
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
     * 设置分类
     *
     * @param F02_FL 分类
     */
    public void setF02_fl(String F02_FL) {
        this.f02_fl = F02_FL;
    }
 
}
