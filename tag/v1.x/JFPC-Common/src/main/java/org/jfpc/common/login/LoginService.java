package org.jfpc.common.login;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 系统登录 */
@Service
public class LoginService extends MyServiceSupport implements ISFrameworkConstants {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	public LoginDao getLoginDao() {
		return getMySqlSession().getMapper(LoginDao.class);
	}

	/**
	 * 登录用户数限制<br>
	 * 0不限制1一次
	 */
	public int loginNumers = 1;

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

			if (logger.isDebugEnabled())
				logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + loginer);
			
			loginer.setUserName(TokenHelper.getUserID(loginer.getToken()));
			List<LoginerBean> logins = getLoginDao().checkLogIn(loginer);
			
			for (int i = 0; i < logins.size(); i++) {
				LoginerBean login = logins.get(i);
				// 根据数目登录
				if (loginNumers > 1 && i >= loginNumers)
					doLogOut(login);
				// 从数据库里面验证TOKEN有效性
				if (loginer.getToken().equals(login.getToken()))
					result = true;
				//TODO 返回最后一次登录位置
				loginer.setLoginUrl(login.getLoginUrl());
			}
		} catch (Exception e) {
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
	public RESTResultBean login(LoginerBean loginer) {
		// 设定返回
		RESTResultBean rs = new RESTResultBean();

		LoginerBean loginerResult = getLoginDao().readLoginer(loginer);
		
//		////////////////////////////////////////
//		login = loginer;
//		login.setUserName("AEK_USER");
//		login.setCompanyId("AEK_COMP");	
//		passWord = login.getPassWord();
//		//////////////////////////////////////
		
		// AND p01_password = #{passWord}
		if (loginerResult != null && !StringUtils.isEmpty(loginerResult.getUserName())) {
			if (loginerResult.getPassWord().equals(loginer.getPassWord())) {
				loginer.setToken(TokenHelper.getToken(loginerResult.getUserName(),// 用户ID
						loginerResult.getCompanyId(),// 企业ID
						("" + System.currentTimeMillis()) // 安全Key=loginer.getSecurityCode()
						));
				loginer.setUserName(loginerResult.getUserName());
				//loginer.setVerCode("9990");//成功登录
				rs.setMessage("9990");
//				// 激活状态（是否激活  0激活1未激活）
//				if ("0".equals(login.getActiveType())) {
//					loginer.setToken("");
//					loginer.setVerCode("当前账户未激活");
//				}
//
//				// 启用状态（是否停用  0启用1停用）
//				if ("0".equals(login.getUseType())) {
//					loginer.setToken("");
//					loginer.setVerCode("当前账户未启用");
//				}

				rs.setToken(loginer.getToken());
				JSONObject result = new JSONObject();
				
				result.put("dlzmc", "张新华");//类型名称
				result.put("qylbid", loginerResult.getUserType());//登录用户类型(1/供应商，2/医院)
				result.put("qylbmc", "供应商");//类型名称
				result.put("zhdlsj", "2014/7/11 16:23:23");//上次登录时间
				result.put("sjhm", "15057177411");//手机号码
				result.put("logourl", "http://img6.cache.netease.com/cnews/news2012/img/logo_news.png");//企业图标地址				
				
				rs.setResult(result);
			} else {
				//loginer.setVerCode("9992");//密码错误，请重新输入
				rs.setCode("2");
				rs.setMessage("9992");
			}
		} else {
			//loginer.setVerCode("9991");//当前用户不存在
			rs.setCode("1");
			rs.setMessage("9991");
		}
		return rs;
	}
	

}
