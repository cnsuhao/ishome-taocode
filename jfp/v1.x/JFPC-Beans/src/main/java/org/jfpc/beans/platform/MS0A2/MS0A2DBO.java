package org.jfpc.beans.platform.MS0A2;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 机能模块定义*/
public class MS0A2DBO extends MyDataBaseObjectSupport
{
    /** 
     * 所属产品ID
     */
    private String k01_sscpid = "";
 
    /** 
     * 机能模块编号
     */
    private String f01_jnmkbh = "";
 
    /** 
     * 机能模块名称
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
     * 获取机能模块编号
     *
     * @return F01_JNMKBH 机能模块编号
     */
    public String getF01_jnmkbh() {
        return this.f01_jnmkbh;
    }
 
    /** 
     * 获取机能模块名称
     *
     * @return F02_JNMKMC 机能模块名称
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
     * 设置机能模块编号
     *
     * @param F01_JNMKBH 机能模块编号
     */
    public void setF01_jnmkbh(String F01_JNMKBH) {
        this.f01_jnmkbh = F01_JNMKBH;
    }
 
    /** 
     * 设置机能模块名称
     *
     * @param F02_JNMKMC 机能模块名称
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
