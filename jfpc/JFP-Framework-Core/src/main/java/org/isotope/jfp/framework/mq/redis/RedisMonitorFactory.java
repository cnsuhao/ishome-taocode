package org.isotope.jfp.framework.mq.redis;

import java.util.ArrayList;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.mq.redis.support.RedisChannelServiceThreadSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通道监听工厂
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @since 2.4.1
 */
public class RedisMonitorFactory implements ISFrameworkConstants {
	private Logger logger = LoggerFactory.getLogger(RedisMonitorFactory.class);

	/**
	 * Redis通道定义
	 */
	private ArrayList<String> channelList = new ArrayList<String>();

	public void setChannelList(ArrayList<String> channelList) {
		this.channelList = channelList;
	}

	/**
	 * 监听服务
	 */
	private RedisChannelServiceThreadSupport service;

	public void setService(RedisChannelServiceThreadSupport service) {
		this.service = service;
	}

	public void run() {
		if (service == null) {
			logger.error(">>>>>监控服务没有定义...xxx");
			return;
		}
		// 多通道
		for (String channelKey : channelList) {
			if (EmptyHelper.isNotEmpty(channelKey)) {
				service.doInit(channelKey);
				service.start();
				logger.debug(">>>>>监控服务启动成功<<<<<....." + channelKey);
			}
		}
	}
}
