package org.isotope.jfp.framework.cache.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

public class RedisPoolUtil {
	public static final String BEAN_NAME = "redisPoolUtil";
	
	private Pool<Jedis> jedisPool;

	public void setJedisPool(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 获得一个连接
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		return getJedis(3);
	}

	public Jedis getJedis(int failedNum) {
		Jedis jedis = null;
		if (failedNum < 3) {
			try {
				jedis = jedisPool.getResource();
			} catch (Exception e) {
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
	public Jedis bgetJedis() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
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
	public void returnJedis(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * return broken jedis to pool
	 * 
	 * @param jedis
	 */
	public void returnBrokenJedis(Jedis jedis) {
		if (jedis != null)
			jedisPool.returnBrokenResource(jedis);
	}
}
