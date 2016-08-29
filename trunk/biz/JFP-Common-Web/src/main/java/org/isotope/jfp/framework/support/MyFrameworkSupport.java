package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;

/**
 * 业务框架超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.1 2014/8/27 增加自定义提示信息页面{getMessageMAV}
 * @version 0.1.0 2014/2/8
 */
public class MyFrameworkSupport implements ISFrameworkConstants, ISModelConstants {

	// ///////////////当然用户信息/////////////////////////
	public static void setUserData(UserBean loginer) {
		SessionHelper.setSessionAttribute(loginer);
	}

	public static UserBean getUserData() {
		return SessionHelper.getSessionAttribute();
	}

}
