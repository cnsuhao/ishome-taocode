package org.jfpc.platform.CSSGG.dbo;
import javax.inject.Named;

import org.jfpc.base.support.MyDataBaseObjectSupport;
 
@Named
/** 系统动态*/
public class CSSGGDBO extends MyDataBaseObjectSupport
{
    /** 
     * 企业ID
     */
    private String k01_qyid = "";
 
    /** 
     * 获取企业ID
     *
     * @return K01_QYID 企业ID
     */
    public String getK01_qyid() {
        return this.k01_qyid;
    }
 
    /** 
     * 设置企业ID
     *
     * @param K01_QYID 企业ID
     */
    public void setK01_qyid(String K01_QYID) {
        this.k01_qyid = K01_QYID;
    }
 
}
