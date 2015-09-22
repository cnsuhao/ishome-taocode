package org.jfpc.beans.common.CS0M8;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 用户问卷答案*/
public class CS0M8DBO extends MyDataBaseObjectSupport
{
    /** 
     * 问卷ID
     */
    private String k01_wjid = "";
 
    /** 
     * 用户ID
     */
    private String k02_yhid = "";
 
    /** 
     * 获取问卷ID
     *
     * @return K01_WJID 问卷ID
     */
    public String getK01_wjid() {
        return this.k01_wjid;
    }
 
    /** 
     * 获取用户ID
     *
     * @return K02_YHID 用户ID
     */
    public String getK02_yhid() {
        return this.k02_yhid;
    }
 
    /** 
     * 设置问卷ID
     *
     * @param K01_WJID 问卷ID
     */
    public void setK01_wjid(String K01_WJID) {
        this.k01_wjid = K01_WJID;
    }
 
    /** 
     * 设置用户ID
     *
     * @param K02_YHID 用户ID
     */
    public void setK02_yhid(String K02_YHID) {
        this.k02_yhid = K02_YHID;
    }
 
}
