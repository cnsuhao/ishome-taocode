package org.jfpc.common.sms.server;

import org.jfpc.framework.bean.ObjectBean;

/**
 * 短信网关配置
 * @author Spook
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 */
public class SMSConfig extends ObjectBean{
	
	/**
	 * 网关实现类
	 */
	String className;

	/**
	 * 服务状态(0开启1关闭)
	 */
	String smsType = "1";

	/**
	 * 用户名
	 */
	String smsId;
	/**
	 * 密码
	 */
	String smsPassword;
	/**
	 * 通信地址
	 */
	String smsUrl;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
}
