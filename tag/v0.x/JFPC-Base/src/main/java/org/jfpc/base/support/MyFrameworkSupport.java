package org.jfpc.base.support;

import org.apache.commons.lang.StringUtils;
import org.jfpc.base.ISFrameworkConstants;
import org.jfpc.base.cache.SessionHelper;
import org.jfpc.base.helper.TokenHelper;


/**
 * 业务框架超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyFrameworkSupport implements ISFrameworkConstants {
	/**
	 * 用户识别标识
	 */
	private String token="";
	
	public String getToken() {
		if (StringUtils.isEmpty(token)) {
			// 从缓存里面获得
			Object tokenSession = SessionHelper.getSessionAttribute(CONSTANT_USER_TOKEN);
			if (tokenSession != null) {
				token = tokenSession.toString();
			}
		}
		return token;
	}

	final static String undefined = "undefined";
	public void setToken(String token) {
		if (!undefined.equals(token)){
			this.token = token;
			if (StringUtils.isEmpty(sessionid)) 
				this.sessionid = TokenHelper.getLoginDateTime(token);
			SessionHelper.setSessionAttribute(CONSTANT_USER_TOKEN, token);
		}
	}
	
	private String sessionid="";
	
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getLoginerId() {
		return TokenHelper.getUserID(getToken());
	}

	public String getCompanyId() {
		return TokenHelper.getCompanyId(getToken());
	}

}
