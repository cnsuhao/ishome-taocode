package org.isotope.jfp.common.mail.server.monitor;

import org.isotope.jfp.framework.beands.pub.MailBean;
import org.isotope.jfp.framework.mq.redis.support.RedisChannelServiceThreadSupport;
import org.isotope.jfp.framework.support.ISMailSendSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 邮件队列监听（控制器）
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class MailCenterMonitorThread extends RedisChannelServiceThreadSupport {

	private Logger logger = LoggerFactory.getLogger(MailCenterMonitorThread.class);

	/**
	 * 短信网关
	 */
	private ISMailSendSupport mailService;
	
	public ISMailSendSupport getMailService() {
		return mailService;
	}

	public void setMailService(ISMailSendSupport mailService) {
		this.mailService = mailService;
	}

	private MailBean msg;

	/**
	 * 检查短信中心配置
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		msg = JSON.parseObject((String) message, MailBean.class);
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		// 企业权限校验

		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		message = mailService.doSend(msg);
		return true;
	}

	@Override
	public boolean doFinished() throws Exception {
		// 记录日志
		// RESTResultBean result = (RESTResultBean) message;
		// TODO

		return true;
	}

}
