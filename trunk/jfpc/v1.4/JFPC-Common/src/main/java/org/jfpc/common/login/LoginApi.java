package org.jfpc.common.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.BeanFactoryHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyFrameworkSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统常量
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Controller
public class LoginApi extends MyFrameworkSupport {
	private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
	@Resource
	protected LoginService LoginService_;

	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("redirect:/");
	}

	/**
	 * 用户登录
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/00000001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean normalLoginPOSTJson(
			LoginerBean loginer, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) {
		// 设定返回
		RESTResultBean rs = new RESTResultBean();

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);

		// 画面校验码
		if (StringUtils.isNotEmpty(loginer.getVerCode())) {
			setSessionAttribute(RANDOM_CODE,session.getAttribute(RANDOM_CODE));
			if (checkRandomCode(loginer.getVerCode()) == false) {
				// 错误返回
				loginer.setCallBackUrl(loginer.getLoginUrl());
				loginer.setVerCode("验证码错误，请重新输入！");
				rs.setCode("1");
				rs.setMessage(loginer.getVerCode());
				return rs;
			}
		}

		// 防伪验证码==MD5(产品ID+默认码+安全码)
		if (StringUtils.isNotEmpty(loginer.getSecurityCode())) {// 用户SessionID
			SecurityCodeService scs = null;
			try {
				scs = (SecurityCodeService) BeanFactoryHelper.getBean("securityCodeService");
				if (scs != null && scs.checkSecurityCode(loginer) == false) {
					// 错误返回
					loginer.setCallBackUrl(loginer.getLoginUrl());
					loginer.setVerCode("安全码校验失败，请关闭后重新登录！");
					rs.setCode("1");
					rs.setMessage(loginer.getVerCode());
					return rs;
				}
			} catch (Exception e) {
			}
		}
		// MD5加密
		String passWord = StringHelper.getPassword(loginer.getPassWord());
		loginer.setPassWord(passWord);
		
		/////////////////////// 登录系统/////////////////////////////////////
		rs = LoginService_.login(loginer);
        ///////////////////////////////////////////////////////////////////
		
		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);

		// 成功判断
		if (StringUtils.isEmpty(rs.getToken())) {
			// 错误返回
			loginer.setCallBackUrl(loginer.getLoginUrl());
		} else {
			// 成功登录
			LoginService_.doLogIn((LoginerBean) rs.getResult());
		}

		if (logger.isDebugEnabled())
			logger.debug("RESTResultBean====///RESTResultBean////RESTResultBean=======>>>>>=========>>>" + rs);

		return rs;
	}

	/**
	 * 账户唯一性检查
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/00000000/{account}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean accountCheckPOST(
			@PathVariable String account, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) {
		return LoginService_.accountCheck(account,false);
	}

	/**
	 * 用户登录(WEB页面)
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/00000000", method = RequestMethod.POST)
	public MyModelAndViewSupport normalLoginPOST(
			LoginerBean loginer, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) {
		// 设定返回
		MyModelAndViewSupport myPv = getModelAndView();

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);

		// 画面校验码
		if (StringUtils.isNotEmpty(loginer.getVerCode())) {
			setSessionAttribute(RANDOM_CODE,session.getAttribute(RANDOM_CODE));
			
			if (!checkRandomCode(loginer.getVerCode())) {
				// 错误返回
				loginer.setCallBackUrl(loginer.getLoginUrl());
				myPv.addObject(PAGE_MESSAGE, "验证码错误，请重新输入！");
				myPv.addObject("loginer", loginer);
				myPv.setViewName("callback");
				return myPv;
			}
		}

		// 防伪验证码==MD5(产品ID+默认码+安全码)
		{
			String securityCode = loginer.getSecurityCode();// 用户SessionID
			if (logger.isDebugEnabled())
				logger.debug("securityCode===========>>>>>>>>>>>>>>>>>>>>>" + securityCode);

			SecurityCodeService scs = null;
			try {
				scs = (SecurityCodeService) BeanFactoryHelper.getBean("securityCodeService");
				if (scs != null && scs.checkSecurityCode(loginer) == false) {
					// 错误返回
					loginer.setCallBackUrl(loginer.getLoginUrl());
					loginer.setVerCode("安全码校验失败，请关闭后重新登录！");
					myPv.addObject(PAGE_MESSAGE, loginer.getVerCode());
					return myPv;
				}
			} catch (Exception e) {
			}
		}

		// MD5加密
		String passWord = new Md5PasswordEncoder().encodePassword(loginer.getPassWord(), null);
		loginer.setPassWord(passWord);
		
		// 登录系统
		RESTResultBean rs = LoginService_.login(loginer);
		LoginerBean loginerResult = (LoginerBean) rs.getResult();
		loginerResult.setCallBackUrl(loginer.getCallBackUrl());
		loginerResult.setLoginUrl(loginer.getLoginUrl());
		myPv.addObject("loginer", loginerResult);
		myPv.setViewName("callback");

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);

		// 成功判断
		if (StringUtils.isEmpty(rs.getToken())) {
			// 错误返回
			loginer.setCallBackUrl(loginer.getLoginUrl());
			myPv.addObject(PAGE_MESSAGE, rs.getMessage());
		} else {
			// 成功登录
			LoginService_.doLogIn(loginerResult);
			// Session保存
			//session.setAttribute(CONSTANT_LOGINER, loginerResult);
			//清除验证码缓存
			session.removeAttribute(RANDOM_CODE);
		}
		return myPv;
	}

	/**
	 * 登录用户主页面
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/00000000", method = RequestMethod.GET)
	public MyModelAndViewSupport errorLoginGET() {
		return getModelAndView();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	/**
	 * 登录用户退出系统
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/00099000", method = RequestMethod.GET)
	public MyModelAndViewSupport loginOutGET(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) {
		// 销毁session
		session.invalidate();
		// 退出登录
		return getModelAndView();
	}

	/**
	 * 登录用户退出系统
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/00099000", method = RequestMethod.POST)
	public MyModelAndViewSupport loginOutPOST(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) {
		// 销毁session
		session.invalidate();
		
		LoginerBean loginer = new LoginerBean();
		
		Object tokenSession = getSessionAttribute(CONSTANT_USER_TOKEN);
		//RequestContextHolder.getRequestAttributes().removeAttribute(CONSTANT_USER_TOKEN, 1);
		if (tokenSession != null) {
			loginer.setToken(tokenSession.toString());
			LoginService loginService = (LoginService)BeanFactoryHelper.getBean("loginService");
			loginService.doLogOut(loginer);
		}
		
		// 退出登录
		return getModelAndView();
	}
}
