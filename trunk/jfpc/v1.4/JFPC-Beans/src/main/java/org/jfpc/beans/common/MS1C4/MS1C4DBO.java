package org.jfpc.beans.common.MS1C4;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业用户所属角色*/
public class MS1C4DBO extends MyDataBaseObjectSupport
{
    /** 
     * 企业ID
     */
    private String k01_qyid = "";
 
    /** 
     * 角色ID
     */
    private String k02_jsid = "";
 
    /** 
     * 获取企业ID
     *
     * @return K01_QYID 企业ID
     */
    public String getK01_qyid() {
        return this.k01_qyid;
    }
 
    /** 
     * 获取角色ID
     *
     * @return K02_JSID 角色ID
     */
    public String getK02_jsid() {
        return this.k02_jsid;
    }
 
    /** 
     * 设置企业ID
     *
     * @param K01_QYID 企业ID
     */
    public void setK01_qyid(String K01_QYID) {
        this.k01_qyid = K01_QYID;
    }
 
    /** 
     * 设置角色ID
     *
     * @param K02_JSID 角色ID
     */
    public void setK02_jsid(String K02_JSID) {
        this.k02_jsid = K02_JSID;
    }
 
}
