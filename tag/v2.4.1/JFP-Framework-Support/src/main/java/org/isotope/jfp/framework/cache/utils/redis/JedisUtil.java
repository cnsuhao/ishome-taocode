package org.isotope.jfp.framework.cache.utils.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.isotope.jfp.framework.beands.common.RedisChannelConfigBean;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.util.Pool;

/**
 * 推送通道队列设置
 * 
 * @author fucy
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * @see RedisChannelConfigBean
 */
public class JedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);

	private static Pool<Jedis> jedisPool;

	/**
	 * get jedis from pool
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		return getJedis(3);
	}

	public static Jedis getJedis(int failedNum) {
		Jedis jedis = null;
		if (failedNum < 3) {
			try {
				jedis = jedisPool.getResource();
			} catch (Exception e) {
				logger.error("get jedis error : " + e.getMessage());
				if (jedis != null)
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
	 * 
	 * @return
	 */
	public static Jedis bgetJedis() {
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
	 * 
	 * @param jedis
	 */
	public static void returnJedis(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * return broken jedis to pool
	 * 
	 * @param jedis
	 */
	public static void returnBrokenJedis(Jedis jedis) {
		if (jedis != null)
			jedisPool.returnBrokenResource(jedis);
	}

	/**
	 * 缓存数据拷贝
	 * 
	 * @param oldKey
	 *            原始键值名称
	 * @param newKey
	 *            新的键值名称
	 * @param retain
	 *            是否保留(true保留数据，顺序在末尾)
	 */
	public static void copy(String oldKey, String newKey, boolean retain) {
		copy(getJedis(),oldKey, newKey, retain, Integer.MAX_VALUE);
	}

	/**
	 * 缓存数据拷贝
	 * 
	 * @param jd
	 *            缓存服务连接
	 * @param oldKey
	 *            原始键值名称
	 * @param newKey
	 *            新的键值名称
	 * @param retain
	 *            是否保留(true保留数据，顺序在末尾)
	 */
	public static void copy(Jedis jd, String oldKey, String newKey, boolean retain) {
		copy(jd,oldKey,newKey,retain,Integer.MAX_VALUE);
	}
	
	public static void copy(String oldKey, String newKey, boolean retain, int num) {
		copy(getJedis(), oldKey, newKey, retain, num);
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

	/**
	 * 缓存数据拷贝
	 * 
	 * @param jd
	 *            缓存服务连接
	 * @param oldKey
	 *            原始键值名称
	 * @param newKey
	 *            新的键值名称
	 * @param retain
	 *            是否保留(true保留数据，顺序在末尾)
	 */
	public static void copy(Jedis oldJedis, String oldKey, Jedis newJedis, String newKey, boolean retain) {
		String value;
		long size = oldJedis.llen(oldKey);
		for (int i = 0; i < size; i++) {
			try {
				value = oldJedis.lpop(oldKey);
				if (retain)
					oldJedis.rpush(oldKey, value);
				newJedis.rpush(newKey, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String get(String key) {
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}

	public static String del(String key) {
		Jedis jedis = getJedis();
		String value = null;
		try {
			value = jedis.get(key);
			jedis.del(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}

	/**
	 * this method will be block, until timeout
	 * 
	 * @param key
	 * @param timeout(millisecond)
	 * @return
	 */
	public static String get(String key, long timeout) {
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
			logger.error("get value from redis error[key:" + key + "]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}

	public static void add(String key, String value) {
		add(key, value, 15, 0);
	}

	public static List<String> hset(String key) {
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

	public static String hdel(String rkey, String mkey) {
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

	public static String hget(String rkey, String mkey) {
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
	}

	public static boolean hset(String rkey, String mkey, String value) {
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
	 * @param expireTime
	 *            seconds
	 */
	public static void add(String key, String value, int expireTime) {
		add(key, value, expireTime, 0);
	}

	public static void add(String key, String value, int expireTime, int failedNum) {
		if (failedNum < 3) {
			Jedis jedis = getJedis();
			try {
				jedis.set(key, value);
				if (expireTime > 0)
					jedis.expire(key, expireTime);
			} catch (Exception e) {
				logger.error("add key[" + key + "] to redis error[" + failedNum + "] ", e);
				returnBrokenJedis(jedis);
				add(key, value, expireTime, failedNum++);
			} finally {
				returnJedis(jedis);
			}
		}
	}

	/**
	 * publish message to special channel
	 * 
	 * @param channel
	 * @param message
	 */
	public static void publish(String channel, String message) {
		publish(channel, message, 0);
	}

	public static void publish(String channel, String message, int failedNum) {
		if (failedNum < 3) {
			Jedis jedis = getJedis();
			try {
				jedis.publish(channel, message);
			} catch (Exception e) {
				logger.error("publish message[" + message + "] to channel[" + channel + "] error[" + failedNum + "] : " + e.getMessage());
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
	 * 
	 * @param channel
	 * @param message
	 */
	public static void queuePublish(String key, String channel, String message) {
		queuePublish(key, channel, message, 0);
	}

	public static void queuePublish(String key, String channel, String message, int failedNum) {
		if (failedNum < 3) {
			Jedis jedis = getJedis();
			try {
				jedis.lpush(key, message);
				jedis.publish(channel, message);
			} catch (Exception e) {
				logger.error("queuePublish message[" + message + "] to channel[" + channel + "] error[" + failedNum + "] : " + e.getMessage());
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
	 * 
	 * @param listener
	 * @param channel
	 */
	public static void subscribe(JedisPubSub listener, String channel) {
		Jedis jedis = getJedis();
		try {
			jedis.subscribe(listener, channel);
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		}
	}

	public static void listAdd(String key, String... value) {
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

	public static String blistPop(String key, int expireTime) {
		Jedis jedis = getJedis();
		try {
			List<String> list = jedis.blpop(expireTime, key);
			if (list != null && list.size() == 2) {
				return list.get(1);
			}
		} catch (Exception e) {
			returnBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			returnJedis(jedis);
		}
		return null;
	}

	public static String listPop(String key) {
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

	public static List<String> listAll(String key) {
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

	public static long llen(String key) {
		Jedis jedis = getJedis();
		long value = 0;
		try {
			value = jedis.llen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}

		return value;
	}

	public static long hlen(String key) {
		Jedis jedis = getJedis();
		long value = 0;
		try {
			value = jedis.hlen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
			returnBrokenJedis(jedis);
		} finally {
			returnJedis(jedis);
		}

		return value;
	}

	public static List<String> listPopAll(String key) {
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
	 * 
	 * @param key
	 * @param count
	 *            delete numbers
	 * @param value
	 */
	public static long listDel(String key, int count, String value) {
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

	public static void listDelAll(String key) {
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
	 * @return 1:add success 0:value is existed other:key is not a set type
	 */
	public static long setAdd(String key, String... value) {
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
	 * @return 1:add success 0:value is existed other:key is not a set type
	 */
	public static long setDel(String key, String... value) {
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

	public static void setDelAll(String key) {
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

	public static long setCount(String key) {
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

	public static Set<String> setAll(String key) {
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

	public static void expire(String key, int seconds) {
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

	public void setJedisPool(Pool<Jedis> jedisPool) {
		JedisUtil.jedisPool = jedisPool;
	}

	public static void sleep(long ms) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
	}

}
