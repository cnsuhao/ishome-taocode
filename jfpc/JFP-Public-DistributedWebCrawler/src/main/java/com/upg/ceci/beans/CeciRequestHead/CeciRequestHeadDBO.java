package com.upg.ceci.beans.CeciRequestHead;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciRequestHeadDBO extends MyDataBaseObjectSupport
{
    /** 
     * 主键ID
     */
    private String id = null;
    
    /** 
     * 区域码
     */
    private String areaCode = null;
 
    public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/** 
     * 参数名称
     */
    private String headName = null;
 
    /** 
     * 参数值
     */
    private String val = null;
 
    /** 
     * 请求信息id
     */
    private String requestInfoId = null;
 
    /** 
     * 类型(0:头部信息，1：cookie信息)
     */
    private String type = null;
 
    /** 
     * 获取主键ID
     *
     * @return ID 主键ID
     */
    public String getId() {
        return this.id;
    }
 
    /** 
     * 获取参数名称
     *
     * @return Head_name 参数名称
     */
    public String getHeadName() {
        return this.headName;
    }
 
    /** 
     * 获取参数值
     *
     * @return Val 参数值
     */
    public String getVal() {
        return this.val;
    }
 
    /** 
     * 获取请求信息id
     *
     * @return Request_info_id 请求信息id
     */
    public String getRequestInfoId() {
        return this.requestInfoId;
    }
 
    /** 
     * 获取类型(0:头部信息，1：cookie信息)
     *
     * @return Type 类型(0:头部信息，1：cookie信息)
     */
    public String getType() {
        return this.type;
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
     * 设置参数名称
     *
     * @param Head_name 参数名称
     */
    public void setHeadName(String headname) {
        this.headName = headname;
    }
 
    /** 
     * 设置参数值
     *
     * @param Val 参数值
     */
    public void setVal(String val) {
        this.val = val;
    }
 
    /** 
     * 设置请求信息id
     *
     * @param Request_info_id 请求信息id
     */
    public void setRequestInfoId(String requestinfoid) {
        this.requestInfoId = requestinfoid;
    }
 
    /** 
     * 设置类型(0:头部信息，1：cookie信息)
     *
     * @param Type 类型(0:头部信息，1：cookie信息)
     */
    public void setType(String type) {
        this.type = type;
    }
 
}
