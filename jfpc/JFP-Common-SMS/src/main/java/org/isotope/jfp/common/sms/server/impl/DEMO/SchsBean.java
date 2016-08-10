package org.isotope.jfp.common.sms.server.impl.DEMO;

import org.isotope.jfp.framework.beans.ObjectBean;

public class SchsBean extends ObjectBean {
	// id
	String userid;
	// 帐号
	String account;
	// 密码
	String password;
	// 目标号码
	String mobile;
	// 内容
	String content;
	// 定时日期
	String sendTime;
	// 发送任务命令
	String action = "send";
	// 扩展子号
	String extno = "106908013";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}

}
