package org.ishome.jfp.framework.sms.server.monitor.sub;

import org.ishome.jfp.framework.support.ISSMSSupport;
import org.ishome.jfp.framework.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;


/**
 * 短信队列监听
 * 
 * @author Spook
 * @version 2.1.3 2015/4/23
 * @since 2.0.0
 */
public class RedisSubscriberThread {
	private static final Logger logger = LoggerFactory.getLogger(RedisSubscriberThread.class);

	Jedis subscriberJedis = JedisUtil.getJedis();
	SMSServiceListener subscriber = new SMSServiceListener();

	public void run() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Subscribing to \"commonChannel\". This thread will be blocked.");
					subscriberJedis.subscribe(subscriber, ISSMSSupport.CONFIG_KEY);
					logger.info("Subscription ended.");
				} catch (Exception e) {
					logger.error("Subscribing failed.", e);
					if(subscriberJedis!=null)
						JedisUtil.returnBrokenJedis(subscriberJedis);
					subscriberJedis = JedisUtil.getJedis();
				}
			}
		}).start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
