package org.isotope.jfp.framework.cache.redis.master;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;
/**
 * Redis操作工具类
 * 
 * @author fucy
 * @version 2.4.1 2015/11/9
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * @see RedisChannelConfigBean
 */
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
		return getJedis(0);
	}

	public Jedis getJedis(int failedNum) {
		Jedis jedis = null;
		if (failedNum < 3) {
			try {
				jedis = jedisPool.getResource();
			} catch (Exception e) {
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
				} catch (Exception e1) {
					throw new RuntimeException("Redis connect fail");
				}
			}
		}else {
			throw new RuntimeException("Redis connect fail");
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
			try {
				Thread.sleep(500);
				bgetJedis();
			} catch (Exception e1) {
				throw new RuntimeException("Redis connect fail");
			}
		}
		return jedis;
	}
}
