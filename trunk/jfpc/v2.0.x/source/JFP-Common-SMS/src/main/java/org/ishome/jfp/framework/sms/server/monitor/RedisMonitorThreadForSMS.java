package org.ishome.jfp.framework.sms.server.monitor;

import org.ishome.jfp.framework.beands.SMSBean;
import org.ishome.jfp.framework.sms.SMSChannelConfig;
import org.ishome.jfp.framework.sms.server.SMSGatewayFactory;
import org.ishome.jfp.framework.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * 短信队列监听
 * 
 * @author Spook
 * @version 2.1.3 2015/4/23
 * @since 2.0.0
 */
public class RedisMonitorThreadForSMS extends SMSChannelConfig {
	private static final Logger logger = LoggerFactory.getLogger(RedisMonitorThreadForSMS.class);

	Jedis jedis = JedisUtil.getJedis();
	SMSGatewayFactory smsGatewayFactory;
	
	public SMSGatewayFactory getSmsGatewayFactory() {
		return smsGatewayFactory;
	}

	public void setSmsGatewayFactory(SMSGatewayFactory smsGatewayFactory) {
		this.smsGatewayFactory = smsGatewayFactory;
	}

	public void run() {
		if (jedis == null)
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						if (jedis.exists(keyList)) {
							long length = jedis.llen(keyList);
							if (length > 0) {
								// 定义接口拦截器
								for (int i = 0; i < length; i++) {
									String message = jedis.lpop(keyList);
									// 发送短信
									smsGatewayFactory.doSend(JSON.parseObject(message, SMSBean.class));
								}
							}
						}
					} catch (Exception e) {
						logger.error("Moniting failed.", e);
						if (jedis != null)
							JedisUtil.returnBrokenJedis(jedis);
						jedis = JedisUtil.getJedis();
					}
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
