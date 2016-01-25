package com.jfpc.base.token.controller;

import org.jfpc.framework.bean.RESTResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Java Framework Project for Cloud
 * @author Spook
 *
 */
@Controller
public class VersionController {
	
	String Version ="v0.0.1 at 2014.6.11";
	
	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00000", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m00000GET() {
		RESTResultBean rs = new RESTResultBean();
		rs.setMessage("中国医疗材料信息平台");
		rs.setResult(Version);
		return rs;
	}
	
//	/**
//	 * 默认登录界面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/00000", method = RequestMethod.GET)
//	@ResponseBody
//	public RESTResultBean m00000GET() {
//		RESTResultBean rs = new RESTResultBean();
//		rs.setResultmessage("医疗材料信息平台");
//		rs.setResult(Version);
//		return rs;
//	}
//
//	
//	
//	@RequestMapping(value = "/00004", method = RequestMethod.GET)
//	@ResponseBody
//	public RESTResultBean doCheckLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.sendRedirect("/99999");
//		response.sendError(405);
//		RESTResultBean rs = new RESTResultBean();
//		rs.setResultmessage("医疗材料信息平台");
//		rs.setResult("123");
//		return rs;
//	}
//	/**
//	 * 默认登录界面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/00001", method = RequestMethod.GET)
//	@ResponseBody
//	public RESTResultBean m00001GET() {
//		RESTResultBean rs = new RESTResultBean();
//		
//		LoginerBean user = new LoginerBean();
//		user.setToken("abc123");
//		rs.setResultmessage("医疗材料信息平台");
//		rs.setResult(user);
//		return rs;
//	}
//	/**
//	 * 默认登录界面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/00002", method = RequestMethod.GET)
//	@ResponseBody
//	public RESTResultBean m00002GET() {
//		RESTResultBean rs = new RESTResultBean();
//		List<LoginerBean> users = new ArrayList<LoginerBean>();
//		LoginerBean user = new LoginerBean();
//		users.add(user);
//		user.setToken("abc123");
//		rs.setResultmessage("医疗材料信息平台");
//		rs.setResult(users);
//		return rs;
//	}
}
