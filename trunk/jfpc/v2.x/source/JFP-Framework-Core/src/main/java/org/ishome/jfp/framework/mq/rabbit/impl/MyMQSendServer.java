package org.ishome.jfp.framework.mq.rabbit.impl;

import org.ishome.jfp.framework.constants.ISMQConstants;
import org.ishome.jfp.framework.mq.rabbit.bean.MQConfigBean;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


/**
 * MQ发送服务端
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
//@component
public class MyMQSendServer implements ISMQConstants {

	ConnectionFactory cf;
	MQConfigBean target;
	
	public MyMQSendServer(MQConfigBean config){
		target = config;
	}

	/**
	 * 参数初始化
	 * @return
	 */
	public boolean init() {
		cf = new CachingConnectionFactory(target.getHostIp(), target.getHostPort());

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue(target.getQueueName() + NAME_QUEUE);
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange(target.getQueueName() + NAME_EXCHANGE);
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(target.getQueueName() + ".*"));

		return true;
	}

	/**
	 * 发送信息
	 * @param message
	 * @return
	 */
	public boolean send(String message) {
		RabbitTemplate template = new RabbitTemplate(cf);
		template.setExchange(target.getQueueName() + NAME_EXCHANGE);
		template.setRoutingKey(target.getQueueName() + "." + NAME_BAR);
		template.convertAndSend(message);
		return true;
	}

}
