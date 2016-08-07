package org.isotope.jfp.common.login;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 缓存与本地线程同步
 * 
 * @author 001745
 *
 */
public class LoginerCacheHelper {

	/**
	 * 获得登录用户信息
	 * 
	 * @param token
	 * @return
	 */
	public static UserBean checkLoginer(String token) {
		if (EmptyHelper.isEmpty(token))
			return null;
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(2);
		UserBean loginer = (UserBean) myCache.getObject(token);
		myCache.init();
		return loginer;
	}

	/**
	 * 缓存登录用户信息
	 * 
	 * @param loginer
	 */
	public static void saveLoginer(UserBean loginer) {
		if (EmptyHelper.isEmpty(loginer))
			return;
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(2);
		myCache.putObject(loginer.getToken(), loginer);
		myCache.init();
	}

	public static void removeLoginer(String token) {
		if (EmptyHelper.isEmpty(token))
			return;
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(2);
		myCache.deleteObject(token);
		myCache.init();
	}
}
