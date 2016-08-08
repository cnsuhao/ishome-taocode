package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;

import com.alibaba.fastjson.JSON;

/**
 * 业务框架超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.1 2014/8/27 增加自定义提示信息页面{getMessageMAV}
 * @version 0.1.0 2014/2/8
 */
public class MyFrameworkSupport implements ISFrameworkConstants, ISModelConstants {

	// //////////////////////////////////////////////////////////////
	public void setSessionAttribute(String key, Object value) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(1);
		myCache.putObject(key, JSON.toJSONString(value), 3600, false);
		myCache.init();
	}

	protected Object getSessionAttribute(String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(1);
		String obj = (String) myCache.getObject(key, false);
		myCache.init();
		return obj;
	}

	protected void removeSessionAttribute(String key) {
		ICacheService myCache = BeanFactoryHelper.getBean("myCache");
		myCache.selectDB(1);
		myCache.deleteObject(key);
		myCache.init();
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 校验图片验证码
	 * 
	 * @param verCode
	 * @return
	 */
	public boolean checkRandomCode(String verCode) {
		return verCode.toUpperCase().equals((String) getSessionAttribute(RANDOM_CODE));
	}

	/**
	 * 校验短信验证码
	 * 
	 * @param phone
	 * @param bizId
	 * @param code
	 * @return 核对手机验证码0正确1失败2过期
	 */
	public int validateSMSCode(String phone, String bizId, String code) {
		if (getSessionAttribute(phone + bizId) == null) {
			return 2;
		}
		if (!code.equals(getSessionAttribute(phone + bizId))) {
			return 1;
		}
		return 0;
	}

	// ////////////////处理线程安全/////////////////////////
	public static UserBean checkLoginer(String token) {
		return UserCacheHelper.checkUser(token);
	}
	public static void setUserData(UserBean loginer) {
		SessionHelper.setUserData(loginer);
	}
	public static UserBean getUserData() {
		return SessionHelper.getUserData();
	}
}
