package org.isotope.jfp.framework.elk;

import org.isotope.jfp.framework.constants.ISLogConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * ELK log运行参数
 * 
 * @author Spook
 * @version 3.2.1 2016/08/17
 * @since 3.2.1 2016/08/17
 */
public class ELKLogConfig implements ISLogConstants {
	private String logServerIP = "127.0.0.1";
	private int logServerPort = 6379;
	private String passWord = "123456";
	private int index = 15;

	public static void main(String[] args) {
		ELKLogConfig config = new ELKLogConfig();
		config.put("CAPTURE1", "111111111");
	}

	private void put(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = loadJedis();
			jedis.set(key,value);
			jedis.expire(key, 30);
			System.out.println(jedis.ttl(key));
			Thread.sleep(2000);
			jedis.get(key);
			System.out.println(jedis.ttl(key));
			Thread.sleep(3000);
			jedis.get(key);
			System.out.println(jedis.ttl(key));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
			jedis = null;
		}
	}

	public Jedis loadJedis() throws Exception {
		Jedis jedis = new Jedis(logServerIP, logServerPort);
		if (EmptyHelper.isNotEmpty(passWord))
			jedis.auth(passWord);
		jedis.select(index);
		return jedis;
	}

	public String getLogServerIP() {
		return logServerIP;
	}

	public void setLogServerIP(String logServerIP) {
		this.logServerIP = logServerIP;
	}

	public int getLogServerPort() {
		return logServerPort;
	}

	public void setLogServerPort(int logServerPort) {
		this.logServerPort = logServerPort;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
}
