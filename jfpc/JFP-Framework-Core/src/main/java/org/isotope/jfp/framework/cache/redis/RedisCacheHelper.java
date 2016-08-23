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

	// //////////////////////////////////////////////////////////////
	public static void setSessionAttribute(int index, String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		myCache.putObject(key, value, 3600, true);
		myCache.init();
	}

	public static void setSessionAttribute(int index, int second, String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		myCache.putObject(key, value, second, true);
		myCache.init();
	}

	public static Object getSessionAttribute(int index, String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		Object obj = myCache.getObject(key);
		myCache.init();
		return obj;
	}

	public static Object removeSessionAttribute(int index, String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(index);
		Object obj = myCache.deleteObject(key);
		myCache.init();
		return obj;
	}
}
