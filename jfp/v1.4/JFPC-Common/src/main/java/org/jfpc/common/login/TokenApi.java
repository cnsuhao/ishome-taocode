package org.jfpc.common.login;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyFrameworkSupport;
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
	LoginService LoginService_;

	private RESTResultBean doCheckToken(String token) {
		RESTResultBean tb = new RESTResultBean();
		boolean result = false;
		if (logger.isDebugEnabled())
			logger.debug("doCheckToken//////////////>>>>>>>>doCheckToken===" + token);
		//缓存校验
		if(getSessionAttribute(CONSTANT_USER_TOKEN)!= null){
			if(token.equals(getSessionAttribute(CONSTANT_USER_TOKEN)))
				result = true;
		}else{//数据库校验
			result = LoginService_.doCheckToken(token);
			if(result){
				setSessionAttribute(CONSTANT_USER_TOKEN,token);
			}
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
	public RESTResultBean m00011011GETREST(@PathVariable String token) {
		return doCheckToken(token);
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011/{token}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00011011POSTREST(@PathVariable String token) {
		return doCheckToken(token);
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m00011011GET(String token) {
		return doCheckToken(token);
	}

	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00011011", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00011011POST(String token) {
		return doCheckToken(token);
	}

}
