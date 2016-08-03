package com.mcookies.qxy.common.PageJurisdiction;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 页面权限管理表 */
public class PageJurisdictionController extends MyControllerSupport {
	@Resource
	protected PageJurisdictionService PageJurisdictionService_;

}
