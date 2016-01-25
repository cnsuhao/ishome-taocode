package org.ishome.jfp.framework.beands;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.JedisUtil;

import redis.clients.jedis.Jedis;


/**
 * Redis通道获取
 * 
 * @author Spook
 * @version 2.0.1 2015/6/11
 * @since 2.0.1 2015/7/7 
 */
public class RedisChannelConfigBean implements ISFrameworkConstants {

	String host;
	int port;
	String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Jedis getJedis() {
		return JedisUtil.getJedis(host, port, password);
	}

}
