package com.upg.ceci.beans.CeciLog;
import java.util.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciLogDBO extends MyDataBaseObjectSupport
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
     * 处理日期
     */
    private Date logDate = null;
 
    /** 
     * 处理省份名称
     */
    private String areaName = null;
    /** 
     * 处理省份名称
     */
    private int pageNo = 0;

	/** 
     * 处理记录数
     */
    private int processNum = 0;
 
    /** 
     * 处理成功记录数
     */
    private int processNumSuccess = 0;
 
    /** 
     * 信息类型
     */
    private String infoType = null;
 
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
     * 获取处理日期
     *
     * @return Log_date 处理日期
     */
    public Date getLogDate() {
        return this.logDate;
    }
 
    /** 
     * 获取处理省份名称
     *
     * @return Area_name 处理省份名称
     */
    public String getAreaName() {
        return this.areaName;
    }
    
    public int getPageNo() {
		return pageNo;
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
     * 获取信息类型
     *
     * @return Info_type 信息类型
     */
    public String getInfoType() {
        return this.infoType;
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
     * 设置处理日期
     *
     * @param Log_date 处理日期
     */
    public void setLogDate(Date logdate) {
        this.logDate = logdate;
    }
 
    /** 
     * 设置处理省份名称
     *
     * @param Area_name 处理省份名称
     */
    public void setAreaName(String areaname) {
        this.areaName = areaname;
    }

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
 
    /** 
     * 设置信息类型
     *
     * @param Info_type 信息类型
     */
    public void setInfoType(String infotype) {
        this.infoType = infotype;
    }
}
