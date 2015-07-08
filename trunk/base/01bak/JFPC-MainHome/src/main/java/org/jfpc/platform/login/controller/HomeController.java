package org.jfpc.platform.login.controller;

import javax.annotation.Resource;

import org.jfpc.base.helper.TokenHelper;
import org.jfpc.base.login.bean.LoginerBean;
import org.jfpc.base.login.service.LoginService;
import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 域名访问默认登陆页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Controller
public class HomeController extends MyControllerSupport{
	
	/**
	 * 登录用户主页面
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/00011000", method = RequestMethod.POST)
	public MyModelAndViewSupport homePOST(LoginerBean loginer) {
		MyModelAndViewSupport myPv = new MyModelAndViewSupport("home");
		loginer.setUserName(TokenHelper.getUserID(loginer.getToken()));
		myPv.addObject("loginer", loginer);
		return myPv;
	}
	
	/**
	 * 登录用户主页面
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/00011000", method = RequestMethod.GET)
	public MyModelAndViewSupport homeGET() {
		MyModelAndViewSupport myPv = new MyModelAndViewSupport("home");
		return myPv;
	}
}
