package org.isotope.jfp.dwc.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.cache.ICacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 客户端配置
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class ClientConfigController {
	@Resource
	protected ICacheService mq;
	static String Cache_KEY = "JOB:LIST";
	static String WEB_KEY = "JOB_LIST";

	/**
	 * 获得运行参数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/09000090", method = RequestMethod.GET)
	public ModelAndView m09000090GET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("DWC/09000090");
		// 【任务名称：任务线程数：间隔秒数】
		model.addObject(WEB_KEY, mq.getObject(Cache_KEY, false));// 任务队列描述
		return model;
	}

	/**
	 * 设置运行参数
	 * 
	 * @param request
	 * @param jobListJson
	 * @return
	 */
	@RequestMapping(value = "/09000090", method = RequestMethod.POST)
	public ModelAndView m09000090POST(HttpServletRequest request, @RequestParam String jobListJson) {
		ModelAndView model = new ModelAndView("DWC/09000090");
		// 【任务名称：任务线程数：间隔秒数】
		mq.putObject(Cache_KEY, jobListJson, 0, false);
		model.addObject(WEB_KEY, mq.getObject(Cache_KEY, false));// 任务队列描述
		return model;
	}
}
