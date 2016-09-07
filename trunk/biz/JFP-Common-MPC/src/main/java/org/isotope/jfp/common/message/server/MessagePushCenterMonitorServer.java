package org.isotope.jfp.common.message.server;

import java.util.List;

import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.common.message.AMessageChannelServiceThreadSupport;
import org.isotope.jfp.framework.common.message.AMessagePushGatewaySupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 消息中心队列监听（控制器）
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class MessagePushCenterMonitorServer extends AMessageChannelServiceThreadSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected int size = 15;

	/**
	 * 设定单次处理信息数目
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	protected List<? extends AMessagePushGatewaySupport> messageServers;

	public void setMessageServers(List<? extends AMessagePushGatewaySupport> messageServers) {
		this.messageServers = messageServers;
	}

	public void process() {
		(new Thread(this)).start();
	}

	/**
	 * 启动监听
	 */
	public void run() {
		if (messageServers == null || messageServers.size() == 0) {
			logger.error("消息中心配置错误，取消运行 xxxxxxxxxx ！！！！！");
		}

		while (true) {
			try {
				Thread.sleep(super.getWaitTime());

				for (int i = 0; i < messageServers.size(); i++) {
					server = messageServers.get(i);
					server.setCatchService(catchService);
					server.setDefaultIndex(defaultIndex);
					server.setSize(size);
					channelKey = server.monitorChannelName();
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
				}
			} catch (Exception e) {
				logger.error(">>>>>服务处理异常....." + e.getMessage());
			}
		}
	}

	AMessagePushGatewaySupport server;
	MessageInfoBean msg;

	/**
	 * 基于消息队列判断是否存在任务推送
	 */
	public boolean doReceive() throws Exception {
		boolean rst = true;
		catchService.selectDB(defaultIndex);
		message = catchService.peekFirstObjectInList(channelKey, false);
		if (EmptyHelper.isEmpty(message))
			rst = false;
		else
			catchService.offerObjectInList(channelKey, message, false);
		catchService.init();
		return rst;
	}

	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		msg = JSON.parseObject((String) message, MessageInfoBean.class);
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		if (server.supportMessageType().equals(msg.getMessgeType()) == false) {
			return false;
		}
		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		(new Thread(server)).start();
		return true;
	}

	@Override
	public boolean doFinished() throws Exception {
		// 记录日志
		logger.debug("成功运行服务 ..... " + server + "," + msg);
		return true;
	}

}
