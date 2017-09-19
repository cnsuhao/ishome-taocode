package org.isotope.jfp.common.mail.server.monitor;

import org.isotope.jfp.framework.beands.pub.LogBean;
import org.isotope.jfp.framework.mq.redis.support.RedisChannelServiceThreadSupport;
import org.isotope.jfp.framework.support.ISLogSaveSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 日志队列监听（控制器）
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class LogCenterMonitorThread extends RedisChannelServiceThreadSupport {

	private Logger logger = LoggerFactory.getLogger(LogCenterMonitorThread.class);

	/**
	 * 日志中心
	 */
	private ISLogSaveSupport logService;

	public ISLogSaveSupport getLogService() {
		return logService;
	}

	public void setLogService(ISLogSaveSupport logService) {
		this.logService = logService;
	}

	private LogBean msg;

	/**
	 * 检查短信中心配置
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		msg = JSON.parseObject((String) message, LogBean.class);
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		// 企业权限校验

		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		message = logService.doSave(msg);
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
