package com.mcookies.qxy.common.ClassTeacher;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级教师关联表 */
public class ClassTeacherController extends MyControllerSupport {
	@Resource
	protected ClassTeacherService ClassTeacherService_;

}
