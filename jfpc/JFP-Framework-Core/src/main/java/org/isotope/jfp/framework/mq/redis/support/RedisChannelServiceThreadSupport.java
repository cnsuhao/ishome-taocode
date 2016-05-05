package org.isotope.jfp.framework.mq.redis.support;

import org.isotope.jfp.framework.biz.ISFinished;
import org.isotope.jfp.framework.biz.ISReceive;
import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通道服务超类
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @since 2.4.1
 */
public abstract class RedisChannelServiceThreadSupport extends Thread implements ISFrameworkConstants, ISReceive, ISInit, ISCheck, ISProcess, ISFinished {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 等待时间（毫秒）
	 */
	private int waitTime = 500;

	/**
	 * Redis通道定义
	 */
	private String channelKey;
	protected String catchBeanName ;

	public String getCatchBeanName() {
		return catchBeanName;
	}

	public void setCatchBeanName(String catchBeanName) {
		this.catchBeanName = catchBeanName;
	}
	/**
	 * 缓存定义
	 */
	private ICacheService catchService;

	public void doInit(String channelKey) {
		this.channelKey = channelKey;
		catchService =  BeanFactoryHelper.getBean(catchBeanName);
	}

	public void doInit( String channelKey, int waitTime) {
		this.channelKey = channelKey;
		catchService =  BeanFactoryHelper.getBean(catchBeanName);
	}

	public ICacheService getCatchService() {
		return catchService;
	}

	public void setCatchService(ICacheService catchService) {
		this.catchService = catchService;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}

	public String getChannelKey() {
		return channelKey;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * 基于消息队列获取数据
	 */
	public boolean doReceive() throws Exception {
		message = catchService.peekFirstObjectInList(channelKey, false);
		if (EmptyHelper.isEmpty(message))
			return false;
		return true;
	}

	/**
	 * 缓存数据（JSON格式）
	 */
	protected Object message;

	/**
	 * 
	 */
	public void run() {

		while (true) {
			try {
				Thread.sleep(waitTime);
				if (doReceive()) {
					if (doInit()) {
						if (doCheck()) {
							if (doProcess()) {
								if (doFinished()) {
									logger.info(">>>>>服务处理成功<<<<<");
								} else {
									logger.info(">>>>>服务处理失败.....doFinished");
								}
							} else {
								logger.info(">>>>>服务处理失败.....doProcess");
							}
						} else {
							logger.info(">>>>>服务处理失败.....doCheck");
						}
					} else {
						logger.info(">>>>>服务处理失败.....doInit");
					}
				}
			} catch (Exception e) {
				logger.error(">>>>>服务处理异常....." + e.getMessage());
			}
		}
	}
}
