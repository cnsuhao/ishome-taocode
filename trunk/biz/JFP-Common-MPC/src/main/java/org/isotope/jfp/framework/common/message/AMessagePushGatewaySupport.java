package org.isotope.jfp.framework.common.message;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.message.MessageInfoBean;
import org.isotope.jfp.framework.constants.pub.ISPushConstant;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 消息发送（服务端SDK）<br>
 * 由具体业务网关实现
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public abstract class AMessagePushGatewaySupport extends AMessageChannelServiceThreadSupport implements ISPushConstant {

	protected MessageType messageType;

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * 返回当前服务支持推送类型
	 * 
	 * @return
	 */
	public MessageType supportMessageType() {
		return messageType;
	}

	/**
	 * 信息存放队列
	 * 
	 * @return
	 */
	public String monitorChannelName() {
		return channelKey;
	}

	protected int size = 15;

	/**
	 * 设定单次处理信息数目
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 消息推送
	 * 
	 * @param message
	 * @return
	 */
	public abstract RESTResultBean push(MessageInfoBean messageInfo);

	/**
	 * 启动监听
	 */
	public void run() {
		try {
			for (int i = 0; i < size; i++) {
				try {
					Thread.sleep(super.getWaitTime());

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
					e.printStackTrace();
					logger.error(">>>>>服务处理异常....." + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public boolean doInit() throws Exception {
		logger.debug((String) message);
		// 参数初始化
		messageBean = JSON.parseObject((String) message, MessageInfoBean.class);
		return true;
	}

	MessageInfoBean messageBean;

	@Override
	public boolean doCheck() throws Exception {
		if (catchService == null) {
			logger.debug("CatchService is null xxxxx !!!");
			return false;
		}
		if (this.supportMessageType().equals(messageBean.getMessgeType()) == false) {
			return false;
		}
		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		RESTResultBean result = push(messageBean);
		if (ZERO.equals(result.getCode())) {
			return true;
		}
		logger.error(">>>>>信息推送服务处理失败.....push......" + result);
		return false;
	}

	@Override
	public boolean doFinished() throws Exception {
		logger.debug("数据推送成功 ...... " + messageBean);
		return true;
	}
}
