package org.isotopes.jfp.framework.support.bean;

import java.io.Serializable;

import javax.inject.Named;

/**
 * 数据返回主体
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 * @see TokenBean
 */
@Named
public class RESTResultBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4191402626708784051L;

	/**
	 * 授权码
	 */
	private String token = "";

	/**
	 * 返回结果
	 */
	private String resultcode = "0";
	
	/**
	 * 提示信息
	 */
	private String resultmessage = "ok";
	
	/**
	 * 交互数据内容
	 */
	private Object resultobject;

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

	public Object getResultobject() {
		return resultobject;
	}

	public void setResultobject(Object resultobject) {
		this.resultobject = resultobject;
	}

	@Override
	public String toString() {
		return "RESTResultBean [token=" + token + ", resultcode=" + resultcode + ", resultmessage=" + resultmessage + ", resultobject=" + resultobject + "]";
	}

}
