package org.isotope.jfp.framework.common;

import org.isotope.jfp.framework.beans.user.UserBean;

/**
 * 当前登录用户
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public interface ILoginer {
	/**
	 * 当前登录用户
	 */
	UserBean currentUser();

}