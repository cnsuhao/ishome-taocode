package com.upg.zx.domain.entity;

import java.io.Serializable;

public class AnalysisTemplate implements Serializable {
	private Long id;
	//key的正则
	private String keyReg;
	//值的正则
	private String valReg;
	//数据行
	private int dataRow ;
	//标题行
 	private int titleRow;
 	//数据列
 	private int dataColCount;
 	//标签ID
 	private String tabId;
 	//标签
 	private String tag;
 	//选择字符串
 	private String selector;
 	//类型(1:基本信息解析,2:表格解析)
 	private String type;
 	//请求信息id
 	private Long requestInfoId; 
 	//对象xml中解析模板编码
 	private String xmlCode;
 	//对应的实体类
 	private String clazz; 
	 
 	private String templateType;
 	
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRequestInfoId(Long requestInfoId) {
		this.requestInfoId = requestInfoId;
	}
	public String getKeyReg() {
		return keyReg;
	}
	public void setKeyReg(String keyReg) {
		this.keyReg = keyReg;
	}
	public String getValReg() {
		return valReg;
	}
	public void setValReg(String valReg) {
		this.valReg = valReg;
	}
	public int getDataRow() {
		return dataRow;
	}
	public void setDataRow(int dataRow) {
		this.dataRow = dataRow;
	}
	public int getTitleRow() {
		return titleRow;
	}
	public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}
	public int getDataColCount() {
		return dataColCount;
	}
	public void setDataColCount(int dataColCount) {
		this.dataColCount = dataColCount;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getRequestInfoId() {
		return requestInfoId;
	}
	public void setRequestInfoId(long requestInfoId) {
		this.requestInfoId = requestInfoId;
	}
	public String getXmlCode() {
		return xmlCode;
	}
	public void setXmlCode(String xmlCode) {
		this.xmlCode = xmlCode;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
 	
 	
}
