package org.jfpc.beans.common.MS3H1;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 用户留言表*/
public class MS3H1DBO extends MyDataBaseObjectSupport
{
    /** 
     * 用户ID
     */
    private String k01_yhid = "";
 
    /** 
     * 获取用户ID
     *
     * @return K01_YHID 用户ID
     */
    public String getK01_yhid() {
        return this.k01_yhid;
    }
 
    /** 
     * 设置用户ID
     *
     * @param K01_YHID 用户ID
     */
    public void setK01_yhid(String K01_YHID) {
        this.k01_yhid = K01_YHID;
    }
 
}
