package org.jfpc.beans.platform.MS3H2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 留言回复表*/
public class MS3H2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 回复用户ID
     */
    private String k01_yhid = "";
 
    /** 
     * 获取回复用户ID
     *
     * @return K01_YHID 回复用户ID
     */
    public String getK01_yhid() {
        return this.k01_yhid;
    }
 
    /** 
     * 设置回复用户ID
     *
     * @param K01_YHID 回复用户ID
     */
    public void setK01_yhid(String K01_YHID) {
        this.k01_yhid = K01_YHID;
    }
 
}
