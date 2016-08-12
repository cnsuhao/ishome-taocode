package com.mcookies.qxy.biz.device;

public class SendTimecardDataPVO {
	String username;
	String password;
	int keepalive;
	String datas;

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

	public int getKeepalive() {
		return keepalive;
	}

	public void setKeepalive(int keepalive) {
		this.keepalive = keepalive;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}
}
