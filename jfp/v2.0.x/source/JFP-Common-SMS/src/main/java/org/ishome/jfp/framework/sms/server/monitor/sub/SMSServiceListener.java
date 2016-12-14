package org.ishome.jfp.framework.sms.server.monitor.sub;

import org.ishome.jfp.framework.beands.SMSBean;
import org.ishome.jfp.framework.sms.server.SMSGatewayFactory;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;

import redis.clients.jedis.JedisPubSub;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送
 * 
 * @author Spook
 * @version 2.1.3 2015/4/23
 * @since 2.0.0
 * 
 */
public class SMSServiceListener extends JedisPubSub {
	// 取得订阅的消息后的处理
	public void onMessage(String channel, String message) {
		System.out.println(channel + "=" + message);
		//定义接口拦截器
		SMSGatewayFactory sgf = BeanFactoryHelper.getBean("SMSGatewayFactory");
		sgf.doSend(JSON.parseObject(message, SMSBean.class));
	}

	// 初始化订阅时候的处理
	public void onSubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 取消订阅时候的处理
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取消按表达式的方式订阅时候的处理
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取得按表达式的方式订阅的消息后的处理
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println(pattern + "=" + channel + "=" + message);
	}
}
