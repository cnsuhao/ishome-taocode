package com.mcookies.qxy.common.STerm;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学期设置表 */
public class STermController extends MyControllerSupport {
	@Resource
	protected STermService STermService_;

}
