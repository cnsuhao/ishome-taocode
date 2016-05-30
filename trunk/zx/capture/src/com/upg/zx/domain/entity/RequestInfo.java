package com.upg.zx.domain.entity;

import java.util.List;

/**
 * 请求信息表
 * 
 * @author lujf
 * 
 */
public class RequestInfo {
	// id 自增
	private Long id;
	// 区域编码
	private String areaCode;

	// 访问域名
	private String host;
	// 请求地址
	private String rurl;
	// 是否设置Cookie
	private String isCookie;
	// Cookie域
	private String cookieDomen;
	// Cookie路径
	private String cookiePath;
	// 请求模块类型
	private String modeType;
	// 类型描述
	private String modeTypeDesc;
	// 请求方式(post,get)
	private String requestType;
	// 信息类型(0:与列表相关的请求，1：基本信息以及其他信息)
	private String infoType;

	// 子请求id
	private String subId;
	
	private String params;

	private List<RequestHead> headList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRurl() {
		return rurl;
	}

	public void setRurl(String rurl) {
		this.rurl = rurl;
	}

	public String getIsCookie() {
		return isCookie;
	}

	public void setIsCookie(String isCookie) {
		this.isCookie = isCookie;
	}

	public String getCookieDomen() {
		return cookieDomen;
	}

	public void setCookieDomen(String cookieDomen) {
		this.cookieDomen = cookieDomen;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public List<RequestHead> getHeadList() {
		return headList;
	}

	public void setHeadList(List<RequestHead> headList) {
		this.headList = headList;
	}

	public String getModeTypeDesc() {
		return modeTypeDesc;
	}

	public void setModeTypeDesc(String modeTypeDesc) {
		this.modeTypeDesc = modeTypeDesc;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	

}
