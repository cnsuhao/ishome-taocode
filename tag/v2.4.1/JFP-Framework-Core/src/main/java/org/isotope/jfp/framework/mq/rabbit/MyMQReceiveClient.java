package org.isotope.jfp.framework.mq.rabbit;

import org.isotope.jfp.framework.constants.pub.ISMQConstants;
import org.isotope.jfp.framework.mq.bean.MQConfigBean;
import org.isotope.jfp.framework.support.ISMQReceiveSupport;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * MQ接受客户端
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
//@component
public class MyMQReceiveClient implements ISMQConstants {

	MQConfigBean target;
	ISMQReceiveSupport listen;// handleMessage

	public MyMQReceiveClient(MQConfigBean config,ISMQReceiveSupport listen) {
		this.target = config;
		this.listen = listen;
	}

	/**
	 * 开启服务监听
	 */
	public void init() {
		/*
		 * Producer,Routing Key,Exchange,Binding，Queue,Consumer. Producer:
		 * 消息的创建者，消息的发送者 Routing Key：唯一用来映射消息该进入哪个队列的标识 Exchange：负责消息的路由，交换
		 * Binding:定义Queue和Exchange的映射关系 Queue：消息队列 Consumer：消息的使用者 Exchange类型
		 * Fan-Out:类似于广播方式，不管RoutingKey Direct:根据RoutingKey,进行关联投寄
		 * Topic:类似于Direct,但是支持多个Key关联，以组的方式投寄。
		 * key以.来定义界限。类似于usea.news,usea.weather.这两个消息是一组的。
		 */

		ConnectionFactory cf = new CachingConnectionFactory(target.getHostIp(), target.getHostPort());

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue(target.getQueueName() + NAME_QUEUE);
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange(target.getQueueName() + NAME_EXCHANGE);
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(target.getQueueName() + ".*"));

		// set up the listener and container
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);

		MessageListenerAdapter adapter = new MessageListenerAdapter(listen);
		container.setMessageListener(adapter);
		container.setQueues(queue);
		container.start();

		// container.stop();
		//
		//
		// try{
		// super.convertAndSend(message+"111111111111111111");
		// super.convertAndSend(message+"222222222222");
		// return true;
		// }catch(Exception e){
		//
		// }
	}

}
