package org.isotope.jfp.framework.cache.redis.cluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISJedisSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis操作工具类
 * 
 * @author fucy
 * @version 3.1.1 2016/3/17
 * @version 2.4.1 2015/11/9
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * @see RedisChannelConfigBean
 */
public class JedisClusterUtil implements ISJedisSupport, ISFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public JedisClusterUtil() {

	}

	List<String> hostAndPorts;

	public List<String> getHostAndPorts() {
		return hostAndPorts;
	}

	public void setHostAndPorts(List<String> hostAndPorts) {
		this.hostAndPorts = hostAndPorts;
	}

	public void init() {
		String[] hps;
		for (String hp : hostAndPorts) {
			hps = hp.split(COLON);
			jedisClusterNodes.add(new HostAndPort(hps[0], Integer.parseInt(hps[1])));
		}

		// jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7000));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7001));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7000));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7001));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7000));
		// jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7001));
	}

	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	/**
	 * get JedisCluster from pool
	 * 
	 * @return
	 */
	public JedisCluster getJedisCluster() {
		return new JedisCluster(jedisClusterNodes);
	}

	/**
	 * @deprecated
	 */
	@Override
	public void selectDB(int index) {
		getJedisCluster().select(index);
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
	public void copy(String oldKey, String newKey, boolean retain) {
		copy(getJedisCluster(), oldKey, newKey, retain, Integer.MAX_VALUE);
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
	public void copy(JedisCluster jd, String oldKey, String newKey, boolean retain) {
		copy(jd, oldKey, newKey, retain, Integer.MAX_VALUE);
	}

	public void copy(String oldKey, String newKey, boolean retain, int num) {
		copy(getJedisCluster(), oldKey, newKey, retain, num);
	}

	public void copy(JedisCluster jd, String oldKey, String newKey, boolean retain, int num) {
		String value;
		long size = jd.llen(oldKey);
		if (num < size)
			size = num;
		try {
			for (int i = 0; i < size; i++) {
				value = jd.lpop(oldKey);
				if (EmptyHelper.isEmpty(value))
					break;
				if (retain)
					jd.rpush(oldKey, value);
				jd.rpush(newKey, value);

			}
		} catch (Exception e) {

		} finally {
			close(jd);
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
	public void copy(JedisCluster oldJedisCluster, String oldKey, JedisCluster newJedisCluster, String newKey, boolean retain) {
		String value;
		long size = oldJedisCluster.llen(oldKey);
		try {
			for (int i = 0; i < size; i++) {

				value = oldJedisCluster.lpop(oldKey);
				if (retain)
					oldJedisCluster.rpush(oldKey, value);
				newJedisCluster.rpush(newKey, value);

			}
		} catch (Exception e) {

		} finally {
			close(oldJedisCluster);
			close(newJedisCluster);
		}
	}

	public String get(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		String value = null;
		try {
			value = jedisCluster.get(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return value;
	}

	public String del(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		String value = null;
		try {
			value = jedisCluster.get(key);
			jedisCluster.del(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
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
	public String get(String key, long timeout) {
		JedisCluster jedisCluster = getJedisCluster();
		String value = null;
		long t1 = System.currentTimeMillis();
		try {
			while (true) {
				value = jedisCluster.get(key);
				if (EmptyHelper.isNotEmpty(value))
					break;

				if (System.currentTimeMillis() - t1 > timeout)
					break;
				Thread.sleep(100);
			}
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return value;
	}

	public void add(String key, String value) {
		add(key, value, 15, 0);
	}

	public Map<String, String> hgetall(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.hgetAll(key);
		} catch (Exception e) {
			logger.error("hgetall from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return new HashMap<String, String>();
	}

	public String hdel(String rkey, String mkey) {
		JedisCluster jedisCluster = getJedisCluster();
		String value = null;
		try {
			value = jedisCluster.hget(rkey, mkey);
			jedisCluster.hdel(rkey, mkey);
			return value;
		} catch (Exception e) {
			logger.error("hdel from redis error[rkey:" + rkey + "],[mkey:" + mkey + "]", e);
		} finally {
			close(jedisCluster);
		}
		return value;
	}

	public String hget(String rkey, String mkey) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.hget(rkey, mkey);
		} catch (Exception e) {
			logger.error("hget from redis error[rkey:" + rkey + "],[mkey:" + mkey + "]", e);
		} finally {
			close(jedisCluster);
		}
		return null;
	}

	public boolean hset(String rkey, String mkey, String value) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			jedisCluster.hset(rkey, mkey, value);
		} catch (Exception e) {
			logger.error("hset from redis error[rkey:" + rkey + "],[mkey:" + mkey + "]", e);
		} finally {
			close(jedisCluster);
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
	public void add(String key, String value, int expireTime) {
		add(key, value, expireTime, 0);
	}

	public void add(String key, String value, int expireTime, int failedNum) {
		if (failedNum < 3) {
			JedisCluster jedisCluster = getJedisCluster();
			try {
				jedisCluster.set(key, value);
				if (expireTime > 0)
					jedisCluster.expire(key, expireTime);
			} catch (Exception e) {
				logger.error("add key[" + key + "] to redis error[" + failedNum + "] ", e);
				add(key, value, expireTime, failedNum++);
			} finally {
				close(jedisCluster);
			}
		}
	}

	public void listAdd(String key, String... value) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			jedisCluster.rpush(key, value);
		} catch (Exception e) {
			logger.error("listAdd from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
	}

	public String blistPop(String key, int expireTime) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			List<String> list = jedisCluster.blpop(expireTime, key);
			if (list != null && list.size() == 2) {
				return list.get(1);
			}
		} catch (Exception e) {
			logger.error("blistPop from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return null;
	}

	public String listPop(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.lpop(key);
		} catch (Exception e) {
			logger.error("listPop from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return null;
	}

	public List<String> listAll(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.lrange(key, 0, -1);
		} catch (Exception e) {
			logger.error("listAll from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return null;
	}

	public long llen(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		long value = 0;
		try {
			value = jedisCluster.llen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}

		return value;
	}

	public long hlen(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		long value = 0;
		try {
			value = jedisCluster.hlen(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}

		return value;
	}

	public List<String> listPopAll(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			long len = jedisCluster.llen(key);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				list.add(jedisCluster.lpop(key));
			}
			return list;
		} catch (Exception e) {
			logger.error("listPopAll from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
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
	public long listDel(String key, int count, String value) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.lrem(key, count, value);
		} catch (Exception e) {
			logger.error("listDel from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return 0;
	}

	public void listDelAll(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			long len = jedisCluster.llen(key);
			for (int i = 0; i < len; i++)
				jedisCluster.rpop(key);
		} catch (Exception e) {
			logger.error("listDelAll from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return 1:add success 0:value is existed other:key is not a set type
	 */
	public long setAdd(String key, String... value) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.sadd(key, value);
		} catch (Exception e) {
			logger.error("setAdd from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return 0;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return 1:add success 0:value is existed other:key is not a set type
	 */
	public long setDel(String key, String... value) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.srem(key, value);
		} catch (Exception e) {
			logger.error("setDel from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return 0;
	}

	public void setDelAll(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			long total = jedisCluster.scard(key);
			for (int i = 0; i < total; i++) {
				jedisCluster.spop(key);
			}
		} catch (Exception e) {
			logger.error("setDelAll from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
	}

	public long setCount(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.scard(key);
		} catch (Exception e) {
			logger.error("setCount from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return 0;
	}

	public Set<String> setAll(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			return jedisCluster.smembers(key);
		} catch (Exception e) {
			logger.error("setAll from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return null;
	}

	public void expire(String key, int seconds) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			jedisCluster.expire(key, seconds);
		} catch (Exception e) {
			logger.error("expire from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
	}

	public void close(JedisCluster jedisCluster) {
		if (jedisCluster != null)
			try {
				jedisCluster.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void sleep(long ms) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
	}

	@Override
	public Object indexObjectInList(String key, int index) {
		JedisCluster jedisCluster = getJedisCluster();
		String value = null;
		try {
			value = jedisCluster.lindex(key, index);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return value;
	}

	@Override
	public long setnx(String key, String stringToRedis, int waitTime) {
		JedisCluster jedisCluster = getJedisCluster();
		long rs = 0l;
		try {
			rs = jedisCluster.setnx(key, stringToRedis);
			jedisCluster.expire(key, waitTime);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return rs;
	}

	@Override
	public boolean removeKey(String key) {
		JedisCluster jedisCluster = getJedisCluster();
		try {
			jedisCluster.del(key);
		} catch (Exception e) {
			logger.error("get value from redis error[key:" + key + "]", e);
		} finally {
			close(jedisCluster);
		}
		return true;
	}

}
