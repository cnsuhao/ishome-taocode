package com.aek56.utils;

public class SmsAek56Bean {
	/**
	 * 用户名
	 */
	private String usr;
	/**
	 * 密码
	 */
	private String psw;
	/**
	 * 授权密钥
	 */
	private String authkey;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 模版编号  0标识不使用模版
	 */
	private String tpl = "0";
	/**
	 * 内容
	 */
	private String text;
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTpl() {
		return tpl;
	}
	public void setTpl(String tpl) {
		this.tpl = tpl;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
