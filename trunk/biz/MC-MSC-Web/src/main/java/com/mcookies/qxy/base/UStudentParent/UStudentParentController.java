package com.mcookies.qxy.base.UStudentParent;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生家长关联表 */
public class UStudentParentController extends MyControllerSupport {

	@Resource
	protected UStudentParentService UStudentParentService_;

}
