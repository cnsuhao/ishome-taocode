package org.isotope.jfp.framework.common.message;

import org.isotope.jfp.framework.biz.ISFinished;
import org.isotope.jfp.framework.biz.ISReceive;
import org.isotope.jfp.framework.biz.common.ISCheck;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 消息发送框架超类<br>
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public abstract class AMessageChannelServiceThreadSupport extends CommonChannelConfig
		implements Runnable, ISFrameworkConstants, ISReceive, ISInit, ISCheck, ISProcess, ISFinished {
	/**
	 * 等待时间（毫秒）
	 */
	private int waitTime = 500;

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
		catchService.selectDB(defaultIndex);
		message = catchService.peekFirstObjectInList(channelKey, false);
		catchService.init();
		if (EmptyHelper.isEmpty(message))
			return false;
		return true;
	}

	/**
	 * 缓存数据（JSON格式）
	 */
	protected Object message;

	public void process() {
		(new Thread(this)).start();
	}

	/**
	 * 启动监听
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
									logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理成功<<<<<");
								} else {
									logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理失败.....doFinished");
								}
							} else {
								logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理失败.....doProcess");
							}
						} else {
							logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理失败.....doCheck");
						}
					} else {
						logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理失败.....doInit");
					}
				} else {
					logger.info(">>>>>AMessageChannelServiceThreadSupport服务处理失败.....doReceive");
				}
			} catch (Exception e) {
				logger.error(">>>>>AMessageChannelServiceThreadSupport服务处理异常....." + e.getMessage());
			}
		}
	}
}
