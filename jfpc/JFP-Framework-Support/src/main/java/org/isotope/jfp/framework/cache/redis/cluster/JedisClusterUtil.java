package org.isotope.jfp.framework.cache.redis.cluster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
 * @version 2.4.1 2015/11/9
 * @version 2.3.0 2015/6/11
 * @since 2.3.0
 * @see RedisChannelConfigBean
 */
public class JedisClusterUtil implements ISJedisSupport,ISFrameworkConstants {
    private Logger logger = LoggerFactory.getLogger(JedisClusterUtil.class);

    public JedisClusterUtil() {

    }

    JedisCluster jedisCluster;

    List<String> hostAndPorts;

    public List<String> getHostAndPorts() {
        return hostAndPorts;
    }

    public void setHostAndPorts(List<String> hostAndPorts) {
        this.hostAndPorts = hostAndPorts;
    }

    public void init() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String[] hps;
        for (String hp : hostAndPorts) {
            hps = hp.split(COLON);
            jedisClusterNodes.add(new HostAndPort(hps[0], Integer.parseInt(hps[1])));
        }

//        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7000));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7001));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7000));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.202", 7001));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.201", 7002));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7000));
//        jedisClusterNodes.add(new HostAndPort("172.16.2.203", 7001));

        jedisCluster = new JedisCluster(jedisClusterNodes);
    }

    /**
     * get JedisCluster from pool
     * 
     * @return
     */
    public JedisCluster getJedisCluster() {

        return jedisCluster;
    }
    
    @Override
	public void selectDB(int index) {
	
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
    public void copy(JedisCluster oldJedisCluster, String oldKey, JedisCluster newJedisCluster, String newKey, boolean retain) {
        String value;
        long size = oldJedisCluster.llen(oldKey);
        for (int i = 0; i < size; i++) {
            try {
                value = oldJedisCluster.lpop(oldKey);
                if (retain)
                    oldJedisCluster.rpush(oldKey, value);
                newJedisCluster.rpush(newKey, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String get(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        String value = null;
        try {
            value = JedisCluster.get(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        }
        return value;
    }

    public String del(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        String value = null;
        try {
            value = JedisCluster.get(key);
            JedisCluster.del(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
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
        JedisCluster JedisCluster = getJedisCluster();
        String value = null;
        long t1 = System.currentTimeMillis();
        try {
            while (true) {
                value = JedisCluster.get(key);
                if (EmptyHelper.isNotEmpty(value))
                    break;

                if (System.currentTimeMillis() - t1 > timeout)
                    break;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        }
        return value;
    }

    public void add(String key, String value) {
        add(key, value, 15, 0);
    }

    public List<String> hset(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public String hdel(String rkey, String mkey) {
        JedisCluster JedisCluster = getJedisCluster();
        String value = null;
        try {
            value = JedisCluster.hget(rkey, mkey);
            JedisCluster.hdel(rkey, mkey);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public String hget(String rkey, String mkey) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.hget(rkey, mkey);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public boolean hset(String rkey, String mkey, String value) {
        JedisCluster JedisCluster = getJedisCluster();

        try {
            JedisCluster.hset(rkey, mkey, value);
        } catch (Exception e) {
            e.printStackTrace();
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
            JedisCluster JedisCluster = getJedisCluster();
            try {
                JedisCluster.set(key, value);
                if (expireTime > 0)
                    JedisCluster.expire(key, expireTime);
            } catch (Exception e) {
                logger.error("add key[" + key + "] to redis error[" + failedNum + "] ", e);
                add(key, value, expireTime, failedNum++);
            }
        }
    }

    public void listAdd(String key, String... value) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            JedisCluster.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String blistPop(String key, int expireTime) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            List<String> list = JedisCluster.blpop(expireTime, key);
            if (list != null && list.size() == 2) {
                return list.get(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String listPop(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> listAll(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.lrange(key, 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long llen(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        long value = 0;
        try {
            value = JedisCluster.llen(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        }

        return value;
    }

    public long hlen(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        long value = 0;
        try {
            value = JedisCluster.hlen(key);
        } catch (Exception e) {
            logger.error("get value from redis error[key:" + key + "]", e);
        }

        return value;
    }

    public List<String> listPopAll(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            long len = JedisCluster.llen(key);
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < len; i++) {
                list.add(JedisCluster.lpop(key));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
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
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.lrem(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void listDelAll(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            long len = JedisCluster.llen(key);
            for (int i = 0; i < len; i++)
                JedisCluster.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param key
     * @param value
     * @return 1:add success 0:value is existed other:key is not a set type
     */
    public long setAdd(String key, String... value) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
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
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.srem(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setDelAll(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            long total = JedisCluster.scard(key);
            for (int i = 0; i < total; i++) {
                JedisCluster.spop(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long setCount(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Set<String> setAll(String key) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            return JedisCluster.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void expire(String key, int seconds) {
        JedisCluster JedisCluster = getJedisCluster();
        try {
            JedisCluster.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
    }

}
