package org.jfpc.beans.platform.CS0M5;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 文章评价*/
public class CS0M5DBO extends MyDataBaseObjectSupport
{
    /** 
     * 文章信息ID
     */
    private String k01_wzid = "";
 
    /** 
     * 点赞人数
     */
    private String f01_dzrs = "";
 
    /** 
     * 获取文章信息ID
     *
     * @return K01_WZID 文章信息ID
     */
    public String getK01_wzid() {
        return this.k01_wzid;
    }
 
    /** 
     * 获取点赞人数
     *
     * @return F01_DZRS 点赞人数
     */
    public String getF01_dzrs() {
        return this.f01_dzrs;
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
     * 设置点赞人数
     *
     * @param F01_DZRS 点赞人数
     */
    public void setF01_dzrs(String F01_DZRS) {
        this.f01_dzrs = F01_DZRS;
    }
 
}
