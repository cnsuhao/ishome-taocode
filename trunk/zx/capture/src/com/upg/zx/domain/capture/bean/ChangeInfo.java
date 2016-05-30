package com.upg.zx.domain.capture.bean;

import java.util.Date;

public class ChangeInfo {
	//变更事项
	private String bgsx;
	//变更前内容
	private String bgqnr;
	//变更后内容
	private String bghnr;
	//变更日期
	private Date bgrq;
	
	public String getBgsx() {
		return bgsx;
	}
	public void setBgsx(String bgsx) {
		this.bgsx = bgsx;
	}
	public String getBgqnr() {
		return bgqnr;
	}
	public void setBgqnr(String bgqnr) {
		this.bgqnr = bgqnr;
	}
	public String getBghnr() {
		return bghnr;
	}
	public void setBghnr(String bghnr) {
		this.bghnr = bghnr;
	}
	public Date getBgrq() {
		return bgrq;
	}
	public void setBgrq(Date bgrq) {
		this.bgrq = bgrq;
	}
	
	
}
