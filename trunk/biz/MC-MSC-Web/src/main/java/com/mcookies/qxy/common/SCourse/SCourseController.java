package com.mcookies.qxy.common.SCourse;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 课表内容标签表 */
public class SCourseController extends MyControllerSupport {
	@Resource
	protected SCourseService SCourseService_;

}
