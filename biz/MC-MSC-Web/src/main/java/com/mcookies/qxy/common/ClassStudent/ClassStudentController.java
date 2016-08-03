package com.mcookies.qxy.common.ClassStudent;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级学生关联表 */
public class ClassStudentController extends MyControllerSupport {

	@Resource
	protected ClassStudentService ClassStudentService_;

}
