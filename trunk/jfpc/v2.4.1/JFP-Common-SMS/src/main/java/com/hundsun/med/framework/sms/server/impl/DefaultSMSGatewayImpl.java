package com.hundsun.med.framework.sms.server.impl;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.security.value.PBESecurityHelper;
import org.ishome.jfp.framework.sms.UserSMSSendServiceImpl;
import org.ishome.jfp.framework.support.ISSMSGatewaySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认短信通道
 * 
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
 * 
 */
public class DefaultSMSGatewayImpl implements ISSMSGatewaySupport, ISFrameworkConstants {

	private Logger log = LoggerFactory.getLogger(DefaultSMSGatewayImpl.class);
	/**
	 * 是否关闭短信功能
	 */
	private Boolean smsDisabled = false;

	/**
	 * @return the smsDisabled
	 */

	public Boolean getSmsDisabled() {
		return smsDisabled;
	}

	/**
	 * @param smsDisabled
	 *            ;;;;;;the smsDisabled to set
	 */
	public void setSmsDisabled(Boolean smsDisabled) {
		this.smsDisabled = smsDisabled;
	}
	///////////////////////////////////////////////////////////////////////

	// id
	String userid;
	// 帐号
	String account;
	// 密码
	String password;
	// 地址
	String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
		try {
			return PBESecurityHelper.decrypt(this.userid + this.account, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public RESTResultBean doSend(SMSBean message) {
		log.debug(message.toString());
		RESTResultBean rs = new RESTResultBean();
		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage("短信通道未开启");
		return rs;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		UserSMSSendServiceImpl sms = new UserSMSSendServiceImpl();
		sms.send("123456", "15057177411", "协和短信测试");
	}

}
