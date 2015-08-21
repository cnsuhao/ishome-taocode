package com.hundsun.med.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class SuperControllerSupport extends MyFrameworkSupport {

	private Logger logger = LoggerFactory.getLogger(SuperControllerSupport.class);

	// 登录相关
	// @Resource
	// protected LoginService loginService;

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView(String pageId) {
		return new MyModelAndViewSupport(pageId);
	}

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("");
	}

	/**
	 * 菜单用户和企业基本信息
	 * 
	 * @param request
	 * @param response
	 */
	public boolean loadUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return true;
	}
}
