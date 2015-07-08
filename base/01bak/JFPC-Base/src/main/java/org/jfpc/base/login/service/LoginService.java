package org.jfpc.base.login.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jfpc.base.ISFrameworkConstants;
import org.jfpc.base.helper.DateHelper;
import org.jfpc.base.helper.PKHelper;
import org.jfpc.base.helper.TokenHelper;
import org.jfpc.base.login.bean.LoginerBean;
import org.jfpc.base.login.dao.LoginDao;
import org.jfpc.base.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 系统登录 */
@Service
public class LoginService extends MyServiceSupport implements ISFrameworkConstants {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	public LoginDao getLoginDao() {
		return mySqlSession.getMapper(LoginDao.class);
	}

	/**
	 * 登录用户数限制<br>
	 * 0不限制1一次
	 */
	public int loginNumers = 2;

	public int getLoginNumers() {
		return loginNumers;
	}

	public void setLoginNumers(int loginNumers) {
		this.loginNumers = loginNumers;
	}

	// ******************************************************************************************
	@Transactional
	public void doLogIn(LoginerBean loginer) {
		try {
			LoginerBean login = new LoginerBean();

			BeanUtils.copyProperties(login, loginer);

			login.setSecurityCode(PKHelper.creatPUKey());
			login.setVerCode(DateHelper.currentTimeMillisCN1());

			// 判断第二次登录
			if (0 == loginNumers) {
				// 不限制
			} else if (loginNumers == 1) {
				LoginerBean logout = new LoginerBean();
				// 唯一登录
				logout.setUserName(TokenHelper.getUserID(loginer.getToken()));
				doLogOut(logout);					
			}

			// 根据用户ID插入登录信息
			getLoginDao().doLoginToken(login);
			getLoginDao().doLoginLog(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户退出
	 * 
	 * @param loginer
	 */
	public void doLogOut(LoginerBean loginer) {
		// 根据用户ID删除已有登录信息，插入新的登录信息
		getLoginDao().doLogOut(loginer);
	}

	@Transactional
	public boolean doCheckToken(LoginerBean loginer) {
		boolean result = false;
		try {
			if (StringUtils.isEmpty(loginer.getToken()))
				return false;
			loginer.setUserName(TokenHelper.getUserID(loginer.getToken()));
			List<LoginerBean> logins = getLoginDao().checkLogIn(loginer);

			for (int i = 0; i < logins.size(); i++) {
				LoginerBean login = logins.get(i);
				// 根据数目登录
				if (loginNumers > 1 && i >= loginNumers)
					doLogOut(login);
				if (logger.isDebugEnabled())
					logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + login);
				// 从数据库里面验证TOKEN有效性
				if (loginer.getToken().equals(login.getToken()))
					result = true;
				//TODO 返回最后一次登录位置
				loginer.setLoginUrl(login.getLoginUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ******************************************************************************************
	/**
	 * 用户登录
	 * 
	 * @param loginer
	 * @return
	 */
	public void login(LoginerBean loginer) {
		// MD5加密
		String passWord = new Md5PasswordEncoder().encodePassword(loginer.getPassWord(), null);
		if (logger.isDebugEnabled())
			logger.debug("passWord====///passWord////passWord=======>>>>>=========>>>" + passWord);
		// loginer.setPassWord();
		LoginerBean login = null;
		// loginer.setPassWord();
		login = getLoginDao().readLoginer(loginer);
		// AND p01_password = #{passWord}
		if (login != null && !StringUtils.isEmpty(login.getUserName())) {
			if (login.getPassWord().equals(passWord)) {
				loginer.setToken(TokenHelper.getToken(login.getUserName(),// 用户ID
						login.getCompanyId(),// 企业ID
						("" + System.nanoTime()) // 安全Key=loginer.getSecurityCode()
						));
				loginer.setUserName(login.getUserName());
				loginer.setVerCode("成功登录");
				// 激活状态（是否激活  0激活1未激活）
				if ("0".equals(login.getActiveType())) {
					loginer.setToken("");
					loginer.setVerCode("当前账户未激活");
				}

				// 启用状态（是否停用  0启用1停用）
				if ("0".equals(login.getUseType())) {
					loginer.setToken("");
					loginer.setVerCode("当前账户未启用");
				}

			} else {
				loginer.setVerCode("密码错误，请重新输入");
			}
		} else {
			loginer.setVerCode("当前用户不存在");
		}
	}
	

}
