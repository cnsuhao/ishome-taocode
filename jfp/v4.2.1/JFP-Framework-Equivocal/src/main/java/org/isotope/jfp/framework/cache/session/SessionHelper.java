package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.token.TokenBusinessSupport;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author Spook
 * @version 4.2.1 2017/03/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/8/6
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	private static final ThreadLocal<TokenBusinessSupport> holder = new ThreadLocal<TokenBusinessSupport>();

	public static void setSessionAttribute(TokenBusinessSupport value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * 
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static TokenBusinessSupport getSessionAttribute() {
		return holder.get();
	}

	public static String currentHdp() {
		try {
			return getSessionAttribute().currentUser().getHdp();
		} catch (Exception e) {
			return null;
		}
	}

	public static String currentToken() {
		try {
			return getSessionAttribute().getToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static UserBean currentUser() {
		try {
			return getSessionAttribute().currentUser();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用户退出
	 * 
	 * @param event
	 */
	public static void sessionDestroyed() {
		try {
			getSessionAttribute().removeToken();
		} catch (Exception e) {
		}
	}
}
