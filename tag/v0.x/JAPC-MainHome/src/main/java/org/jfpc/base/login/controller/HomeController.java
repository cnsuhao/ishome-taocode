package org.jfpc.base.login.controller;

import org.jfpc.base.bean.LoginerBean;
import org.jfpc.base.bean.RESTResultBean;
import org.jfpc.base.support.MyControllerSupport;
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
	@RequestMapping(value = "/80011000", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean homePOST(LoginerBean loginer) {
		RESTResultBean rs = new RESTResultBean();
		//rs.setToken(loginer.getToken());
		rs.setMessage("中国医疗材料信息平台");
		rs.setResult("Token 验证成功");
		return rs;
	}
	/**
	 * 登录用户主页面
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/80011000", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean homePOST1(LoginerBean loginer) {
		RESTResultBean rs = new RESTResultBean();
		//rs.setToken(loginer.getToken());
		rs.setMessage("中国医疗材料信息平台");
		rs.setResult("Token 验证成功");
		return rs;
	}
	
}
