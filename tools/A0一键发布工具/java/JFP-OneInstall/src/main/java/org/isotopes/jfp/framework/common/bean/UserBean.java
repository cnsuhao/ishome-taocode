package org.isotopes.jfp.framework.common.bean;

import java.io.Serializable;

import org.isotopes.jfp.framework.common.util.DateUtil;
import org.isotopes.jfp.framework.common.util.TokenUtil;
import org.isotopes.jfp.framework.support.bean.TokenBean;


/**
 * 用户登录信息
 * @author Spook
 * @since 0.1 2012-7-13
 * @version 0.1.0.0
 *
 */
public class UserBean extends TokenBean implements Serializable {

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
	 * 登录时间
	 */
	private String loginDateTime = DateUtil.currentTimeMillis3();

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	/**
     * 用户登录后系统唯一识别ID
     * @return
     */
    public String getToken(){    	
    	return TokenUtil.getToken(this);
    }
	
}
