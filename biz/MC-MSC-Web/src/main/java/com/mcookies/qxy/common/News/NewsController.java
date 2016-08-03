package com.mcookies.qxy.common.News;
import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 新闻表 */
public class NewsController extends MyControllerSupport {
	@Resource
	protected NewsService NewsService_;

}
