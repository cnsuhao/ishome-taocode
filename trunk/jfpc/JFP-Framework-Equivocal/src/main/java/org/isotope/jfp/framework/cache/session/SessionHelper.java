package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.beans.token.TokenBusinessBean;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author fucy
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/8/6
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	private static final ThreadLocal<TokenBusinessBean> holder = new ThreadLocal<TokenBusinessBean>();

	public static void setSessionAttribute(TokenBusinessBean value) {
		holder.set(value);
	}

	public static TokenBusinessBean getSessionAttribute() {
		return holder.get();
	}

	/**
	 * 加载测试用户登录数据
	 */
	public static void loadTestSession() {
		setSessionAttribute(new TokenBusinessBean("12345678", "2223334", "1", "1234567890123456", "6543210987654321"));
	}
}
