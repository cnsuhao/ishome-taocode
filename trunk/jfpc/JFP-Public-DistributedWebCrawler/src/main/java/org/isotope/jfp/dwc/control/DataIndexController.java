package org.isotope.jfp.dwc.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.search.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分布式网络爬虫服务端
 * Distributed Web Crawler
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DataIndexController {
	@Resource
	TableService table;
	@RequestMapping(value = "/DIC", method = RequestMethod.GET)
	public ModelAndView loadHttpProxys(HttpServletRequest request,@RequestParam String T) throws Exception {
		ModelAndView model = new ModelAndView("DWC/index");
		table.creatIndexByTable(T);
		return model;
	}

}
