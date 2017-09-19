package org.isotope.jfp.framework.elk;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.constants.ISLogConstants;

/**
 * 日志结构体
 * 
 * @author Spook
 * @version 3.2.1 2016/08/17
 * @since 3.2.1 2016/08/17
 */
public class ELKLogData extends ObjectBean implements ISLogConstants {

	private String dataType;// 数据类别;

	private String ipAdress;// IP地址;

	private String countType;// 统计分类;
	private String serviceType;// 服务分类;

	private String functionName;// 功能名称;
	private String businessName;// 业务名称;

	private String explain1;// 说明1;
	private String explain2;// 说明2;
	private String explain3;// 说明3;
	private Object comment;// 备注;
	public ELKLogData() {
	}
	public ELKLogData(String datatype) {
		this.dataType = datatype;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getExplain1() {
		return explain1;
	}

	public void setExplain1(String explain1) {
		this.explain1 = explain1;
	}

	public String getExplain2() {
		return explain2;
	}

	public void setExplain2(String explain2) {
		this.explain2 = explain2;
	}

	public String getExplain3() {
		return explain3;
	}

	public void setExplain3(String explain3) {
		this.explain3 = explain3;
	}

	public Object getComment() {
		return comment;
	}

	public void setComment(Object comment) {
		this.comment = comment;
	}
	
	///////////////////////////////////////////////
	public ELKLoggerAppender loadELKLoggerAppender(){
		return new ELKLoggerAppender(this);
	}
}
