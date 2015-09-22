package com.ttnn.business.cs.fvo;

import java.io.Serializable;

import com.ttnn.common.base.bean.TokenBean;
import com.ttnn.common.util.TokenUtil;

public class UserBean extends TokenBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7316296932626037438L;
	
	/**
	 * 客户端IP
	 */
	private String loginIP;
	/**
	 * 客户端MAC
	 */
	private String loginMAC;
	/**
	 * 客户端分类
	 */
	private String loginType;
	
	public String getLoginIP() {
		return loginIP;
	}


	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}


	public String getLoginMAC() {
		return loginMAC;
	}


	public void setLoginMAC(String loginMAC) {
		this.loginMAC = loginMAC;
	}


	public String getLoginType() {
		return loginType;
	}


	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/**
     * 用户登录后系统唯一识别ID
     * @return
     */
    public String getToken(){    	
    	return TokenUtil.getToken(this);
    }
	
}
