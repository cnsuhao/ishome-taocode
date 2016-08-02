package com.mcookies.qxy;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcookies.qxy.common.User.UserDBO;

/**
 * 默认首页
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class HomeIndexController implements ISFrameworkConstants {

	@Resource
	protected ICacheService myCacheService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("DDD", "欢迎访问千校云平台：" + DateHelper.currentTimeMillis2());
		return model;
	}

	@RequestMapping(value = "/ttt", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody // @PathVariable String token,
	public RESTResultBean doProcess(@RequestBody UserDBO user) throws Exception {
		RESTResultBean result = new RESTResultBean();
		try {
			result.setMessage("欢迎访问千校云平台：" + user.getAccount());
		} catch (Exception e) {
			result.setMessage("访问失败");
			result.setCode(ONE);
		}

		return result;
	}
}
