package org.isotope.jfp.framework.cache.redis;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

import com.alibaba.fastjson.JSON;

/**
 * Redis缓存操作key-value
 * 
 * @author Spook
 * @since 3.2.1
 * @version 3.2.1 2016/08/17
 * @see <ICacheService>
 */
public class RedisCacheHelper implements ISFrameworkConstants {

	// //////////////////////////////////////////////////////////////
	public static void setSessionAttribute(int index, String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		myCache.putObject(key, JSON.toJSONString(value), 3600, false);
		myCache.init();
	}

	public static String getSessionAttribute(int index, String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		String obj = (String) myCache.getObject(key, false);
		myCache.init();
		return obj;
	}

	public static String removeSessionAttribute(int index, String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		String obj = (String) myCache.deleteObject(key);
		myCache.init();
		return obj;
	}
}
