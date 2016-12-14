package org.isotope.jfp.framework.beands.common;

import org.isotope.jfp.framework.beands.ObjectBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

import redis.clients.jedis.Jedis;

/**
 * Redis通道获取
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 */
public class RedisChannelConfigBean extends ObjectBean implements ISFrameworkConstants {

	String host;
	int port = 6379;
	String password;
	int db = 0;

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

	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		this.db = db;
	}

	/**
	 * 获得一个Redis连接
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		return getJedis(host, port, password, db);
	}

	public static Jedis getJedis(String host, Integer port) {
		Jedis jedis = new Jedis(host, port);
		jedis.connect();
		if (jedis.isConnected())
			return jedis;
		return null;
	}

	public static Jedis getJedis(String host, Integer port, String password) {
		try {
			Jedis jedis = new Jedis(host, port);
			jedis.auth(password);
			jedis.connect();
			if (jedis.isConnected())
				return jedis;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Jedis getJedis(String host, Integer port, String password, int database) {
		try {
			Jedis jedis = new Jedis(host, port);
			jedis.auth(password);
			jedis.select(database);
			jedis.connect();
			if (jedis.isConnected())
				return jedis;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
