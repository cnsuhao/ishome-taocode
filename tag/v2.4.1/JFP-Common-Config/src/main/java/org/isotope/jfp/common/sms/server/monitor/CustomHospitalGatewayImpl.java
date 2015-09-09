package org.isotope.jfp.common.sms.server.monitor;

import java.util.LinkedHashMap;

import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.support.ISSMSGatewaySupport;

/**
 * 客户自定义短信通道
 * 
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
 * 
 */
public class CustomHospitalGatewayImpl implements ISSMSGatewaySupport, ISFrameworkConstants {
	/**
	 * 日常监听业务
	 */
	private LinkedHashMap<String, String> monitorConfig =new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getMonitorConfig() {
		return monitorConfig;
	}

	public void setMonitorConfig(LinkedHashMap<String, String> monitorConfig) {
		this.monitorConfig = monitorConfig;
	}

	@Override
	public RESTResultBean doSend(SMSBean message) {
		RESTResultBean rs = new RESTResultBean();
		if (monitorConfig != null && monitorConfig.containsKey(message.getHosId())) {
			// 获得手机号段对应的网关
			ISSMSGatewaySupport curSMSGateway = BeanFactoryHelper.getBean(message.getHosId());
			curSMSGateway.doSend(message);
			rs.setResult(ONE);
		}else{
			rs.setResult(ZERO);
		}
		return rs;
	}

}
