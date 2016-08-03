package com.mcookies.qxy.common.PagePersonal;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 个人权限页面表 */
public class PagePersonalController extends MyControllerSupport {
	@Resource
	protected PagePersonalService PagePersonalService_;

}
