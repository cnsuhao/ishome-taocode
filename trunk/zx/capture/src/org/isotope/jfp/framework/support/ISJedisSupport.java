package org.isotope.jfp.framework.support;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis缓存实现<br>
 * 面向缓存服务使用
 * 
 * @version 3.1.2 2016/4/21
 * @version 3.1.1 2016/3/17
 * @version 2.4.2 2015/12/10
 * @since 2.4.2 2015/12/10
 *
 */
public interface ISJedisSupport {
	/**
	 * 初始化
	 */
	void init();

	void selectDB(int index);boolean removeKey(String key);

	String get(String key);

	String del(String key);

	String get(String key, long timeout);

	void add(String key, String value);

	Map<String, String> hgetall(String key);

	String hdel(String rkey, String mkey);

	String hget(String rkey, String mkey);

	boolean hset(String rkey, String mkey, String value);

	void add(String key, String value, int expireTime);

	void add(String key, String value, int expireTime, int failedNum);

	void listAdd(String key, String... value);

	String blistPop(String key, int expireTime);

	String listPop(String key);

	List<String> listAll(String key);

	long llen(String key);

	long hlen(String key);

	List<String> listPopAll(String key);

	long listDel(String key, int count, String value);

	void listDelAll(String key);

	long setAdd(String key, String... value);

	long setDel(String key, String... value);

	void setDelAll(String key);

	long setCount(String key);

	Set<String> setAll(String key);

	void expire(String key, int seconds);

	Object indexObjectInList(String key, int index);

	long setnx(String key, String stringToRedis, int waitTime);
}