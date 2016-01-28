package com.upg.ceci.beans.CeciRequestStatus;
import java.util.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciRequestStatusDBO extends MyDataBaseObjectSupport
{
    /** 
     * 主键ID
     */
    private String id = null;
 
    /** 
     * 区域码
     */
    private String areaCode = null;
 
    /** 
     * 请求地址状态
     */
    private String connStatus = null;
 
    /** 
     * 最后刷新时间
     */
    private Date lastConnTime = null;
    /** 
     * 最后失败时间
     */
    private Date lastErrorTime = null;
    public Date getLastErrorTime() {
		return lastErrorTime;
	}

	public void setLastErrorTime(Date lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}

	/** 
     * 历史失败次数
     */
    private int hisErrorNum = 0;
 
    /** 
     * 最后运行处理记录数
     */
    private int processNum = 0;
 
    /** 
     * 最后运行处理成功记录数
     */
    private int processNumSuccess = 0;
 
    /** 
     * 获取主键ID
     *
     * @return ID 主键ID
     */
    public String getId() {
        return this.id;
    }
 
    /** 
     * 获取区域码
     *
     * @return Area_code 区域码
     */
    public String getAreaCode() {
        return this.areaCode;
    }
 
    /** 
     * 获取请求地址状态
     *
     * @return Conn_status 请求地址状态
     */
    public String getConnStatus() {
        return this.connStatus;
    }
 
    /** 
     * 获取最后刷新时间
     *
     * @return Last_conn_time 最后刷新时间
     */
    public Date getLastConnTime() {
        return this.lastConnTime;
    }
 
    /** 
     * 获取历史失败次数
     *
     * @return His_error_num 历史失败次数
     */
    public int getHisErrorNum() {
        return this.hisErrorNum;
    }
 
    /** 
     * 获取处理记录数
     *
     * @return Process_num 处理记录数
     */
    public int getProcessNum() {
        return this.processNum;
    }
 
    /** 
     * 获取处理成功记录数
     *
     * @return Process_num_success 处理成功记录数
     */
    public int getProcessNumSuccess() {
        return this.processNumSuccess;
    }
 
    /** 
     * 设置主键ID
     *
     * @param ID 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }
 
    /** 
     * 设置区域码
     *
     * @param Area_code 区域码
     */
    public void setAreaCode(String areacode) {
        this.areaCode = areacode;
    }
 
    /** 
     * 设置请求地址状态
     *
     * @param Conn_status 请求地址状态
     */
    public void setConnStatus(String connstatus) {
        this.connStatus = connstatus;
    }
 
    /** 
     * 设置最后刷新时间
     *
     * @param Last_conn_time 最后刷新时间
     */
    public void setLastConnTime(Date lastconntime) {
        this.lastConnTime = lastconntime;
    }
 
    /** 
     * 设置历史失败次数
     *
     * @param His_error_num 历史失败次数
     */
    public void setHisErrorNum(int hiserrornum) {
        this.hisErrorNum = hiserrornum;
    }
 
    /** 
     * 设置处理记录数
     *
     * @param Process_num 处理记录数
     */
    public void setProcessNum(int processnum) {
        this.processNum = processnum;
    }
 
    /** 
     * 设置处理成功记录数
     *
     * @param Process_num_success 处理成功记录数
     */
    public void setProcessNumSuccess(int processnumsuccess) {
        this.processNumSuccess = processnumsuccess;
    }
 
}
