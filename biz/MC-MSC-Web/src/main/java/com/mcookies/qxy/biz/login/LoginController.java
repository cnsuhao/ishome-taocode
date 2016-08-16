package com.mcookies.qxy.biz.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.isotope.jfp.common.login.LoginBusiness;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.user.LoginerBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
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
// @Controller
public class LoginController extends MyControllerSupport {
	@Resource
	protected LoginBusiness LoginService_;

	// public MyModelAndViewSupport getModelAndView() {
	// return new MyModelAndViewSupport("redirect:/");
	// }

	/**
	 * 用户登录
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doLoginPOST(LoginerBean loginer, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设定返回
		RESTResultBean rs = new RESTResultBean();

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);
		//
		// // 画面校验码
		// if (StringUtils.isNotEmpty(loginer.getVerCode())) {
		// setSessionAttribute(RANDOM_CODE, session.getAttribute(RANDOM_CODE));
		// if (checkRandomCode(loginer.getVerCode()) == false) {
		// // 错误返回
		// loginer.setCallBackUrl(loginer.getLoginUrl());
		// loginer.setVerCode("验证码错误，请重新输入！");
		// rs.setCode("1");
		// rs.setMessage(loginer.getVerCode());
		// return rs;
		// }
		// }
		//
		// // 防伪验证码==MD5(产品ID+默认码+安全码)
		// if (StringUtils.isNotEmpty(loginer.getSecurityCode())) {//
		// 用户SessionID
		// SecurityCodeService scs = null;
		// try {
		// scs = (SecurityCodeService)
		// BeanFactoryHelper.getBean("securityCodeService");
		// if (scs != null && scs.checkSecurityCode(loginer) == false) {
		// // 错误返回
		// loginer.setCallBackUrl(loginer.getLoginUrl());
		// loginer.setVerCode("安全码校验失败，请关闭后重新登录！");
		// rs.setCode("1");
		// rs.setMessage(loginer.getVerCode());
		// return rs;
		// }
		// } catch (Exception e) {
		// }
		// }
		// // MD5加密
		// String passWord = StringHelper.getPassword(loginer.getPassWord());
		// loginer.setPassWord(passWord);

		/////////////////////// 登录系统/////////////////////////////////////
		loginer.setIpAdress(HttpRequestHelper.getIpAddr(request));		
		UserBean user = LoginService_.doLogIn(loginer);
		///////////////////////////////////////////////////////////////////

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + user);

		rs.setCode(user.getLoginStatus());

		return rs;
	}

	/**
	 * 账户唯一性检查
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/accountCheck", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean accountCheckPOST(String account, String userType, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		RESTResultBean rs = new RESTResultBean();
		boolean only = LoginService_.accountCheck(account, userType, false);
		if (only == true) {

		}
		return rs;
	}

	// /**
	// * 用户登录(WEB页面)
	// *
	// * @param productId
	// * @return
	// */
	// @RequestMapping(value = "/00000000", method = RequestMethod.POST)
	// public MyModelAndViewSupport normalLoginPOST(LoginerBean loginer,
	// HttpServletRequest request, HttpServletResponse response, HttpSession
	// session) {
	// // 设定返回
	// MyModelAndViewSupport myPv = getModelAndView();
	//
	// if (logger.isDebugEnabled())
	// logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" +
	// loginer);
	//
	// // 画面校验码
	// if (StringUtils.isNotEmpty(loginer.getVerCode())) {
	// setSessionAttribute(RANDOM_CODE, session.getAttribute(RANDOM_CODE));
	//
	// if (!checkRandomCode(loginer.getVerCode())) {
	// // 错误返回
	// loginer.setCallBackUrl(loginer.getLoginUrl());
	// myPv.addObject(PAGE_MESSAGE, "验证码错误，请重新输入！");
	// myPv.addObject("loginer", loginer);
	// myPv.setViewName("callback");
	// return myPv;
	// }
	// }
	//
	// // 防伪验证码==MD5(产品ID+默认码+安全码)
	// {
	// String securityCode = loginer.getSecurityCode();// 用户SessionID
	// if (logger.isDebugEnabled())
	// logger.debug("securityCode===========>>>>>>>>>>>>>>>>>>>>>" +
	// securityCode);
	//
	// SecurityCodeService scs = null;
	// try {
	// scs = (SecurityCodeService)
	// BeanFactoryHelper.getBean("securityCodeService");
	// if (scs != null && scs.checkSecurityCode(loginer) == false) {
	// // 错误返回
	// loginer.setCallBackUrl(loginer.getLoginUrl());
	// loginer.setVerCode("安全码校验失败，请关闭后重新登录！");
	// myPv.addObject(PAGE_MESSAGE, loginer.getVerCode());
	// return myPv;
	// }
	// } catch (Exception e) {
	// }
	// }
	//
	// // MD5加密
	// String passWord = new
	// Md5PasswordEncoder().encodePassword(loginer.getPassWord(), null);
	// loginer.setPassWord(passWord);
	//
	// // 登录系统
	// RESTResultBean rs = LoginService_.login(loginer);
	// LoginerBean loginerResult = (LoginerBean) rs.getResult();
	// loginerResult.setCallBackUrl(loginer.getCallBackUrl());
	// loginerResult.setLoginUrl(loginer.getLoginUrl());
	// myPv.addObject("loginer", loginerResult);
	// myPv.setViewName("callback");
	//
	// if (logger.isDebugEnabled())
	// logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" +
	// loginer);
	//
	// // 成功判断
	// if (StringUtils.isEmpty(rs.getToken())) {
	// // 错误返回
	// loginer.setCallBackUrl(loginer.getLoginUrl());
	// myPv.addObject(PAGE_MESSAGE, rs.getMessage());
	// } else {
	// // 成功登录
	// LoginService_.doLogIn(loginerResult);
	// // Session保存
	// // session.setAttribute(CONSTANT_LOGINER, loginerResult);
	// // 清除验证码缓存
	// session.removeAttribute(RANDOM_CODE);
	// }
	// return myPv;
	// }

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	/**
	 * 登录用户退出系统
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean loginOutGET(UserBean user, HttpSession session) {
		RESTResultBean rs = new RESTResultBean();
		// 销毁session
		session.invalidate();
		LoginService_.doLogOut(user);
		// 退出登录
		return rs;
	}

	/**
	 * 登录用户退出系统
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean loginOutPOST(UserBean user, HttpSession session) {
		RESTResultBean rs = new RESTResultBean();
		// 销毁session
		session.invalidate();
		LoginService_.doLogOut(user);

		// 退出登录
		return rs;
	}
}
