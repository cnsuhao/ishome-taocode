package org.jfpc.framework.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口控制层超类
 * 
 * @author Spook
 * @since 1.0.1
 * @version 0.1.0 2014/12/17
 */
public class MyJSONControllerSupport extends SuperControllerSupport implements ISSessionSupport {

	private static final Logger logger = LoggerFactory.getLogger(SuperControllerSupport.class);

	/**
	 * 返回拦截
	 * @param request
	 * @param response
	 */
	public void goBack(HttpServletRequest request, HttpServletResponse response) {
		goBack(request, response, true);
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
