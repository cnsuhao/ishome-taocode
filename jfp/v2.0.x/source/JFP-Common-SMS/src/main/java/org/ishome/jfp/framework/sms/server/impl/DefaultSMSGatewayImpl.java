package org.ishome.jfp.framework.sms.server.impl;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.sms.UserSMSSendServiceImpl;
import org.ishome.jfp.framework.sms.utils.ISSMSGatewaySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 默认短信通道
 * 
 * @author Spook
 * @version 2.1.3 2015/4/23
 * @since 2.0.0
 * 
 */
public class DefaultSMSGatewayImpl implements ISSMSGatewaySupport,ISFrameworkConstants {

	private static Logger log = LoggerFactory.getLogger(DefaultSMSGatewayImpl.class);


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
		sms.send("123456", "15057177411", "短信测试");
	}

}
