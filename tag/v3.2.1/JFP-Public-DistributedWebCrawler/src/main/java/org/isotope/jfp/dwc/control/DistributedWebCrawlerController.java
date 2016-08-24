package org.isotope.jfp.dwc.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分布式网络爬虫服务端
 * Distributed Web Crawler
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DistributedWebCrawlerController {
	
	@RequestMapping(value = "/0900", method = RequestMethod.GET)
	public ModelAndView loadHttpProxys(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("DWC/index");
		return model;
	}

}
