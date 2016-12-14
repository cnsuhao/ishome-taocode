package org.isotope.jfp.framework.utils;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * 缓存数据对象与字符串转换
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class RedisHelper implements ISFrameworkConstants {

	public static void copy(Jedis jd, String oldKey, String newKey, boolean retain) {
		copy(jd, oldKey, newKey, retain, Integer.MAX_VALUE);
	}

	public static void copy(Jedis jd, String oldKey, String newKey, boolean retain, int num) {
		String value;
		long size = jd.llen(oldKey);
		if (num < size)
			size = num;
		for (int i = 0; i < size; i++) {
			try {
				value = jd.lpop(oldKey);
				if (EmptyHelper.isEmpty(value))
					break;
				if (retain)
					jd.rpush(oldKey, value);
				jd.rpush(newKey, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Jedis oldJedis = new Jedis("10.10.168.50", 6379);
		oldJedis.auth("ImxV@ly1D4bBtGwv");
		Jedis newJedis = new Jedis("10.10.168.50", 6379);
		newJedis.auth("ImxV@ly1D4bBtGwv");
		//newJedis.select(1);
		copy(oldJedis,"QXB:COMP:KEY",newJedis,"QCC:COMP:KEY",false);
		
		//copy(oldJedis,"PAGE:GANJI:NUM",newJedis,"PAGE:GANJI:NUM",true);
		
	}
	
	public static void copy(Jedis oldJedis, String oldKey, Jedis newJedis, String newKey, boolean retain) {
		String value;
		long size = oldJedis.llen(oldKey);
		for (int i = 0; i < size; i++) {
			try {
				if(i>100000)
				 break;
				value = oldJedis.lpop(oldKey);
				System.out.println(value);
				if (retain)
					oldJedis.rpush(oldKey, value);
				newJedis.rpush(newKey, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	public static String getStringToRedis(Object value) {
		return getStringToRedis(value, true);
	}

	public static String getStringToRedis(Object value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (value instanceof String)
			return (String) value;
		if (translation)
			return value.getClass().getName() + BACKSLASH2 + JSON.toJSON(value);
		else
			return (String) value;
	}

	public static final String CLASS_NAME = "className";

	/**
	 * 实例化对象
	 * 
	 * @param value
	 * @return
	 */
	public static Object getClassFromRedis(String value) {
		return getClassFromRedis(value, true);
	}

	public static Object getClassFromRedis(String value, boolean translation) {
		if (EmptyHelper.isEmpty(value))
			return null;
		if (translation) {
			if (value.indexOf(BACKSLASH2) < 0)
				return (String) value;
			String[] values = new String[2];
			values[0] = value.substring(0, value.indexOf("//"));
			values[1] = value.substring(value.indexOf("//") + 2);
			try {
				return JSON.parseObject(values[1], Class.forName(values[0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return values[1];
		} else {
			return value;
		}
	}

}
