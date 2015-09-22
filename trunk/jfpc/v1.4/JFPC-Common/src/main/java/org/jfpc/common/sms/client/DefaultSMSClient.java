package org.jfpc.common.sms.client;
import org.jfpc.common.sms.SMSBean;
import org.jfpc.common.sms.server.SMSConfig;

/**
 * 短信网关客户端实现默认类
 * @author Spook
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 * @see <SMSHelper>
 */
public class DefaultSMSClient {

	protected SMSConfig sp;
	public void setConfig(SMSConfig config) {
		sp = config;
	}
	
	public SMSConfig getConfig() {
		return sp;
	}

	/**
	 * 
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(SMSBean sms) throws Exception {
		throw new Exception("目前没有可用的短信网关");
	}
	
	/**
	 * 直接发送短信
	 * @param mobilNumber
	 * @param Message
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(String mobilNumber, String Message) throws Exception {		
		throw new Exception("目前没有可用的短信网关");
	}
}