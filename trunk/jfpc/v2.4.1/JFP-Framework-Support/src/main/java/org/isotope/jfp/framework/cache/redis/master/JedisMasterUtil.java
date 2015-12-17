package org.isotope.jfp.framework.cache.redis.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.isotope.jfp.framework.support.ISJedisSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Redis操作工具类
 * 
 * @author fucy
 * @version 2.4.1 2015/11/9
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * @see RedisChannelConfigBean
 */
public class JedisMasterUtil implements ISJedisSupport {
    private Logger logger = LoggerFactory.getLogger(JedisMasterUtil.class);

    public JedisMasterUtil() {

    }

    public JedisMasterUtil(RedisPoolUtil jedisPool) {
        this.redisPool = jedisPool;
    }

    /**
     * Redis服务器定义
     */
    private RedisPoolUtil redisPool;

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#getJedisPool()
     */

    public RedisPoolUtil getRedisPool() {
        return redisPool;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#setJedisPool(org.
     * isotope.jfp.framework.cache.utils.redis.RedisPoolUtil)
     */

    public void setRedisPool(RedisPoolUtil jedisPool) {
        this.redisPool = jedisPool;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#getJedis()
     */

    public Jedis getJedis() {
        return redisPool.getJedis(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#clear()
     */

    public boolean clear() {
        getJedis().flushAll();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#copy(java.lang.String,
     * java.lang.String, boolean)
     */

    public void copy(String oldKey, String newKey, boolean retain) {
        copy(getJedis(), oldKey, newKey, retain, Integer.MAX_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#copy(redis.clients.
     * jedis.Jedis, java.lang.String, java.lang.String, boolean)
     */

    public void copy(Jedis jd, String oldKey, String newKey, boolean retain) {
        copy(jd, oldKey, newKey, retain, Integer.MAX_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#copy(java.lang.String,
     * java.lang.String, boolean, int)
     */

    public void copy(String oldKey, String newKey, boolean retain, int num) {
        copy(getJedis(), oldKey, newKey, retain, num);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#copy(redis.clients.
     * jedis.Jedis, java.lang.String, java.lang.String, boolean, int)
     */

    public void copy(Jedis jd, String oldKey, String newKey, boolean retain, int num) {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#copy(redis.clients.
     * jedis.Jedis, java.lang.String, redis.clients.jedis.Jedis,
     * java.lang.String, boolean)
     */

    public void copy(Jedis oldJedis, String oldKey, Jedis newJedis, String newKey, boolean retain) {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#get(java.lang.String)
     */

    public String get(String key) {
        Jedis jedis = getJedis();
        String value = null;
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        } finally {
            close(jedis);
        }
        return value;
    }

    public void close(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#del(java.lang.String)
     */

    public String del(String key) {
        Jedis jedis = getJedis();
        String value = null;
        try {
            value = jedis.get(key);
            jedis.del(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#get(java.lang.String,
     * long)
     */

    public String get(String key, long timeout) {
        Jedis jedis = getJedis();
        String value = null;
        long t1 = System.currentTimeMillis();
        try {
            while (true) {
                value = jedis.get(key);
                if (EmptyHelper.isNotEmpty(value))
                    break;

                if (System.currentTimeMillis() - t1 > timeout)
                    break;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#add(java.lang.String,
     * java.lang.String)
     */

    public void add(String key, String value) {
        add(key, value, 15, 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#hset(java.lang.String)
     */

    public List<String> hset(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return new ArrayList<String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#hdel(java.lang.String,
     * java.lang.String)
     */

    public String hdel(String rkey, String mkey) {
        Jedis jedis = getJedis();
        String value = null;
        try {
            value = jedis.hget(rkey, mkey);
            jedis.hdel(rkey, mkey);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#hget(java.lang.String,
     * java.lang.String)
     */

    public String hget(String rkey, String mkey) {
        Jedis jedis = getJedis();
        try {
            return jedis.hget(rkey, mkey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#hset(java.lang.String,
     * java.lang.String, java.lang.String)
     */

    public boolean hset(String rkey, String mkey, String value) {
        Jedis jedis = getJedis();

        try {
            jedis.hset(rkey, mkey, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#add(java.lang.String,
     * java.lang.String, int)
     */

    public void add(String key, String value, int expireTime) {
        add(key, value, expireTime, 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#add(java.lang.String,
     * java.lang.String, int, int)
     */

    public void add(String key, String value, int expireTime, int failedNum) {
        if (failedNum < 3) {
            Jedis jedis = getJedis();
            try {
                jedis.set(key, value);
                if (expireTime > 0)
                    jedis.expire(key, expireTime);
            } catch (Exception e) {
                logger.error("add key[" + key + "] to redis error[" + failedNum + "] ", e);
                add(key, value, expireTime, ++failedNum);
            } finally {
                close(jedis);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#publish(java.lang.
     * String, java.lang.String)
     */

    public void publish(String channel, String message) {
        publish(channel, message, 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#publish(java.lang.
     * String, java.lang.String, int)
     */

    public void publish(String channel, String message, int failedNum) {
        if (failedNum < 3) {
            Jedis jedis = getJedis();
            try {
                jedis.publish(channel, message);
            } catch (Exception e) {
                logger.error("publish message[" + message + "] to channel[" + channel + "] error[" + failedNum + "] : " + e.getMessage());
                e.printStackTrace();
                publish(channel, message, failedNum++);
            } finally {
                close(jedis);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#queuePublish(java.lang.
     * String, java.lang.String, java.lang.String)
     */

    public void queuePublish(String key, String channel, String message) {
        queuePublish(key, channel, message, 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#queuePublish(java.lang.
     * String, java.lang.String, java.lang.String, int)
     */

    public void queuePublish(String key, String channel, String message, int failedNum) {
        if (failedNum < 3) {
            Jedis jedis = getJedis();
            try {
                jedis.lpush(key, message);
                jedis.publish(channel, message);
            } catch (Exception e) {
                logger.error("queuePublish message[" + message + "] to channel[" + channel + "] error[" + failedNum + "] : " + e.getMessage());
                e.printStackTrace();
                queuePublish(key, channel, message, failedNum++);
            } finally {
                close(jedis);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#subscribe(redis.clients
     * .jedis.JedisPubSub, java.lang.String)
     */

    public void subscribe(JedisPubSub listener, String channel) {
        Jedis jedis = getJedis();
        try {
            jedis.subscribe(listener, channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#listAdd(java.lang.
     * String, java.lang.String)
     */

    public void listAdd(String key, String... value) {
        Jedis jedis = getJedis();
        try {
            jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#blistPop(java.lang.
     * String, int)
     */

    public String blistPop(String key, int expireTime) {
        Jedis jedis = getJedis();
        try {
            List<String> list = jedis.blpop(expireTime, key);
            if (list != null && list.size() == 2) {
                return list.get(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#listPop(java.lang.
     * String)
     */

    public String listPop(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#listAll(java.lang.
     * String)
     */

    public List<String> listAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#llen(java.lang.String)
     */

    public long llen(String key) {
        Jedis jedis = getJedis();
        long value = 0;
        try {
            value = jedis.llen(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        } finally {
            close(jedis);
        }

        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#hlen(java.lang.String)
     */

    public long hlen(String key) {
        Jedis jedis = getJedis();
        long value = 0;
        try {
            value = jedis.hlen(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        } finally {
            close(jedis);
        }

        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#listPopAll(java.lang.
     * String)
     */

    public List<String> listPopAll(String key) {
        Jedis jedis = getJedis();
        try {
            long len = jedis.llen(key);
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < len; i++) {
                list.add(jedis.lpop(key));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#listDel(java.lang.
     * String, int, java.lang.String)
     */

    public long listDel(String key, int count, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.lrem(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#listDelAll(java.lang.
     * String)
     */

    public void listDelAll(String key) {
        Jedis jedis = getJedis();
        try {
            long len = jedis.llen(key);
            for (int i = 0; i < len; i++)
                jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#setAdd(java.lang.
     * String, java.lang.String)
     */

    public long setAdd(String key, String... value) {
        Jedis jedis = getJedis();
        try {
            return jedis.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#setDel(java.lang.
     * String, java.lang.String)
     */

    public long setDel(String key, String... value) {
        Jedis jedis = getJedis();
        try {
            return jedis.srem(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#setDelAll(java.lang.
     * String)
     */

    public void setDelAll(String key) {
        Jedis jedis = getJedis();
        try {
            long total = jedis.scard(key);
            for (int i = 0; i < total; i++) {
                jedis.spop(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.isotope.jfp.framework.cache.utils.redis.Jedis#setCount(java.lang.
     * String)
     */

    public long setCount(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#setAll(java.lang.
     * String)
     */

    public Set<String> setAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#expire(java.lang.
     * String, int)
     */

    public void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.isotope.jfp.framework.cache.utils.redis.Jedis#sleep(long)
     */

    public void sleep(long ms) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
    }

}
