package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.beans.user.LoginerBean;

/**
 * 登录用户Session（UserBean）本地ThreadLocal
 * 
 * @author fucy
 * @version 3.2.1 2016/8/6
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	private static final ThreadLocal<LoginerBean> holder = new ThreadLocal<LoginerBean>();

	public static void setSessionAttribute(LoginerBean value) {
		holder.set(value);
	}

	public static LoginerBean getSessionAttribute() {
		return holder.get();
	}
}
