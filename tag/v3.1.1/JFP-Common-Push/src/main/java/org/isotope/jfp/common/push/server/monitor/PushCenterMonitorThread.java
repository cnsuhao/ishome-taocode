package org.isotope.jfp.common.push.server.monitor;

import org.isotope.jfp.framework.beands.pub.PushBean;
import org.isotope.jfp.framework.mq.redis.support.RedisChannelServiceThreadSupport;
import org.isotope.jfp.framework.support.ISPushCometSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 推送队列监听（控制器）
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class PushCenterMonitorThread extends RedisChannelServiceThreadSupport {

	private Logger logger = LoggerFactory.getLogger(PushCenterMonitorThread.class);

	/**
	 * 短信网关
	 */
	private ISPushCometSupport pushService;

	public ISPushCometSupport getPushService() {
		return pushService;
	}

	public void setPushService(ISPushCometSupport pushService) {
		this.pushService = pushService;
	}

	private PushBean msg;

	/**
	 * 检查短信中心配置
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		msg = JSON.parseObject((String) message, PushBean.class);
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		// 企业权限校验

		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		message = pushService.doPush(msg);
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
