package org.isotope.jfp.dwc.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.cache.ICacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网页结果保存服务端 Distributed Web Crawler
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class WebCrawlerServerController {
	@Resource
	protected ICacheService mq;
	static String WEB_KEY = "HTML_RESULT";

	@RequestMapping(value = "/09001000", method = RequestMethod.POST)
	public ModelAndView capUpload(HttpServletRequest request, @RequestParam MultipartFile file) {
		ModelAndView model = new ModelAndView("DWC/09001000");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				System.out.println(new String(bytes, "UTF-8"));
				model.addObject(WEB_KEY, "OK");
				//model.addObject("FILE_VALUE", new String(bytes, "UTF-8"));
			} catch (Exception e) {
				model.addObject(WEB_KEY, "FAIL");
			}
		} else {
			model.addObject(WEB_KEY, "FAIL");
		}
		return model;
	}

}
