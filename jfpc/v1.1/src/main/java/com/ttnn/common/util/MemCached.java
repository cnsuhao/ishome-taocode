package com.ttnn.common.util;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 使用memcached的缓存实用类.
 * 
 * @see http://code.google.com/p/xmemcached/wiki/User_Guide_zh
 * @author
 * 
 */
@Service
public class MemCached {
	// protected MemcachedClientBuilder builder = new XMemcachedClientBuilder(
	// AddrUtil.getAddresses("localhost:11211"));
	//
	// protected static MemCached memCached = new MemCached();

	// /**
	// * 保护型构造方法，不允许实例化！
	// *
	// */
	// protected MemCached()
	// {
	// }
	//
	// /**
	// * 获取唯一实例.
	// * @return
	// */
	// public static MemCached getInstance()
	// {
	// return memCached;
	// }
	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(MemCached.class);
	}

	HashMap cached = new HashMap();

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		cached.put(key, value);
		return true;
	}

	/**
	 * 替换一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value) {
		getLogger().debug(key + ">>>>>>cached>>>>>>>>>");
		cached.put(key, value);

		return true;
	}

	/**
	 * 删除一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean delete(String key) {
		cached.remove(key);

		return true;
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		getLogger().debug(key + ">>>>>>cached>>>>>>>>>" + cached);
		return cached.get(key);
	}

	// /**
	// * 添加一个指定的值到缓存中.
	// * @param key
	// * @param value
	// * @return
	// */
	// public boolean add(String key, Object value)
	// {
	// try {
	// MemcachedClient mcc = builder.build();
	// return mcc.add(key, 36000, value);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return false;
	// }
	//
	// /**
	// * 替换一个指定的值到缓存中.
	// * @param key
	// * @param value
	// * @return
	// */
	// public boolean replace(String key, Object value)
	// {
	// try {
	// MemcachedClient mcc = builder.build();
	// return mcc.replace(key, 36000, value);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return false;
	// }
	//
	// /**
	// * 删除一个指定的值到缓存中.
	// * @param key
	// * @param value
	// * @return
	// */
	// public boolean delete(String key)
	// {
	// try {
	// MemcachedClient mcc = builder.build();
	// return mcc.delete(key);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return false;
	// }
	//
	//
	// /**
	// * 根据指定的关键字获取对象.
	// * @param key
	// * @return
	// */
	// public Object get(String key)
	// {
	// try {
	// MemcachedClient mcc = builder.build();
	// return mcc.get(key);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return false;
	// }

}
