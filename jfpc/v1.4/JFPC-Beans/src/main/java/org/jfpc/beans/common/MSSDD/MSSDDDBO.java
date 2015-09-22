package org.jfpc.beans.common.MSSDD;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 资源路径表*/
public class MSSDDDBO extends MyDataBaseObjectSupport
{
    /** 
     * 图片本地保存路径
     */
    private String f01_tpurl = "";
 
    /** 
     * 获取图片本地保存路径
     *
     * @return F01_TPURL 图片本地保存路径
     */
    public String getF01_tpurl() {
        return this.f01_tpurl;
    }
 
    /** 
     * 设置图片本地保存路径
     *
     * @param F01_TPURL 图片本地保存路径
     */
    public void setF01_tpurl(String F01_TPURL) {
        this.f01_tpurl = F01_TPURL;
    }
 
}
