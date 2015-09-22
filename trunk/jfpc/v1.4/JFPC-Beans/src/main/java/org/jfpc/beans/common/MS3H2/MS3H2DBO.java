package org.jfpc.beans.common.MS3H2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 留言回复表*/
public class MS3H2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 主题ID
     */
    private String k01_ztid = "";
 
    /** 
     * 获取主题ID
     *
     * @return K01_ZTID 主题ID
     */
    public String getK01_ztid() {
        return this.k01_ztid;
    }
 
    /** 
     * 设置主题ID
     *
     * @param K01_ZTID 主题ID
     */
    public void setK01_ztid(String K01_ZTID) {
        this.k01_ztid = K01_ZTID;
    }
 
}
