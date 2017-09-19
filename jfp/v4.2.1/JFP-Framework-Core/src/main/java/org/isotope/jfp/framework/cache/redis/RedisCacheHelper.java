package org.isotope.jfp.framework.cache.redis;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

/**
 * Redis缓存操作key-value
 * 
 * @author Spook
 * @since 3.2.1
 * @version 3.2.1 2016/08/17
 * @see <ICacheService>
 */
public class RedisCacheHelper implements ISFrameworkConstants {
	public static void setSessionAttribute(String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.putObject(key, value, 3600, false);
		myCache.init();
	}

	public static void setSessionAttribute(int second, String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.putObject(key, value, second, false);
		myCache.init();
	}

	public static Object getSessionAttribute(String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		Object obj = myCache.getObject(key, false);
		myCache.init();
		return obj;
	}

	public static Object removeSessionAttribute(String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		Object obj = myCache.deleteObject(key, false);
		myCache.init();
		return obj;
	}
}
