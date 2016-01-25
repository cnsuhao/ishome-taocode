package org.jfpc.framework.remot;

import org.apache.commons.lang.StringUtils;

public class HttpServiceConfig {


	// 127.0.0.1:8888
	String serverIp = "127.0.0.1";
	String serverPort = "8888";

	public HttpServiceConfig() {
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	
	// 获得服务器地址
	// "http://127.0.0.1:8888/0002/1123213/20140526"
	public String getServerURL(String serviceid) {
		StringBuilder uri = new StringBuilder();
		// 获得访问地址进行拼接
		uri.append("http://")// 通信协议
				.append(serverIp)// 获得服务器地址和端口
				.append(":");// 分隔符
		if(StringUtils.isNotEmpty(serverPort)){
		uri.append(serverPort)// 获得业务访问地址
				.append("/");// 分隔符
		}
				uri.append(serviceid);

		return uri.toString();
	}

}
