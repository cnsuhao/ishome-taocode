package org.isotope.jfp.framework.mq.bean;

import org.isotope.jfp.framework.beands.ObjectBean;

/**
 * MQ配置信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class MQConfigBean extends ObjectBean {

	/**
	 * 服务地址
	 */
	String hostIp;
	/**
	 * 服务端口
	 */
	int hostPort;
	/**
	 * 超时时间
	 */
	int closeTimeout;

	/**
	 * 队列名称
	 */
	String queueName;

	/**
	 * 用户名
	 */
	String username;
	/**
	 * 密码
	 */
	String password;

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public int getCloseTimeout() {
		return closeTimeout;
	}

	public void setCloseTimeout(int closeTimeout) {
		this.closeTimeout = closeTimeout;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
