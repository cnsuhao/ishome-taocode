package org.isotope.jfp.framework.beans.user;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.DateHelper;

/**
 * 用户登录信息
 * 
 * @author Spook
 * @version 2.0.1 2015/07/07
 * @version 1.1.0 2014/12/15
 * @version 0.1.0 2014/05/30
 * @since 0.1.0 2014/5/30
 */

public class TokenBean extends FrameworkDataBean implements ISFrameworkConstants {
	/**
	 * IP地址
	 */
	private String ipAdress;
	/**
	 * 登录时间
	 */
	private String clientTimestamp;
	/**
	 * 用户会话标识
	 */
	private String sessionId;
	/**
	 * 服务器认证授权码（登记授权）
	 */
	private String token;

	///////////////////////////////////////////////////////////////////////////////////
	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp() {
		this.clientTimestamp = DateHelper.currentTimestamp();
	}

	public void setClientTimestamp(String clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
