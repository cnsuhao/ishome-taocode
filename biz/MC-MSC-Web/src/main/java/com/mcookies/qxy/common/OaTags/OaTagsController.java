package com.mcookies.qxy.common.OaTags;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** OA标签表 */
public class OaTagsController extends MyControllerSupport {

	@Resource
	protected OaTagsService OaTagsService_;

}
