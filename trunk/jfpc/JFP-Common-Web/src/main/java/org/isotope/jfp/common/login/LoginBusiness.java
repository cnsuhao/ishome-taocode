package org.isotope.jfp.common.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.user.LoginerBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 系统登录 */
@Service
public class LoginBusiness extends LoginService {

	/**
	 * 登录用户数限制<br>
	 * 0不限制1一次
	 */
	public int loginNumers = 0;

	public int getLoginNumers() {
		return loginNumers;
	}

	public void setLoginNumers(int loginNumers) {
		this.loginNumers = loginNumers;
	}

	// ******************************************************************************************
	/**
	 * 用户登录
	 * @param authorizerRefreshToken
	 * @return
	 */
	@Transactional
	public UserBean doLogIn(String authorizerRefreshToken) {
		// 获得登录用户信息（存在性检索）
		// 读取OPEN ID表数据
		// 完成用户登录
		List<UserBean> loginers = readLoginer(authorizerRefreshToken);
		if (EmptyHelper.isEmpty(loginers)) {
			// 创建新用户信息
			// 创建第三方授权信息
			loginers = new ArrayList<UserBean>();
			UserBean user0 = creatLoginer(authorizerRefreshToken);
			loginers.add(user0);
		}
		UserBean user = loginers.get(0);
		doOtherProcess(user);
		return user;
	}

	/**
	 * 用户登录
	 * @param loginer
	 * @return
	 */
	@Transactional
	public UserBean doLogIn(LoginerBean loginer) {
		// 获得登录用户信息（存在性检索）
		HashMap<String, String> login = new HashMap<String, String>();
		String account = loginer.getAccount();
		// 种类判断
		if (account.indexOf("@") > 0) {// 邮件
			login.put("email", account);
		} else if (StringUtils.isNumeric(account)) {// 手机号码
			login.put("phone", account);
		} else {// 用户名
			login.put("account", account);
		}
		// 完成用户登录
		UserBean user = new UserBean();
		List<UserBean> loginers = readLoginer(login);
		boolean logined = false;
		if (loginers == null) {
			user.setLoginStatus("2");
		} else if (loginers.size() == 0) {
			user.setLoginStatus("2");
		} else if (loginers.size() > 1) {
			// 系统整合的场合进行用户排他
			for (UserBean loginerdb : loginers) {
				if (loginer.getUserType().equals(loginerdb.getUserType())) {
					logined = checkLogin(loginer, loginerdb, user);
					if (logined == true) {
						break;
					}
				}
			}
		} else {
			logined = checkLogin(loginer, loginers.get(0), user);
		}
		if (logined == false) {
			user.setLoginStatus("1");
			return user;
		}

		// 记录登录时间
		user.setLoginTime(DateHelper.currentTimeMillisCN1());
		// 保证安全
		user.setPassWord(EMPTY);

		// 判断第二次登录
		if (0 == loginNumers) {
			// 不限制
		} else // if (loginNumers == 1)
		{
			// TODO
			// 注销原登录用户Token
			doLogoutToken(user);
			// 强制注销
			doLogOut(user);
		}
		doOtherProcess(user);
		return user;
	}

	/**
	 * 用户注销
	 */
	@Transactional
	public void doLogOut(UserBean user) {
		// 注销原登录用户Token
		doLogoutToken(user);
		// 强制注销
		doLogOut(user);
	}
	
	/**
	 * 账户唯一性检查
	 * 
	 * @param account
	 * @return false无true有
	 */
	public boolean accountCheck(String account, boolean all) {
		HashMap<String, String> login = new HashMap<String, String>();
		if (all) {
			// 种类判断
			if (account.indexOf("@") > 0) {// 邮件
				login.put("email", account);
			} else if (StringUtils.isNumeric(account)) {// 手机号码
				login.put("phone", account);
			} else {// 用户名
				login.put("account", account);
			}
		} else {
			login.put("account", account);
		}
		// 获得用户数据
		List<UserBean> loginers = readLoginer(login);
		if (loginers.size() >= 1) {
			return true;
		} 
		return false;
	}
	
	/**
	 * 完成登录后业务
	 * 
	 * @param user
	 */
	private void doOtherProcess(UserBean user) {
		// 创建本次登录Token
		makeLoginToken(user);
		// 保存本次登录信息（缓存、数据库）
		doLoginToken(user);
		// 保存本次登录日志（数据库）
		doLoginLog(user);
	}
	/**
	 * 检查用户登录可行性
	 * 
	 * @param loginerFrom
	 * @param loginerDB
	 * @param rs
	 */
	private boolean checkLogin(LoginerBean loginerFrom, UserBean loginerDB, UserBean user) {
		if (loginerDB.getPassWord().equals(loginerFrom.getPassWord()) || loginerDB.getPassWord().toLowerCase().equals(loginerFrom.getPassWord().toLowerCase().substring(3, 23))) {// 原有用户登录
			return true;
		}
		return false;
	}
}
