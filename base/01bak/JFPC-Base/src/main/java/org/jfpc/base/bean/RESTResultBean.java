package org.jfpc.base.bean;

import javax.inject.Named;

/**
 * 数据返回主体
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class RESTResultBean {
	 
	/**
	 * 授权码
	 */
	private String token = "";

	/**
	 * 返回结果(0成功1失败)
	 */
	private String resultcode = "0";
	
	/**
	 * 提示信息
	 */
	private String resultmessage = "ok";
	
	/**
	 * 提示信息
	 */
	private Object result = "";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultmessage() {
		return resultmessage;
	}

	public void setResultmessage(String resultmessage) {
		this.resultmessage = resultmessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RESTResultBean [token=" + token + ", resultcode=" + resultcode + ", resultmessage=" + resultmessage + ", result=" + result + "]";
	}

}
