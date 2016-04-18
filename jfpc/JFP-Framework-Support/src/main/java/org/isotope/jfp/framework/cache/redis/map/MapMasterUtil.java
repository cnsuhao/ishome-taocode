package org.isotope.jfp.framework.cache.redis.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.isotope.jfp.framework.support.ISJedisSupport;

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
public class MapMasterUtil implements ISJedisSupport {
	HashMap<String, String> cache = new HashMap<String, String>();

	public boolean clear() {
		cache.clear();
		return true;
	}

	/**
	 * @deprecated
	 */
	@Override
	public void selectDB(int index) {

	}

	/**
	 * @deprecated
	 */
	@Override
	public void expire(String key, int timeOut) {

	}

	/**
	 * @deprecated
	 */
	@Override
	public void init() {

	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}

	@Override
	public String del(String key) {
		return cache.remove(key);
	}

	@Override
	public String get(String key, long timeout) {
		return cache.get(key);
	}

	@Override
	public void add(String key, String value) {
		cache.put(key, value);
	}

	@Override
	public Map<String, String> hgetall(String key) {
		return null;
	}

	@Override
	public String hdel(String rkey, String mkey) {
		return null;
	}

	@Override
	public String hget(String rkey, String mkey) {
		return null;
	}

	@Override
	public boolean hset(String rkey, String mkey, String value) {
		return false;
	}

	@Override
	public void add(String key, String value, int expireTime) {
		cache.put(key, value);
	}

	@Override
	public void add(String key, String value, int expireTime, int failedNum) {
		cache.put(key, value);
	}

	/**
	 * @deprecated
	 */
	@Override
	public void listAdd(String key, String... value) {

	}

	/**
	 * @deprecated
	 */
	@Override
	public String blistPop(String key, int expireTime) {
		return null;
	}

	@Override
	public String listPop(String key) {
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public List<String> listAll(String key) {
		return null;
	}

	/**
	 * @deprecated
	 */
	@Override
	public long llen(String key) {
		return 0;
	}

	@Override
	public long hlen(String key) {
		return 0;
	}

	/**
	 * @deprecated
	 */
	@Override
	public List<String> listPopAll(String key) {
		return null;
	}

	@Override
	public long listDel(String key, int count, String value) {
		return 0;
	}

	/**
	 * @deprecated
	 */
	@Override
	public void listDelAll(String key) {
	}

	@Override
	public long setAdd(String key, String... value) {
		return 0;
	}

	/**
	 * @deprecated
	 */
	@Override
	public long setDel(String key, String... value) {
		return 0;
	}

	/**
	 * @deprecated
	 */
	@Override
	public void setDelAll(String key) {
	}

	/**
	 * @deprecated
	 */
	@Override
	public long setCount(String key) {
		return 0;
	}

	/**
	 * @deprecated
	 */
	@Override
	public Set<String> setAll(String key) {
		return null;
	}
}
