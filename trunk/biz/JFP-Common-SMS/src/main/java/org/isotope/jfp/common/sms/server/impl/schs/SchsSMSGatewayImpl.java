package org.isotope.jfp.common.sms.server.impl.schs;

import org.isotope.jfp.common.sms.server.impl.DefaultSMSGatewayImpl;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.pub.SMSBean;
import org.isotope.jfp.framework.utils.HttpServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 北京领先短信平台 验证码通道模版报备发送
 * 
 * @author fucy
 * 
 */
public class SchsSMSGatewayImpl extends DefaultSMSGatewayImpl {
	private Logger logger = LoggerFactory.getLogger(SchsSMSGatewayImpl.class);

	public SchsSMSGatewayImpl() {
		this.setUrl("http://code.58yhkj.com:7862/sms");
		this.setAccount("922013");
		this.setPassword("7fQ3fJ");
	}

	public static void main(String[] args) {
		SchsSMSGatewayImpl sms = new SchsSMSGatewayImpl();

		SMSBean message = new SMSBean();
		message.setPhoneNum("15057177411");
		message.setMessage("【千校云】尊敬的用户，您的验证码为654321，本次验证码30分钟内有效，感谢您的使用");

		sms.doSend(message);
	}

	SchsBean shjz;

	@Override
	public RESTResultBean doSend(SMSBean message) {
		if (isDisabled() == false) {
			logger.debug(message.toString());

			shjz = new SchsBean();
			shjz.setUserid(this.getUserid());
			shjz.setAccount(this.getAccount());
			shjz.setPassword(this.getPassword());
			shjz.setContent(message.getMessage());
			shjz.setMobile(message.getPhoneNum().replaceAll(";", ","));

			try {
				String respose = HttpServiceHelper.doHttpPOST(getUrl(), shjz);
				System.out.println(respose);
				logger.debug(respose);
				RESTResultBean rs = new RESTResultBean();
				rs.setResult(respose);
				return rs;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// ("未开启短信服务功能!");
			return null;
		}
		return null;
	}

}
