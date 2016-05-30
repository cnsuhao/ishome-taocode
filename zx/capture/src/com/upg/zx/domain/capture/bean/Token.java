package com.upg.zx.domain.capture.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 

/**
 * token信息
 * @author lujf
 *
 */
public class Token{
	//参数存储
	private Map<String,String> param = new HashMap<String,String>();
	//cookie
	private List cookies;
	
	private String jesssionId;
	//设置创建时间
	private Date createDate;
	
	//过期时间
	private long timeOut;

	public Token(){
	}
	
	public Token(List cookies,String jesssionId,Map<String,String>param,long timeOut){
		this.param = param;
		this.cookies = cookies;
		this.jesssionId = jesssionId;
		this.createDate = new Date();
		this.timeOut = timeOut;
	
	}
	
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Map<String, String> getParam() {
		return param;
	}

	public void setParam(Map<String, String> param) {
		this.param = param;
	}


	public List getCookies() {
		return cookies;
	}

	public void setCookies(List cookies) {
		this.cookies = cookies;
	}

	public String getJesssionId() {
		return jesssionId;
	}

	public void setJesssionId(String jesssionId) {
		this.jesssionId = jesssionId;
	}
	
	
	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	
	/**
	 * 是否过期(单位毫秒)
	 * @return
	 */
	public boolean isExpired(){
		if(createDate.getTime()+timeOut < new Date().getTime()){
			return true;
		}
		return false;
	}
	
}
