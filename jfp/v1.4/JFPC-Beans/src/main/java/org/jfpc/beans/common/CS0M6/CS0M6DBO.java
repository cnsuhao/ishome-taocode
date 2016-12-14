package org.jfpc.beans.common.CS0M6;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 文章评价人*/
public class CS0M6DBO extends MyDataBaseObjectSupport
{
    /** 
     * 文章信息ID
     */
    private String k01_wzid = "";
 
    /** 
     * 评价人ID
     */
    private String k02_pjrid = "";
 
    /** 
     * 评价分数
     */
    private String pjfs = "";
 
    /** 
     * 获取文章信息ID
     *
     * @return K01_WZID 文章信息ID
     */
    public String getK01_wzid() {
        return this.k01_wzid;
    }
 
    /** 
     * 获取评价人ID
     *
     * @return K02_PJRID 评价人ID
     */
    public String getK02_pjrid() {
        return this.k02_pjrid;
    }
 
    /** 
     * 获取评价分数
     *
     * @return PJFS 评价分数
     */
    public String getPjfs() {
        return this.pjfs;
    }
 
    /** 
     * 设置文章信息ID
     *
     * @param K01_WZID 文章信息ID
     */
    public void setK01_wzid(String K01_WZID) {
        this.k01_wzid = K01_WZID;
    }
 
    /** 
     * 设置评价人ID
     *
     * @param K02_PJRID 评价人ID
     */
    public void setK02_pjrid(String K02_PJRID) {
        this.k02_pjrid = K02_PJRID;
    }
 
    /** 
     * 设置评价分数
     *
     * @param PJFS 评价分数
     */
    public void setPjfs(String PJFS) {
        this.pjfs = PJFS;
    }
 
}
