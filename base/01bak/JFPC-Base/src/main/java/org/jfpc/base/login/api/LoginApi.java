package org.jfpc.base.login.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jfpc.base.helper.BeanFactoryHelper;
import org.jfpc.base.login.bean.LoginerBean;
import org.jfpc.base.login.service.LoginService;
import org.jfpc.base.login.service.SecurityCodeService;
import org.jfpc.base.support.MyFrameworkSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	 * 用户登录(WEB页面)
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/00000000", method = RequestMethod.POST)
	@ResponseBody
	public MyModelAndViewSupport normalLoginPOST(LoginerBean loginer, HttpServletRequest request) {

		// 设定返回
		MyModelAndViewSupport myPv = getModelAndView();

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);
		// 防伪验证码==MD5(产品ID+默认码+安全码)
		String securityCode = loginer.getSecurityCode();// 用户SessionID
		if (logger.isDebugEnabled())
			logger.debug("securityCode===========>>>>>>>>>>>>>>>>>>>>>" + securityCode);

		SecurityCodeService scs = null;
		try{
			scs = (SecurityCodeService) BeanFactoryHelper.getBean("securityCodeService");		
			if (scs != null && scs.checkSecurityCode(loginer) == false) {
				// 错误返回
				loginer.setCallBackUrl(loginer.getLoginUrl());
				loginer.setVerCode("安全码校验失败，请关闭后重新登录！");
				myPv.addObject("message", loginer.getVerCode());
				return myPv;
			}
		}catch(Exception e){}		

		// 登录系统
		LoginService_.login(loginer);

		myPv.addObject("loginer", loginer);
		myPv.setViewName("callback");

		if (logger.isDebugEnabled())
			logger.debug("loginer====///loginer////loginer=======>>>>>=========>>>" + loginer);

		// 成功判断
		if (StringUtils.isEmpty(loginer.getToken())) {
			// 错误返回
			loginer.setCallBackUrl(loginer.getLoginUrl());
			myPv.addObject("message", loginer.getVerCode());
		} else {
			// 成功登录
			LoginService_.doLogIn(loginer);
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
	public MyModelAndViewSupport loginOutGET(HttpServletRequest request) {
		// 销毁session
		request.getSession().invalidate();
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
	public MyModelAndViewSupport loginOutPOST(HttpServletRequest request) {
		// 销毁session
		request.getSession().invalidate();
		// 退出登录
		return getModelAndView();
	}
}
