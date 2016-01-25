package org.jfpc.beans.platform.MS1C5;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 企业组织机构角色定义*/
public class MS1C5DBO extends MyDataBaseObjectSupport
{
    /** 
     * 角色编号
     */
    private String k01_jsbh = "";
 
    /** 
     * 所属组织机构ID
     */
    private String k02_zzjgid = "";
 
    /** 
     * 角色名称
     */
    private String f01_jsmc = "";
 
    /** 
     * 排列顺序
     */
    private String f02_plsx = "";
 
    /** 
     * 角色类型
     */
    private String f03_jslx = "";
 
    /** 
     * 获取角色编号
     *
     * @return K01_JSBH 角色编号
     */
    public String getK01_jsbh() {
        return this.k01_jsbh;
    }
 
    /** 
     * 获取所属组织机构ID
     *
     * @return K02_ZZJGID 所属组织机构ID
     */
    public String getK02_zzjgid() {
        return this.k02_zzjgid;
    }
 
    /** 
     * 获取角色名称
     *
     * @return F01_JSMC 角色名称
     */
    public String getF01_jsmc() {
        return this.f01_jsmc;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return F02_PLSX 排列顺序
     */
    public String getF02_plsx() {
        return this.f02_plsx;
    }
 
    /** 
     * 获取角色类型
     *
     * @return F03_JSLX 角色类型
     */
    public String getF03_jslx() {
        return this.f03_jslx;
    }
 
    /** 
     * 设置角色编号
     *
     * @param K01_JSBH 角色编号
     */
    public void setK01_jsbh(String K01_JSBH) {
        this.k01_jsbh = K01_JSBH;
    }
 
    /** 
     * 设置所属组织机构ID
     *
     * @param K02_ZZJGID 所属组织机构ID
     */
    public void setK02_zzjgid(String K02_ZZJGID) {
        this.k02_zzjgid = K02_ZZJGID;
    }
 
    /** 
     * 设置角色名称
     *
     * @param F01_JSMC 角色名称
     */
    public void setF01_jsmc(String F01_JSMC) {
        this.f01_jsmc = F01_JSMC;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param F02_PLSX 排列顺序
     */
    public void setF02_plsx(String F02_PLSX) {
        this.f02_plsx = F02_PLSX;
    }
 
    /** 
     * 设置角色类型
     *
     * @param F03_JSLX 角色类型
     */
    public void setF03_jslx(String F03_JSLX) {
        this.f03_jslx = F03_JSLX;
    }
 
}
