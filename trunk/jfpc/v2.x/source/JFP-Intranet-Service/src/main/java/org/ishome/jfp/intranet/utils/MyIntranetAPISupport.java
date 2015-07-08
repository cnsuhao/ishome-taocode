package org.ishome.jfp.intranet.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;

/**
 * 接口控制层超类
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public class MyIntranetAPISupport implements ISFrameworkConstants{

	/**
	 * 登录拦截
	 * 
	 * @param response
	 */
	public void doCheckLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
	}
	
	/**
	 * 返回拦截
	 * @param request
	 * @param response
	 */
	public void goBack(HttpServletRequest request, HttpServletResponse response) {
//		RESTResultBean rs = new RESTResultBean();
//		rs.setCode("9");
//		rs.setMessage("9");//登录失效，请重新登录
//		OutputStream out = response.getOutputStream();
//		out.write(JSONObject.fromObject(rs).toString().getBytes("UTF-8"));
//		out.flush();
//		out.close();
	}
}
