package org.ishome.jfp.pdp.conf;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;


public class MyJsonData implements ISFrameworkConstants {
	// ////////////////////////////////////////////////////////////////////////////////
	/**
	 * 返回结果(0：成功、其他：失败（业务系统提示码）)
	 */
	protected String returnCode = ZERO;// 

	/**
	 * 提示信息
	 */
	protected String returnMessage = JOB_MESSAGE_ERROR;// 
	/**
	 * 接口返回的数据
	 */
	protected Object returnObject = EMPTY;
	
	/**
	 * 客户端返回
	 * 提示的信息
	 */
	protected String alertMessag = "成功!";

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		if (EmptyHelper.isEmpty(returnObject)) {
			this.returnObject = EMPTY;
		} else {
			this.returnObject = returnObject;
		}
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	// ////////////////////////////////////////////////////////////////////////////////	

	//
	// public void setReturnObject(String jsonData) {
	// //数据加密
	// this.jsonData = CloundApiConfig_.encryption(bizName, jsonData);
	// }
	//
	/**
	 * 业务请求API key 名称
	 */
	protected String bizName;

	public String getBizName() {
		if (EmptyHelper.isEmpty(bizName))
			bizName = this.getClass().getSimpleName();
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getAlertMessag() {
		return alertMessag;
	}

	public void setAlertMessag(String alertMessag) {
		this.alertMessag = alertMessag;
	}
	
}
