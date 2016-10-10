package org.isotope.jfp.framework.beans.common;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.user.TokenBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author Spook
 * @version 0.1
 * @since 0.2.0.0
 */
@Named
public class RESTResultBean extends TokenBean implements ISFrameworkConstants {

	/**
	 * 返回结果(0成功1失败) 11->token is invalid 12->token is timeout 13->Illegal
	 * interface calls, not power use interface
	 */
	protected int status = 0;

	/**
	 * 提示信息
	 */
	protected String info = "OK";// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	protected Object data = EMPTY;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	//////////////////////////////////覆盖框架内容///////////////////////////////////////

	/**
	 * 设定返回值
	 */
	public void setCode(String resultCode) {
		this.status = Integer.parseInt(resultCode);
	}
	/**
	 * 设定返回信息
	 */
	public void setMessage(String resultMsg) {
		this.info = resultMsg;
	}

	/**
	 * 设定返回对象
	 */
	public void setResult(Object result) {
		this.data = result;
	}

}
