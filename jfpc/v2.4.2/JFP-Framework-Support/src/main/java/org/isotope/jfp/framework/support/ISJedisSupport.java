package org.isotope.jfp.framework.support;

import java.util.List;
import java.util.Set;

/**
 * Redis缓存实现<br>
 * 面向缓存服务使用
 * 
 * @version 2.4.2 2015/12/10
 * @since 2.4.2 2015/12/10
 *
 */
public interface ISJedisSupport {

    String get(String key);

    String del(String key);

    /**
     * this method will be block, until timeout
     * 
     * @param key
     * @param timeout(millisecond)
     * @return
     */
    String get(String key, long timeout);

    void add(String key, String value);

    List<String> hset(String key);

    String hdel(String rkey, String mkey);

    String hget(String rkey, String mkey);

    boolean hset(String rkey, String mkey, String value);

    /**
     * 
     * @param key
     * @param value
     * @param expireTime
     *            seconds
     */
    void add(String key, String value, int expireTime);

    void add(String key, String value, int expireTime, int failedNum);

    void listAdd(String key, String... value);

    String blistPop(String key, int expireTime);

    String listPop(String key);

    List<String> listAll(String key);

    long llen(String key);

    long hlen(String key);

    List<String> listPopAll(String key);

    /**
     * delete special value
     * 
     * @param key
     * @param count
     *            delete numbers
     * @param value
     */
    long listDel(String key, int count, String value);

    void listDelAll(String key);

    /**
     * 
     * @param key
     * @param value
     * @return 1:add success 0:value is existed other:key is not a set type
     */
    long setAdd(String key, String... value);

    /**
     * 
     * @param key
     * @param value
     * @return 1:add success 0:value is existed other:key is not a set type
     */
    long setDel(String key, String... value);

    void setDelAll(String key);

    long setCount(String key);

    Set<String> setAll(String key);

    void expire(String key, int seconds);
}