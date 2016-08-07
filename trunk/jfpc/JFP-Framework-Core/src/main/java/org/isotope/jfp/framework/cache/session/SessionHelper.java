package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.beans.user.UserBean;

/**
 * 登录用户Session（UserBean）
 * 
 * @author fucy
 * @version 3.2.1 2016/8/6
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	private static final ThreadLocal<UserBean> holder = new ThreadLocal<UserBean>();

	public static void setUserData(UserBean value) {
		holder.set(value);
	}

	public static UserBean getUserData() {
		return holder.get();
	}
}
