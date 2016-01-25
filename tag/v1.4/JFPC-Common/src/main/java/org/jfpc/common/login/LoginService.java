package org.jfpc.common.login;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.helper.TokenHelper;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/** 系统登录 */
public class LoginService extends MyServiceSupport implements ISFrameworkConstants {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	public LoginDao getLoginDao() {
		return getMySqlSession().getMapper(LoginDao.class);
	}

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
	@Transactional
	public void doLogIn(LoginerBean loginer) {
		try {
			LoginerBean login = new LoginerBean();

			BeanUtils.copyProperties(loginer, login);
			// 定义数据PUK
			login.setSecurityCode(PKHelper.creatPUKey());
			// 记录登录时间
			login.setLoginTime(DateHelper.currentTimeMillisCN1());

			// 判断第二次登录
			if (0 == loginNumers) {
				// 不限制
			} else if (loginNumers == 1) {
				LoginerBean logout = new LoginerBean();
				// 唯一登录
				logout.setUserName(TokenHelper.getUserID(login.getToken()));
				doLogOut(logout);
			}
			login.setPassWord(EMPTY);//保证安全
			// 记录登录信息
			doLoginToken(login);
			//记录登录日志
			getLoginDao().doLoginLog(login);
			//更新最后登录时间
			getLoginDao().doLoginInfo(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录Token
	 * 
	 * @param loginer
	 */
	public void doLoginToken(LoginerBean loginer) {
		loginer.setPpp(JSONObject.fromObject(loginer).toString());
		// 根据用户ID删除已有登录信息，插入新的登录信息
		getLoginDao().doLoginToken(loginer);
	}

	/**
	 * 用户登录Token
	 * 
	 * @param loginer
	 */
	public void doUpdateByToken(LoginerBean loginer) {
		loginer.setPpp(JSONObject.fromObject(loginer).toString());
		// 根据用户ID删除已有登录信息，插入新的登录信息
		getLoginDao().doUpdateByToken(loginer);
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

	/**
	 * 获得登录信息
	 * @param token
	 * @return
	 */
	public LoginerBean getCheckToken(String token) {
		if (StringUtils.isEmpty(token))
			return null;
		if (logger.isDebugEnabled())
			logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + token);

		LoginerBean login = getLoginDao().doCheckToken(token);
		return login;
	}

	/**
	 * 
	 * @param puk
	 * @return
	 */
	public HashMap<?, ?> getCompanyLoginer(String puk) {
		if (StringUtils.isEmpty(puk))
			return null;
		if (logger.isDebugEnabled())
			logger.debug("getCompanyLoginer//////////////>>>>>>>>getCompanyLoginer===" + puk);

		HashMap<?, ?> map = getLoginDao().getCompanyLoginer(puk);
		return map;
	}
	
	public boolean doCheckToken(String token) {
		if (StringUtils.isEmpty(token))
			return false;
		if (logger.isDebugEnabled())
			logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + token);

		LoginerBean loginerResult = getLoginDao().doCheckToken(token);
		if (loginerResult == null){
			return false;
		}
		else
		{
			loginerResult = (LoginerBean) JSONObject.toBean(JSONObject.fromObject(loginerResult.getPpp()),loginerResult.getClass());
			//保存用户登录信息
			super.setLoginerBean(loginerResult);
		}
		return true;
	}
	@Transactional
	public boolean doCheckToken(LoginerBean loginer) {
		boolean result = false;
		try {
			if (StringUtils.isEmpty(loginer.getToken()))
				return false;

			if (logger.isDebugEnabled())
				logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + loginer);

			loginer.setUserName(TokenHelper.getUserID(loginer.getToken()));
			List<LoginerBean> logins = getLoginDao().checkLogIn(loginer);

			for (int i = 0; i < logins.size(); i++) {
				LoginerBean loginerResult = logins.get(i);
				// 根据数目登录
				if (loginNumers > 1 && i >= loginNumers)
					doLogOut(loginerResult);
				// 从数据库里面验证TOKEN有效性
				if (loginer.getToken().equals(loginerResult.getToken())){
					result = true;
					//换成成为真实登录数据
					loginerResult = (LoginerBean) JSONObject.toBean(JSONObject.fromObject(loginerResult.getPpp()),loginerResult.getClass());
					loginer = loginerResult;
					//保存用户登录信息
					super.setToken(loginer.getToken());
					super.setLoginerBean(loginerResult);
					break;
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 用户退出
	 * 
	 * @param loginer
	 */
	public List<LoginerBean> readLoginer(LoginerBean loginer) {
		// 根据用户ID删除已有登录信息，插入新的登录信息
		return getLoginDao().readLoginer(loginer);
	}

	// ******************************************************************************************
	// @Resource
	// MessageModelUtils MessageUtil_;
	/**
	 * 用户登录
	 * 
	 * @param loginer
	 * @return
	 */
	public RESTResultBean login(LoginerBean loginer) {
		// 设定返回
		RESTResultBean rs = new RESTResultBean();
		boolean logined = false;
		rs.setResult(loginer);
		// 种类判断
		if (StringUtils.isNumeric(loginer.getUserName())) {// 手机号码
			loginer.setEb5(ONE);
		} else if (loginer.getUserName().indexOf("@") > 0) {// 邮件
			loginer.setEb5(TWO);
		} else {// 用户名
			loginer.setEb5(ZERO);
		}
		// 获得用户数据
		List<LoginerBean> loginers = readLoginer(loginer);
		if (loginers == null) {
			rs.setCode("1");
			rs.setMessage("9991");
		} else if (loginers.size() == 0) {
			rs.setCode("1");
			rs.setMessage("9991");
		} else if (loginers.size() > 1) {
			//系统整合的场合进行用户排他
			for(LoginerBean loginerdb : loginers){
				if(loginer.getUserType().equals(loginerdb.getUserType()))
				{
					logined = checkLogin(loginer,loginerdb,rs);
					if(logined==true){
						break;
					}
				}
			}

			if(logined==false){
				rs.setCode("1");
				if (StringUtils.isNumeric(loginer.getUserName())) {// 手机号码
					rs.setMessage("9981");
				} else if (loginer.getUserName().indexOf("@") > 0) {// 邮件
					rs.setMessage("9982");
				} else {// 用户名
					rs.setMessage("9983");
				}
			}
		} else {
			logined = checkLogin(loginer,loginers.get(0),rs);
			rs.setMessage("9983");
		}
		return rs;
	}
	
	/**
	 * 检查用户登录可行性
	 * @param loginerFrom
	 * @param loginerDB
	 * @param rs
	 */
	public boolean checkLogin(LoginerBean loginerFrom,LoginerBean loginerDB,RESTResultBean rs){
		// if (loginerResult != null &&
		// !StringUtils.isEmpty(loginerResult.getUserName())) {		
		if (loginerDB.getPassWord().equals(loginerFrom.getPassWord()) || loginerDB.getPassWord().toLowerCase().equals(loginerFrom.getPassWord().toLowerCase().substring(3, 23))) {// 原有用户登录
			loginerDB.setToken(TokenHelper.getToken(loginerDB.getUserName(),// 用户ID
					loginerDB.getCompanyId(),// 企业ID
					("" + System.currentTimeMillis()) // 安全Key=loginer.getSecurityCode()
					));
			
			rs.setMessage("9990");
			rs.setToken(loginerDB.getToken());
			rs.setResult(loginerDB);
			return true;
		} else {
			// loginer.setVerCode("9992");//密码错误，请重新输入
			rs.setCode("2");
		}
		return false;
	}

	/**
	 * 账户唯一性检查
	 * 
	 * @param account
	 * @return 1 无0有
	 */
	public RESTResultBean accountCheck(String account, boolean all) {
		// 设定返回
		RESTResultBean rs = new RESTResultBean();
		LoginerBean loginer = new LoginerBean();
		loginer.setUserName(account);
		if (all) {
			// 种类判断
			if (StringUtils.isNumeric(account)) {// 手机号码
				loginer.setEb5(ONE);
			} else if (account.indexOf("@") > 0) {// 邮件
				loginer.setEb5(TWO);
			} else {// 用户名
				loginer.setEb5(ZERO);
			}
		} else {
			loginer.setEb5(ZERO);
		}
		// 获得用户数据
		List<LoginerBean> loginers = getLoginDao().readLoginer(loginer);
		if (loginers.size() == 1) {
			LoginerBean loginerRs = loginers.get(0);
			loginerRs.setEb5(loginer.getEb5());
			rs.setResult(loginerRs);
		} else {
			rs.setCode("1");
			rs.setResult(loginer.getEb5());
		}
		return rs;
	}

}
