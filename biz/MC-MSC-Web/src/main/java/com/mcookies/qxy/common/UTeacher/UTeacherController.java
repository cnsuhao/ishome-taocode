package com.mcookies.qxy.common.UTeacher;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 教师表 */
public class UTeacherController extends MyControllerSupport {
	@Resource
	protected UTeacherService UTeacherService_;

}
