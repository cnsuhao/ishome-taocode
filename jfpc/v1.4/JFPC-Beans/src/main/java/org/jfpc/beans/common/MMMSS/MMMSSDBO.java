package org.jfpc.beans.common.MMMSS;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 短信与邮件模版*/
public class MMMSSDBO extends MyDataBaseObjectSupport
{
    /** 
     * 产品ID
     */
    private String k01_cpid = "";
 
    /** 
     * 业务识别标识
     */
    private String k02_ywsbbs = "";
 
    /** 
     * 模版编号
     */
    private String k03_mbbh = "";
 
    /** 
     * 获取产品ID
     *
     * @return K01_CPID 产品ID
     */
    public String getK01_cpid() {
        return this.k01_cpid;
    }
 
    /** 
     * 获取业务识别标识
     *
     * @return K02_YWSBBS 业务识别标识
     */
    public String getK02_ywsbbs() {
        return this.k02_ywsbbs;
    }
 
    /** 
     * 获取模版编号
     *
     * @return K03_MBBH 模版编号
     */
    public String getK03_mbbh() {
        return this.k03_mbbh;
    }
 
    /** 
     * 设置产品ID
     *
     * @param K01_CPID 产品ID
     */
    public void setK01_cpid(String K01_CPID) {
        this.k01_cpid = K01_CPID;
    }
 
    /** 
     * 设置业务识别标识
     *
     * @param K02_YWSBBS 业务识别标识
     */
    public void setK02_ywsbbs(String K02_YWSBBS) {
        this.k02_ywsbbs = K02_YWSBBS;
    }
 
    /** 
     * 设置模版编号
     *
     * @param K03_MBBH 模版编号
     */
    public void setK03_mbbh(String K03_MBBH) {
        this.k03_mbbh = K03_MBBH;
    }
 
}
