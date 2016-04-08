package org.isotope.jfp.dwc.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.biz.job.AJobServiceSupport;
import org.isotope.jfp.dwc.config.JobConfig;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.redis.master.JedisMasterUtil;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

/**
 * 分布式网络爬虫服务端 
 * Distributed Web Crawler
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DistributedServerController {
	@Resource
	protected ICacheService myCacheService;

	@Resource
	protected JobConfig config;

	@RequestMapping(value = "/09001010", method = RequestMethod.GET)
	public ModelAndView m09001010GET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("DWC/09001010");
		model.addObject("TASK_TYPE", "");// 任务运行状态
		model.addObject("FTP_PATH", "");// 上传路径
		model.addObject("TASK_INVEL", "");// 任务间隔时间
		model.addObject("HTTP_URL", "");// 抓取地址
		model.addObject("FILE_NAME", "");// 回传文件名称
		return model;
	}

	public static final int NUM_SIZE = 678;

	@RequestMapping(value = "/09001010/{key}", method = RequestMethod.GET)
	public ModelAndView m09001010GETKey(@PathVariable String key) {
		ModelAndView model = new ModelAndView("DWC/09001010");

		String job = config.getJob(key);
		if (EmptyHelper.isEmpty(job)) {
			return model;
		}
		AJobServiceSupport jobService = BeanFactoryHelper.getBean(job);
		// 安全限定
		if (key.equals(jobService.getJobKey())) {
			jobService.setMyCacheService(myCacheService);
			
		}

		return model;
	}

	@Resource
	protected JedisMasterUtil jedisMasterUtil;

	public Jedis getJedis() {
		return jedisMasterUtil.getJedis();
	}

	@RequestMapping(value = "/09001010/{key}", method = RequestMethod.POST)
	public ModelAndView m09001010POST(@PathVariable String key, String TASK_TYPE,String FTP_PATH, String TASK_INVEL, int MAX_NUM) {
		ModelAndView model = new ModelAndView("DWC/09001010");
		model.addObject("TASK_TYPE", myCacheService.putObject("FTP:PATH:" + key, TASK_TYPE, 0, false));// 任务运行状态
		model.addObject("FTP_PATH", myCacheService.putObject("FTP:PATH:" + key, FTP_PATH, 0, false));
		model.addObject("TASK_INVEL", myCacheService.putObject("TASK:INVEL:" + key, TASK_INVEL, 0, false));

		Jedis jedis = jedisMasterUtil.getJedis();

		for (int i = 0; i < MAX_NUM; i = i + NUM_SIZE)
			jedis.rpush("PAGE:NUM:" + key, "" + (i + 1));

		model.addObject("HTTP_URL", jedis.llen("PAGE:NUM:" + key));

		model.addObject("FILE_NAME", "" + key);
		return model;
	}

}
