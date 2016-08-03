package com.mcookies.qxy.common.SLabelTeacher;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 教工标签关联表 */
public class SLabelTeacherController extends MyControllerSupport {
	@Resource
	protected SLabelTeacherService SLabelTeacherService_;

}
