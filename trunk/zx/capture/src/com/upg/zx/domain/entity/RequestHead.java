package com.upg.zx.domain.entity;

import com.upg.zx.web.utils.StringUtil;

/**
 * 请求参数表(头部参数以及cookie参数)
 * @author lujf
 *
 */
public class RequestHead {
	private Long id;
	//参数名称
	private String headName;
	//参数值
	private String val;
	//请求信息
	private Long requestInfoId;
	//参数类型，是否是cookie(0:头部信息，1：cookie信息)
	private String type;
	
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
 
	
 
	public Long getRequestInfoId() {
		return requestInfoId;
	}
	public void setRequestInfoId(Long requestInfoId) {
		this.requestInfoId = requestInfoId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
