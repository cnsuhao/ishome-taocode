package org.jfpc.base.bean;

import javax.annotation.Resource;
import javax.inject.Named;

import org.jfpc.base.utils.MessageUtil;

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
	private String code = "0";
	
	/**
	 * 提示信息
	 */
	private String message = "ok";
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String resultcode) {
		this.code = resultcode;
	}

	public String getMessage() {
		return MessageUtil_.getLocalMessage(message);
	}
	
	@Resource
	MessageUtil MessageUtil_;
	public void setMessage(String resultmessage) {
		this.message = resultmessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RESTResultBean [token=" + token + ", code=" + code + ", message=" + message + ", result=" + result + "]";
	}
	
}
