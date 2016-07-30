package com.mcookies.qxy.base.SLabelTeacher;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 教工标签关联表 */
public class SLabelTeacherController extends MyControllerSupport {

	@Resource
	protected SLabelTeacherService SLabelTeacherService_;

}
