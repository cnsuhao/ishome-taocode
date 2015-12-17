package org.isotope.jfp.framework.beands.net;

import org.isotope.jfp.framework.beands.ObjectBean;

/**
 * HTTP代理
 * 
 * @author Spook
 * @version 2.4.2
 * @since 2.4.2 2015/12/17
 *
 */

public class HttpProxyBean extends ObjectBean {
	/**
	 * 识别ID
	 */
	private String id;
	/**
	 * 主机地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * 类别（http/https）
	 */
	private String type;
	/**
	 * 权限使用(1不使用0使用)
	 */
	private String auth;
	/**
	 * 用户名
	 */
	private String user;
	/**
	 * 密码
	 */
	private String pwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
