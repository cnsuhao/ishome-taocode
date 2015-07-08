package org.jfpc.framework.cache;

import java.util.HashMap;

import org.jfpc.constants.ISFrameworkConstants;


/**
 * 数据缓存
 * 
 * @author Spook
 * @since 0.1.0 
 * @version 0.1.0 2014/2/8
 */
public class CatchUtils implements ISFrameworkConstants{

	/*
		@Cacheable：负责将方法的返回值加入到缓存中		
		@CacheEvict：负责清除缓存
		
		@Cacheable 支持如下几个参数：		
			value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name		
			key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL		
			condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
		
		@CacheEvict 支持如下几个参数：
			value：缓存位置名称，不能为空，同上		
			key：缓存的key，默认为空，同上		
			condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL		
			allEntries：true表示清除value中的全部缓存，默认为false


	 */
	private static HashMap<String,Object> cached = new HashMap<String,Object>();

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean put(String key, Object value) {
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
	public static boolean replace(String key, Object value) {
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
	public static boolean delete(String key) {
		cached.remove(key);

		return true;
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return cached.get(key);
	}
}
