package org.isotope.jfp.common.login;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.token.BusinessTokenHelper;

public class LoginService extends MyServiceSupport implements ISFrameworkConstants {

	public LoginDao getLoginDao() {
		return getMySqlSession().getMapper(LoginDao.class);
	}

	/**
	 * 读取用户信息
	 * 
	 * @param loginer
	 */
	protected List<UserBean> readTeacherLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readTeacherLoginer(loginer);
	}
	protected List<UserBean> readParentLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readParentLoginer(loginer);
	}
	protected List<UserBean> readStudentLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readStudentLoginer(loginer);
	}
	protected List<UserBean> readLoginer(String authorizerRefreshToken) {
		return getLoginDao().readLoginerByOpenId(authorizerRefreshToken);
	}

	/**
	 * 创建用户信息
	 * 
	 * @param authorizerRefreshToken
	 */
	protected UserBean creatLoginer(String authorizerRefreshToken) {
		UserBean user = new UserBean();
		user.setOpenId(authorizerRefreshToken);
		getLoginDao().creatLoginerByOpenId(authorizerRefreshToken);
		return user;
	}

	protected void makeLoginToken(UserBean loginer) {
		String token = BusinessTokenHelper.getBizToken(loginer.getCompanyId(), loginer.getUserId(), ("" + System.currentTimeMillis()), loginer.getUserType());
		loginer.setToken(token);
	}

	/**
	 * 用户登录Token
	 * 
	 * @param loginer
	 */
	protected void doLoginToken(UserBean loginer) {
		// 缓存登录信息
		LoginerCacheHelper.saveLoginer(loginer);
		// 数据库保存
		getLoginDao().doLoginToken(loginer);
	}

	/**
	 * 用户登出
	 * 
	 * @param loginer
	 */
	protected void doLogoutToken(UserBean loginer) {
		LoginerCacheHelper.removeLoginer(loginer.getToken());
		getLoginDao().doLogoutToken(loginer);
	}

	/**
	 * 
	 * @param loginer
	 */
	protected void doLoginLog(UserBean loginer) {
		getLoginDao().doLoginLog(loginer);
	}

	/**
	 * 更新用户登录Token
	 * 
	 * @param loginer
	 */
	protected void doUpdateByToken(UserBean loginer) {
		// 根据用户ID删除已有登录信息，插入新的登录信息
		getLoginDao().doUpdateByToken(loginer);
	}

	/**
	 * 用户退出
	 * 
	 * @param loginer
	 */
	protected void doLogOut(UserBean loginer) {
		// 根据用户ID删除已有登录信息，插入新的登录信息
		getLoginDao().doLogOut(loginer);
	}

	/**
	 * 获得登录信息
	 * 
	 * @param token
	 * @return
	 */
	protected UserBean checkLoginer(String token) {
		if (EmptyHelper.isEmpty(token))
			return null;

		UserBean loginer = getLoginDao().doCheckToken(token);
		return loginer;
	}

	//
	// public boolean doCheckToken(String token) {
	// if (StringUtils.isEmpty(token))
	// return false;
	// if (logger.isDebugEnabled())
	// logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" +
	// token);
	//
	// LoginerBean loginerResult = getLoginDao().doCheckToken(token);
	// if (loginerResult == null) {
	// return false;
	// } else {
	// loginerResult = (LoginerBean)
	// JSONObject.toBean(JSONObject.fromObject(loginerResult.getPpp()),
	// loginerResult.getClass());
	// // 保存用户登录信息
	// super.setLoginerBean(loginerResult);
	// }
	// return true;
	// }
	//
	// @Transactional
	// public boolean doCheckToken1(LoginerBean loginer) {
	// boolean result = false;
	// try {
	// if (StringUtils.isEmpty(loginer.getToken()))
	// return false;
	//
	// if (logger.isDebugEnabled())
	// logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" +
	// loginer);
	//
	// loginer.setUserName(TokenHelper.getUserID(loginer.getToken()));
	// List<LoginerBean> logins = getLoginDao().checkLogIn(loginer);
	//
	// for (int i = 0; i < logins.size(); i++) {
	// LoginerBean loginerResult = logins.get(i);
	// // 根据数目登录
	// if (loginNumers > 1 && i >= loginNumers)
	// doLogOut(loginerResult);
	// // 从数据库里面验证TOKEN有效性
	// if (loginer.getToken().equals(loginerResult.getToken())) {
	// result = true;
	// // 换成成为真实登录数据
	// loginerResult = (LoginerBean)
	// JSONObject.toBean(JSONObject.fromObject(loginerResult.getPpp()),
	// loginerResult.getClass());
	// loginer = loginerResult;
	// // 保存用户登录信息
	// super.setToken(loginer.getToken());
	// super.setLoginerBean(loginerResult);
	// break;
	// }
	// }
	// } catch (Exception e) {
	// }
	// return result;
	// }

}
