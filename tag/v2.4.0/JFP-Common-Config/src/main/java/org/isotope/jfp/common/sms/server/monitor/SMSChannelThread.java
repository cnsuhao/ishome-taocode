package org.isotope.jfp.common.sms.server.monitor;

import java.util.List;

import org.ishome.jfp.framework.beands.common.RedisChannelConfigBean;
import org.ishome.jfp.framework.beands.pub.SMSBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.common.mail.server.SMSGatewayFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * 短信通道监听程序(阻塞模式)
 * 
 * @author fucy
 * @version 2.3.3 2015/7/29
 * @since 2.3.3
 * 
 */
public class SMSChannelThread extends Thread implements ISFrameworkConstants {
	private Logger logger = LoggerFactory.getLogger(SMSChannelThread.class);

	Jedis jedis;
	RedisChannelConfigBean redisChannelConfig;// 缓存中心定义
	SMSGatewayFactory smsGatewayFactory;// 短信中心定义
	String keyList;// 通道中心定义

	public SMSChannelThread(SMSGatewayFactory smsGatewayFactory, RedisChannelConfigBean redisChannelConfig, String keyList) {
		// 短信中心定义
		this.smsGatewayFactory = smsGatewayFactory;
		// 缓存中心定义
		this.redisChannelConfig = redisChannelConfig;
		// 通道中心定义
		this.keyList = keyList;
	}

	@Override
	public void run() {
		CustomHospitalGatewayImpl chg = BeanFactoryHelper.getBean("customHospitalGatewayImpl");
		while (true) {
			try {
				logger.debug("keyList====" + keyList+"====="+redisChannelConfig.getHost());
				Thread.sleep(1000);
				if (jedis == null)
					jedis = redisChannelConfig.getJedis();

				List<String> list = jedis.blpop(15,keyList);
				if(list!=null&&list.size()==2){
					// 发送短信					
					SMSBean sms = JSON.parseObject(list.get(1), SMSBean.class);
					if(ZERO.equals(chg.doSend(sms).getResult()))
						smsGatewayFactory.doSend(sms);
				}				
			} catch (Exception e) {
				logger.error(e.getMessage());
				jedis = null;
			}
		}
	}
}
