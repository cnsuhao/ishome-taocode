package org.jfpc.beans.platform.MSSKK;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 主键关联表*/
public class MSSKKDBO extends MyDataBaseObjectSupport
{
    /** 
     * 关联数据ID
     */
    private String k01_glsjid = "";
 
    /** 
     * 业务区分关键字
     */
    private String k02_ywqfgjz = "";
 
    /** 
     * 获取关联数据ID
     *
     * @return K01_GLSJID 关联数据ID
     */
    public String getK01_glsjid() {
        return this.k01_glsjid;
    }
 
    /** 
     * 获取业务区分关键字
     *
     * @return K02_YWQFGJZ 业务区分关键字
     */
    public String getK02_ywqfgjz() {
        return this.k02_ywqfgjz;
    }
 
    /** 
     * 设置关联数据ID
     *
     * @param K01_GLSJID 关联数据ID
     */
    public void setK01_glsjid(String K01_GLSJID) {
        this.k01_glsjid = K01_GLSJID;
    }
 
    /** 
     * 设置业务区分关键字
     *
     * @param K02_YWQFGJZ 业务区分关键字
     */
    public void setK02_ywqfgjz(String K02_YWQFGJZ) {
        this.k02_ywqfgjz = K02_YWQFGJZ;
    }
 
}
