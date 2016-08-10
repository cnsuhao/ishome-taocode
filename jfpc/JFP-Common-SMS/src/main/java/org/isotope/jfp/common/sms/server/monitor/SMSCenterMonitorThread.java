package org.isotope.jfp.common.sms.server.monitor;

import java.util.HashMap;

import org.isotope.jfp.common.sms.server.SMSGatewayFactory;
import org.isotope.jfp.framework.beans.pub.SMSBean;
import org.isotope.jfp.framework.mq.redis.support.RedisChannelServiceThreadSupport;
import org.isotope.jfp.framework.support.ISSMSGatewaySupport;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 短信队列监听（控制器）
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @version 2.1.3 2015/4/23
 * @since 2.1.3
 */
public class SMSCenterMonitorThread extends RedisChannelServiceThreadSupport {

	private Logger logger = LoggerFactory.getLogger(SMSCenterMonitorThread.class);

	/**
	 * 短信网关
	 */
	private SMSGatewayFactory smsGatewayFactory;

	public SMSGatewayFactory getSmsGatewayFactory() {
		return smsGatewayFactory;
	}

	public void setSmsGatewayFactory(SMSGatewayFactory smsGatewayFactory) {
		this.smsGatewayFactory = smsGatewayFactory;
	}

	/**
	 * 企业自定义网关(企业ID，网关服务)
	 */
	private HashMap<String, String> customGateway = new HashMap<String, String>();

	public HashMap<String, String> getCustomGateway() {
		return customGateway;
	}

	public void setCustomGateway(HashMap<String, String> customGateway) {
		this.customGateway = customGateway;
	}

	private SMSBean msg;

	/**
	 * 检查短信中心配置
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		msg = JSON.parseObject((String) message, SMSBean.class);
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		// 企业短信权限校验
		// TODO

		// 企业自定义通道校验
		if (customGateway != null && customGateway.containsKey(msg.getCompanyId())) {
			// 获得手机号段对应的网关
			ISSMSGatewaySupport curSMSGateway = BeanFactoryHelper.getBean(customGateway.get(msg.getCompanyId()));
			curSMSGateway.doSend(msg);
			if (logger.isDebugEnabled())
				logger.debug("企业自定义通道......" + msg.getCompanyId() + ">>>>>" + curSMSGateway);
			return false;
		}

		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		message = smsGatewayFactory.doSend(msg);
		return true;
	}

	@Override
	public boolean doFinished() throws Exception {
		// 记录日志
		//RESTResultBean result = (RESTResultBean) message;
		// TODO
		
		return true;
	}

}
