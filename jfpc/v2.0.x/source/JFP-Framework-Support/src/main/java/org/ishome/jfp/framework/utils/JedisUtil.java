package org.ishome.jfp.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

/** 
 * Redis工具
 * @author Spook
 * @since 1.2.0
 * @version 1.2.0 2014/12/17
 */
public class JedisUtil {
	private static Logger logger = Logger.getLogger(JedisUtil.class);
	
	private static JedisPool jedisPool;
	
	/**
	 * get jedis from pool
	 * @return
	 */
	public static Jedis getJedis(){
		return getJedis(0);
	}
	
	public static Jedis getJedis(String host, Integer port){
		Jedis jedis = new Jedis(host, port);
		jedis.connect();
		if(jedis.isConnected())
			return jedis;
		return null;
	}
	
	public static Jedis getJedis(String host, Integer port, String password){
		try {
			Jedis jedis = new Jedis(host, port);
			jedis.auth(password);
			jedis.connect();
			if(jedis.isConnected())
				return jedis;
		} catch (Exception e) {
			if(e instanceof JedisConnectionException){
				logger.error("get jedis JedisConnectionException : " + e.getMessage());
			}
		}
		return null;
	}
	
	public static Jedis getJedis(String host, Integer port, String password, int database){
		try {
			Jedis jedis = new Jedis(host, port);
			jedis.auth(password);
			jedis.select(database);
			jedis.connect();
			if(jedis.isConnected())
				return jedis;
		} catch (Exception e) {
			if(e instanceof JedisConnectionException){
				logger.error("get jedis JedisConnectionException : " + e.getMessage());
			}
		}
		return null;
	}
	
	public static Jedis getJedis(int failedNum){
		Jedis jedis = null;
		if(failedNum < 3){
			try {
				jedis = jedisPool.getResource();
			} catch (Exception e) {
				logger.error("get jedis error : " + e.getMessage());
				if(jedis != null)
					returnBrokenJedis(jedis);
				e.printStackTrace();
				try {
					switch (failedNum) {
					case 0:
						Thread.sleep(100);
						break;
					case 1:
						Thread.sleep(300);
						break;
					case 2:
						Thread.sleep(500);
						break;
					default:
						return null;
					}
					getJedis(++failedNum);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return jedis;
	}
	
	/**
	 * this method will be block until return Jedis client
	 * @return
	 */
	public static Jedis bgetJedis(){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("get jedis error : " + e.getMessage());
			returnBrokenJedis(jedis);
			e.printStackTrace();
			try {
				Thread.sleep(500);
				bgetJedis();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		return jedis;
	}
	
	/**
	 * return jedis to pool
	 * @param jedis
	 */
	public static void returnJedis(Jedis jedis){
		jedisPool.returnResource(jedis);
	}
	
	/**
	 * return broken jedis to pool
	 * @param jedis
	 */
	public static void returnBrokenJedis(Jedis jedis){
		if(jedis != null)
			jedisPool.returnBrokenResource(jedis);
	}
	
	public static String get(String key){
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:"+key+"]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
	public static String del(String key){
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.get(key);
			jedis.del(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:"+key+"]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
	/**
	 * this method will be block, until timeout 
	 * @param key
	 * @param timeout(millisecond)
	 * @return
	 */
	public static String get(String key, long timeout){
		Jedis jedis = getJedis();
		String value = null;
		long t1 = System.currentTimeMillis();
		try {
			while (true) {
				value = jedis.get(key);
				if (!StringUtils.isEmpty(value))
					break;
				
				if (System.currentTimeMillis() - t1 > timeout)
					break;
				Thread.sleep(100);
			}
		} catch (Exception e) {
			logger.error("get value from redis error[key:"+key+"]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
	
	
	public static void add(String key, String value){
		add(key, value, 15, 0);
	}
	
	public static List<String> hset(String key){
		Jedis jedis = JedisUtil.getJedis();
		try {
			return jedis.hvals(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisUtil.returnJedis(jedis);
		}
		return new ArrayList<String>();
	}
	
	public static String hdel(String rkey, String mkey){
		Jedis jedis = JedisUtil.getJedis();
		String value = null;
		try {
			value = jedis.hget(rkey, mkey);
			jedis.hdel(rkey, mkey);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisUtil.returnJedis(jedis);
		}
		return value;
	}
	public static String hget(String rkey, String mkey){
		Jedis jedis = getJedis();
		try {
			return jedis.hget(rkey, mkey);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}public static boolean hset(String rkey, String mkey, String value){
		Jedis jedis = JedisUtil.getJedis();

		try {
			jedis.hset(rkey, mkey, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisUtil.returnJedis(jedis);
		}
		return true;
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expireTime seconds
	 */
	public static void add(String key, String value, int expireTime){
		add(key, value, expireTime, 0);
	}
	
	public static void add(String key, String value, int expireTime, int failedNum){
		if(failedNum < 3){
			Jedis jedis = getJedis();
			try {
				jedis.set(key, value);
				if(expireTime > 0)
					jedis.expire(key, expireTime);
			} catch (Exception e) {
				logger.error("add key["+key+"] to redis error["+failedNum+"] ", e);
				returnBrokenJedis(jedis);
				add(key, value, expireTime, failedNum++);
			} finally {
				returnJedis(jedis);
			}
		}
	}
	
	/**
	 * publish message to special channel
	 * @param channel
	 * @param message
	 */
	public static void publish(String channel, String message){
		publish(channel, message, 0);
	}
	
	public static void publish(String channel, String message, int failedNum){
		if(failedNum < 3){
			Jedis jedis = getJedis();
			try {
				jedis.publish(channel, message);
			} catch (Exception e) {
				logger.error("publish message[" +message+"] to channel["+channel+"] error["+failedNum+"] : " + e.getMessage());
				e.printStackTrace();
				returnBrokenJedis(jedis);
				publish(channel, message, failedNum++);
			} finally {
				returnJedis(jedis);
			}
		}
	}
	
	/**
	 * publish message to special channel and queue
	 * @param channel
	 * @param message
	 */
	public static void queuePublish(String key, String channel, String message){
		queuePublish(key, channel, message, 0);
	}
	
	public static void queuePublish(String key, String channel, String message, int failedNum){
		if(failedNum < 3){
			Jedis jedis = getJedis();
			try {
				jedis.lpush(key, message);
				jedis.publish(channel, message);
			} catch (Exception e) {
				logger.error("queuePublish message[" +message+"] to channel["+channel+"] error["+failedNum+"] : " + e.getMessage());
				e.printStackTrace();
				returnBrokenJedis(jedis);
				queuePublish(key, channel, message, failedNum++);
			} finally {
				returnJedis(jedis);
			}
		}
	}
	
	/**
	 * subscribe special channel
	 * @param listener
	 * @param channel
	 */
	public static void subscribe(JedisPubSub listener, String channel){
		Jedis jedis = getJedis();
		try {
			jedis.subscribe(listener, channel);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		}
	}
	
	public static void listAdd(String key, String ... value){
		Jedis jedis = getJedis();
		try {
			jedis.rpush(key, value);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
	}
	
	public static String blistPop(String key, int expireTime){
		Jedis jedis = getJedis();
		try {
			List<String> list = jedis.blpop(expireTime,key);
			if(list!=null&&list.size()==1){
				return list.get(0);
			}
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}
	
	public static String listPop(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.lpop(key);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}
	
	public static List<String> listAll(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.lrange(key, 0, -1);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}
	
	public static long llen(String key){
		Jedis jedis = getJedis();
		long value = 0;
		try {
			value = jedis.llen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:"+key+"]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		
		return value;
	}
	public static long hlen(String key){
		Jedis jedis = getJedis();
		long value = 0;
		try {
			value = jedis.hlen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:"+key+"]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		
		return value;
	}
	
	public static List<String> listPopAll(String key){
		Jedis jedis = getJedis();
		try {
			long len = jedis.llen(key);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				list.add(jedis.lpop(key));
			}
			return list;
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}
	

	/**
	 * delete special value
	 * @param key
	 * @param count	delete numbers
	 * @param value
	 */
	public static long listDel(String key, int count, String value){
		Jedis jedis = getJedis();
		try {
			return jedis.lrem(key, count, value);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return 0;
	}
	
	public static void listDelAll(String key){
		Jedis jedis = getJedis();
		try {
			long len = jedis.llen(key);
			for (int i = 0; i < len; i++)
				jedis.rpop(key);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return	1:add success	0:value is existed 	other:key is not a set type
	 */
	public static long setAdd(String key, String ... value){
		Jedis jedis = getJedis();
		try {
			return jedis.sadd(key, value);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return 0;
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return	1:add success	0:value is existed 	other:key is not a set type
	 */
	public static long setDel(String key, String ... value){
		Jedis jedis = getJedis();
		try {
			return jedis.srem(key, value);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return 0;
	}
	
	public static void setDelAll(String key){
		Jedis jedis = getJedis();
		try {
			long total = jedis.scard(key);
			for (int i = 0; i < total; i++) {
				jedis.spop(key);
			}
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
	}
	
	public static long setCount(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.scard(key);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return 0;
	}
	
	public static Set<String> setAll(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.smembers(key);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}
	
	public static void expire(String key, int seconds){
		Jedis jedis = getJedis();
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
	}
	
	public void setJedisPool(JedisPool jedisPool) {
		JedisUtil.jedisPool = jedisPool;
	}
	
	public static void sleep(long ms){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			
		}
	}
	
}
