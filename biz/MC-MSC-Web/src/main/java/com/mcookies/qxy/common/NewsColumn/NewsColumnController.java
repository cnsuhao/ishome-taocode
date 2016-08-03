package com.mcookies.qxy.common.NewsColumn;
import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 栏目表 */
public class NewsColumnController extends MyControllerSupport {
	@Resource
	protected NewsColumnService NewsColumnService_;

}
