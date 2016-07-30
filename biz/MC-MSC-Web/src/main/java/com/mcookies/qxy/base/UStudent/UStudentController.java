package com.mcookies.qxy.base.UStudent;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生表 */
public class UStudentController extends MyControllerSupport {

	@Resource
	protected UStudentService UStudentService_;

}
