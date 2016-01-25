package com.japc.platform.resources.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.jfpc.base.bean.RESTResultBean;
import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyFrameworkSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 域名访问系统首页
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/5/30
 */
@Controller
public class IndexController extends MyFrameworkSupport{
	
	/**
	 * 默认首页
	 * @return
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("index");
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public MyModelAndViewSupport root(Locale locale,HttpServletRequest request) {
		// 销毁session
		request.getSession().invalidate();
		return getModelAndView();
	}
	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public MyModelAndViewSupport rootPOST(Locale locale,HttpServletRequest request) {
		// 销毁session
		request.getSession().invalidate();
		return getModelAndView();
	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(MyControllerSupport.class);
	/**
	 * 默认登录失效界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00009999", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m99999GET() {
		RESTResultBean rs = new RESTResultBean();
		rs.setCode("9");
		rs.setMessage("登录失效，请重新登录");
		if (logger.isDebugEnabled())
			logger.debug("XXXXXXXXXXXXXXXXXXXXXX");
		return rs;
	}
}
