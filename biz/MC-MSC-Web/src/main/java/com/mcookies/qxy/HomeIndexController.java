package com.mcookies.qxy;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.BusinessTokenBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		model.addObject("DDD", "欢迎来到蛋仔的世界：" + DateHelper.currentTimeMillis2());
		return model;
	}

	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean doProcess(@PathVariable String token, String paramValue) throws Exception {
		RESTResultBean result = new RESTResultBean();
		BusinessTokenBean btb = result.getToken(token);
		try {
//			AGameBussinessService asgbs = BeanFactoryHelper.getBean(btb.getBizName());
//			asgbs.setMyCacheService(myCacheService);
//			asgbs.setParamValue(paramValue);
//			asgbs.setToken(btb);
//			asgbs.doProcess();
//			// 设定返回值
//			result.setCode(asgbs.getReturnCode());
//			result.setMessage(asgbs.getReturnMessage());
//			result.setResult(asgbs.getReturnObject());
//			result.setToken(asgbs.getToken());
		} catch (Exception e) {
			result.setMessage("蛋仔的世界：" + MESSAGE_ERROR_SYNC);
			result.setCode(ONE);
			btb.chageToken();
			result.setToken(BusinessTokenBean.getBizToken(btb));
		}

		return result;
	}
}
