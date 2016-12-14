package org.jfpc.beans.platform.MS0A3;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 业务功能定义*/
public class MS0A3DBO extends MyDataBaseObjectSupport
{
    /** 
     * 所属产品ID
     */
    private String k01_sscpid = "";
 
    /** 
     * 机能模块ID
     */
    private String k02_jnmkid = "";
 
    /** 
     * 业务功能ID
     */
    private String f01_ywgnid = "";
 
    /** 
     * 业务功能名称
     */
    private String f02_jnmkmc = "";
 
    /** 
     * 版本
     */
    private String f03_bb = "";
 
    /** 
     * 获取所属产品ID
     *
     * @return K01_SSCPID 所属产品ID
     */
    public String getK01_sscpid() {
        return this.k01_sscpid;
    }
 
    /** 
     * 获取机能模块ID
     *
     * @return K02_JNMKID 机能模块ID
     */
    public String getK02_jnmkid() {
        return this.k02_jnmkid;
    }
 
    /** 
     * 获取业务功能ID
     *
     * @return F01_YWGNID 业务功能ID
     */
    public String getF01_ywgnid() {
        return this.f01_ywgnid;
    }
 
    /** 
     * 获取业务功能名称
     *
     * @return F02_JNMKMC 业务功能名称
     */
    public String getF02_jnmkmc() {
        return this.f02_jnmkmc;
    }
 
    /** 
     * 获取版本
     *
     * @return F03_BB 版本
     */
    public String getF03_bb() {
        return this.f03_bb;
    }
 
    /** 
     * 设置所属产品ID
     *
     * @param K01_SSCPID 所属产品ID
     */
    public void setK01_sscpid(String K01_SSCPID) {
        this.k01_sscpid = K01_SSCPID;
    }
 
    /** 
     * 设置机能模块ID
     *
     * @param K02_JNMKID 机能模块ID
     */
    public void setK02_jnmkid(String K02_JNMKID) {
        this.k02_jnmkid = K02_JNMKID;
    }
 
    /** 
     * 设置业务功能ID
     *
     * @param F01_YWGNID 业务功能ID
     */
    public void setF01_ywgnid(String F01_YWGNID) {
        this.f01_ywgnid = F01_YWGNID;
    }
 
    /** 
     * 设置业务功能名称
     *
     * @param F02_JNMKMC 业务功能名称
     */
    public void setF02_jnmkmc(String F02_JNMKMC) {
        this.f02_jnmkmc = F02_JNMKMC;
    }
 
    /** 
     * 设置版本
     *
     * @param F03_BB 版本
     */
    public void setF03_bb(String F03_BB) {
        this.f03_bb = F03_BB;
    }
 
}
