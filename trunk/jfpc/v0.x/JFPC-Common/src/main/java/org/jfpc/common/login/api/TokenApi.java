package org.jfpc.common.login.api;

import javax.annotation.Resource;

import org.jfpc.base.bean.LoginerBean;
import org.jfpc.base.bean.RESTResultBean;
import org.jfpc.base.support.MyFrameworkSupport;
import org.jfpc.common.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
public class TokenApi extends MyFrameworkSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(TokenApi.class);

	@Resource
	protected LoginService LoginService_;

	private RESTResultBean doCheckToken(String token) {
		logger.debug(token);
		RESTResultBean tb = new RESTResultBean();
		LoginerBean loginer = new LoginerBean();
		loginer.setToken(token);
		boolean result = false;
		try {
			//LoginService_.doCheckToken(loginer);
		} catch (Exception e) {
		}
		if (result) {
			tb.setCode("0");
			tb.setMessage("验证成功");
		} else {
			tb.setCode("1");
			tb.setMessage("验证失败");
		}
		return tb;
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011/{token}", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m00111100GET(@PathVariable String token) {
		return doCheckToken(token);
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m00111100GET() {
		return doCheckToken(super.getToken());
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011/{token}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00111100POST(@PathVariable String token) {
		return doCheckToken(token);
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00111100POST() {
		return doCheckToken(super.getToken());
	}

}
