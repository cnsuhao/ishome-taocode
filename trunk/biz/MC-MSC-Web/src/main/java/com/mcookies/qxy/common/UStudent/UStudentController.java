package com.mcookies.qxy.common.UStudent;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生表 */
public class UStudentController extends MyControllerSupport {
	@Resource
	protected UStudentService UStudentService_;

}
