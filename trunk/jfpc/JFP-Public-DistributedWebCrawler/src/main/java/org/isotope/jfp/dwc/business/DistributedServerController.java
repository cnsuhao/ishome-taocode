package org.isotope.jfp.dwc.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.business.job.AJobServiceSupport;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分布式网络爬虫服务端 Distributed Web Crawler
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DistributedServerController {
	@Resource
	protected ICacheService mq;

	@Resource
	protected JobConfig config;

	@RequestMapping(value = "/09001010", method = RequestMethod.GET)
	public ModelAndView capInit(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("DWC/09001010");
		model.addObject("TASK:TYPE", "");// 任务运行状态
		model.addObject("FTP:PATH", "");// 上传路径
		model.addObject("TASK:INVEL", "");// 任务间隔时间
		model.addObject("HTTP:URL", "");// 抓取地址
		model.addObject("FILE:NAME", "");// 回传文件名称
		return model;
	}

	@RequestMapping(value = "/09001010/{key}", method = RequestMethod.GET)
	public ModelAndView loadConfig(@PathVariable String key) {
		ModelAndView model = new ModelAndView("DWC/09001010");

		String job = config.getJob(key);
		if (EmptyHelper.isEmpty(job)) {
			return model;
		}
		AJobServiceSupport jobService = BeanFactoryHelper.getBean(job);
		// 安全限定
		if (key.equals(jobService.getJobKey())) {
			jobService.loadJobCongig(model);
		}

		return model;
	}

}
