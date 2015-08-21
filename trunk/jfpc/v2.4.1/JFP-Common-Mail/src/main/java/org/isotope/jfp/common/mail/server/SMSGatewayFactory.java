package org.isotope.jfp.common.mail.server;

import java.util.Map;
import java.util.Map.Entry;

import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.support.ISSMSGatewaySupport;

/**
 * 短信发送工厂
 * 
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
 */
public class SMSGatewayFactory implements ISSMSGatewaySupport, ISFrameworkConstants {
	/**
	 * 默认网关
	 */
	ISSMSGatewaySupport defaultGateway;

	public ISSMSGatewaySupport getDefaultGateway() {
		return defaultGateway;
	}

	public void setDefaultGateway(ISSMSGatewaySupport defaultGateway) {
		this.defaultGateway = defaultGateway;
	}

	/**
	 * 短信短信网关过滤规则定义
	 */
	Map<String, String> smsGatewayConfig;

	public Map<String, String> getSmsGatewayConfig() {
		return smsGatewayConfig;
	}

	public void setSmsGatewayConfig(Map<String, String> smsGatewayConfig) {
		String[] keys;
		try {
			for (Entry<String, String> entry : smsGatewayConfig.entrySet()) {
				// 数据二次整理
				keys = entry.getKey().split(SEMICOLON);
				for (String k : keys)
					this.smsGatewayConfig.put(k, entry.getValue());
			}
		} catch (Exception e) {
			this.smsGatewayConfig = smsGatewayConfig;
		}
	}

	/**
	 * 短信发送
	 */
	@Override
	public RESTResultBean doSend(SMSBean message) {
		ISSMSGatewaySupport curSMSGateway = null;
		// 获得手机号段
		String key = smsGatewayConfig.get(message.getPhoneNum().substring(0, 3));
		if (EmptyHelper.isNotEmpty(key)) {
			// 获得手机号段对应的网关
			curSMSGateway = BeanFactoryHelper.getBean(key);
		}
		// 如果不存在使用默认网关
		if (curSMSGateway == null) {
			curSMSGateway = defaultGateway;
		}
		return curSMSGateway.doSend(message);
	}

}
