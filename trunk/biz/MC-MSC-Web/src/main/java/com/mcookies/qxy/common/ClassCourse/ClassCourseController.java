package com.mcookies.qxy.common.ClassCourse;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级课程教师关联表 */
public class ClassCourseController extends MyControllerSupport {

	@Resource
	protected ClassCourseService ClassCourseService_;

}
