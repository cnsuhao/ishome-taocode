package org.ishome.jfp.framework.push.server.impl;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.ishome.jfp.beans.PhoneUser.PhoneUserDBO;
import org.ishome.jfp.beans.PhoneUserTerminal.PhoneUserTerminalDBO;
import org.ishome.jfp.framework.beands.PushBean;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.RedisChannelConfigBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISPushMessageConstant;
import org.ishome.jfp.framework.push.beans.PushService;
import org.ishome.jfp.framework.push.utils.ISPushGatewaySupport;
import org.ishome.jfp.framework.sms.UserSMSSendServiceImpl;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * 默认短信通道
 * 
 * @author Spook
 * @version 2.0.1 2015/6/15
 * @since 2.0.1 2015/7/7
 * 
 */
public class DefaultPushServiceImpl implements ISPushMessageConstant, ISPushGatewaySupport, ISFrameworkConstants {

	private static Logger log = LoggerFactory.getLogger(DefaultPushServiceImpl.class);

	@Resource
	PushService PushService_;

	RedisChannelConfigBean redisChannelConfig;

	public RedisChannelConfigBean getRedisChannelConfig() {
		return redisChannelConfig;
	}

	public void setRedisChannelConfig(RedisChannelConfigBean redisChannelConfig) {
		this.redisChannelConfig = redisChannelConfig;
	}

	@Override
	public RESTResultBean doPush(PushBean message) {
		log.debug(message.toString());
		RESTResultBean rs = new RESTResultBean();

		// 获得用户ID
		if (EmptyHelper.isEmpty(message.getUserId())) {
			PhoneUserDBO phoneUser = new PhoneUserDBO();
			phoneUser.setHosId(message.getHosId());
			phoneUser.setMobilePhone(message.getPhoneNum());
			phoneUser = PushService_.getPhoneUserId(phoneUser);

			if (phoneUser == null) {
				rs.setCode(SYSTEM_ERROR_CODE);
				rs.setMessage("用户未注册");
				return rs;
			}
			message.setUserId(phoneUser.getUsId());
		}
		// 获得用户终端类型
		if (EmptyHelper.isEmpty(message.getPhoneType())) {
			PhoneUserTerminalDBO phoneUserTerminal = new PhoneUserTerminalDBO();
			phoneUserTerminal.setHosId(message.getHosId());
			phoneUserTerminal.setUsId(message.getUserId());
			phoneUserTerminal = PushService_.getPhoneUserTerminalType(phoneUserTerminal);
			if (phoneUserTerminal == null) {
				rs.setCode(SYSTEM_ERROR_CODE);
				rs.setMessage("用户未注册");
				return rs;
			}
			message.setPhoneType(phoneUserTerminal.getTerminalType());
			message.setToken(phoneUserTerminal.getToken());
		}
		// message.setPhoneType("1");

		// 手机Key
		String key = getPushKey(message.getHosId(), message.getUserId());
		// 终端类型
		int phoneType = Integer.parseInt(message.getPhoneType());

		// 发送到推送网关服务 1-android,2-ios,8-alipay,9-weixin
		if (1 == phoneType) {
			MedAndroidPushMesssage mp = new MedAndroidPushMesssage(key, message.getTitle(), message.getMessage(), phoneType);
			publish(key, PUSH_CHANNEL, JSON.toJSONString(mp));
		} else if (2 == phoneType) {
			MedIosPushMesssage mp = new MedIosPushMesssage(key, message.getTitle(), message.getMessage(), phoneType, message.getToken(), getCertificateKey(message.getHosId(), USER_TYPE_PAT));
			queuePublish(key, PUSH_CHANNEL_IOS, JSON.toJSONString(mp));
		} else if (8 == phoneType) {
			queuePublish(key, PUSH_CHANNEL_ALIPAY, JSON.toJSONString(message));
		} else if (9 == phoneType) {
			queuePublish(key, PUSH_CHANNEL_WEIXIN, JSON.toJSONString(message));
		}

		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage("短信通道未开启");
		return rs;
	}

	public static final String CERT_PATH_KEY = "IOS_CERT:";
	public static final int USER_TYPE_PAT = 0;
	public static final int USER_TYPE_DOC = 1;

	public static String getCertificateKey(String hosId, int userType) {
		return CERT_PATH_KEY + hosId + ":" + userType;
	}

	public String getPushKey(String hosId, String usId) {
		return hosId + "_" + usId;
	}

	private boolean publish(String key, String channel, String message) {
		Jedis jedis = redisChannelConfig.getJedis();
		try {
			jedis.publish(channel, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return true;
	}

	private boolean queuePublish(String key, String channel, String message) {
		Jedis jedis = redisChannelConfig.getJedis();
		try {
			jedis.lpush(key, message);
			jedis.publish(channel, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return true;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		UserSMSSendServiceImpl sms = new UserSMSSendServiceImpl();
		sms.send("123456", "15057177411", "短信测试");
	}

}
