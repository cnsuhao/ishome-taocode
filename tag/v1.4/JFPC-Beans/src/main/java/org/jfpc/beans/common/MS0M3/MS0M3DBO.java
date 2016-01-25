package org.jfpc.beans.common.MS0M3;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 帮助信息意见评价*/
public class MS0M3DBO extends MyDataBaseObjectSupport
{
    /** 
     * 帮助信息ID
     */
    private String k01_bzxxid = "";
 
    /** 
     * 评价分数
     */
    private String f01_pjfs = "";
 
    /** 
     * 获取帮助信息ID
     *
     * @return K01_BZXXID 帮助信息ID
     */
    public String getK01_bzxxid() {
        return this.k01_bzxxid;
    }
 
    /** 
     * 获取评价分数
     *
     * @return F01_PJFS 评价分数
     */
    public String getF01_pjfs() {
        return this.f01_pjfs;
    }
 
    /** 
     * 设置帮助信息ID
     *
     * @param K01_BZXXID 帮助信息ID
     */
    public void setK01_bzxxid(String K01_BZXXID) {
        this.k01_bzxxid = K01_BZXXID;
    }
 
    /** 
     * 设置评价分数
     *
     * @param F01_PJFS 评价分数
     */
    public void setF01_pjfs(String F01_PJFS) {
        this.f01_pjfs = F01_PJFS;
    }
 
}
