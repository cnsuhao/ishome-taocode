package com.upg.zx.domain.capture.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Column {
	//属性
	private String property;
	//java类型
	private String javaType;
	//日期格式
	private String dateForm;
	//源数据属性
	private String propertySource;
	//源数据类型(主要分为两类，单个或多个，single,multiple)
	private String typeSource;
	//值得正则
	private String valReg;
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getDateForm() {
		return dateForm;
	}
	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}
	public String getPropertySource() {
		return propertySource;
	}
	public void setPropertySource(String propertySource) {
		this.propertySource = propertySource;
	}
	public String getTypeSource() {
		return typeSource;
	}
	public void setTypeSource(String typeSource) {
		this.typeSource = typeSource;
	}
	public String getValReg() {
		return valReg;
	}
	public void setValReg(String valReg) {
		this.valReg = valReg;
	}
	

}
