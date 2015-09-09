package org.isotope.jfp.common.push.server.monitor;

import org.ishome.jfp.framework.beands.pub.PushBean;
import org.ishome.jfp.framework.cache.utils.redis.JedisUtil;
import org.ishome.jfp.framework.push.PushChannelConfig;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.support.ISPushGatewaySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * 推送队列监听
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 */
public class RedisMonitorThreadForPush extends PushChannelConfig {
	private Logger logger = LoggerFactory.getLogger(RedisMonitorThreadForPush.class);

	Jedis jedis = JedisUtil.getJedis();
	ISPushGatewaySupport pushService;	

	public ISPushGatewaySupport getPushService() {
		return pushService;
	}

	public void setPushService(ISPushGatewaySupport pushService) {
		this.pushService = pushService;
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
									if(EmptyHelper.isNotEmpty(message))
										// 发送短信
										pushService.doPush(JSON.parseObject(message, PushBean.class));
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

}
