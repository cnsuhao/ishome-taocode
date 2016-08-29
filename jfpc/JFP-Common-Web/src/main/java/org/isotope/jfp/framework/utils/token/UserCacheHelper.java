package org.isotope.jfp.framework.utils.token;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.MySystemConfig;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;

/**
 * 缓存与本地线程同步
 * 
 * @author 001745
 *
 */
public class UserCacheHelper implements ISFrameworkConstants {

	public static void main(String[] args) throws Exception {
		UserBean user = new UserBean();
		user.setSchoolId(987654321L);
		user.setUserId(123456789L);
		user.setUserType("0");
		System.out.println(JSON.toJSONString(user));
	}

	/**
	 * 获得登录用户信息
	 * 
	 * @param token
	 * @return
	 */
	public static UserBean checkUser(String token) {
		try {
			if (EmptyHelper.isEmpty(token))
				return null;
			ICacheService myCache = BeanFactoryHelper.getBean("myCache");
			myCache.selectDB(3);
			String obj = (String) myCache.getObject(token, false);
			if (EmptyHelper.isEmpty(obj))
				return null;

			UserBean loginer = JSON.parseObject(obj, UserBean.class);
			myCache.init();
			return loginer;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 缓存登录用户信息
	 * 
	 * @param loginer
	 */
	public static void saveUser(UserBean loginer) {
		if (EmptyHelper.isEmpty(loginer))
			return;
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		MySystemConfig mySystemConfig = BeanFactoryHelper.getBean("mySystemConfig");

		myCache.selectDB(3);
		myCache.putObject(loginer.getToken(), JSON.toJSONString(loginer), Integer.parseInt(mySystemConfig.getConfig(Token_Can_Use_Hour)) * 3600, false);
		myCache.init();
	}

	public static UserBean removeUser(String token) {
		if (EmptyHelper.isEmpty(token))
			return null;

		try {
			if (EmptyHelper.isEmpty(token))
				return null;
			ICacheService myCache = BeanFactoryHelper.getBean("myCache");
			myCache.selectDB(3);
			String obj = (String) myCache.deleteObject(token, false);
			if (EmptyHelper.isEmpty(obj))
				return null;

			UserBean loginer = JSON.parseObject(obj, UserBean.class);
			myCache.init();
			return loginer;
		} catch (Exception e) {
			return null;
		}
	}
}
