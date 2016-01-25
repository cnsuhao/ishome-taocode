package org.isotope.jfp.common.sms.server.impl.DEMO;

import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.isotope.jfp.common.sms.server.impl.DefaultSMSGatewayImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 北京领先短信平台 验证码通道模版报备发送
 * 
 * @author fucy
 * 
 */
public class BjlxSMSGatewayImpl extends DefaultSMSGatewayImpl {
	private Logger logger = LoggerFactory.getLogger(BjlxSMSGatewayImpl.class);

	public BjlxSMSGatewayImpl() {
	}

	BjlxBean shjz;

	@Override
	public RESTResultBean doSend(SMSBean message) {
		if (!getSmsDisabled()) {
			logger.debug(message.toString());

			shjz = new BjlxBean();
			shjz.setUserid(this.getUserid());
			shjz.setAccount(this.getAccount());
			shjz.setPassword(this.getPassword());
			shjz.setContent(message.getMessage());
			shjz.setMobile(message.getPhoneNum().replaceAll(";", ","));

			try {
				String respose = HttpServiceHelper.doHttpPOST(getUrl(), shjz);
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
