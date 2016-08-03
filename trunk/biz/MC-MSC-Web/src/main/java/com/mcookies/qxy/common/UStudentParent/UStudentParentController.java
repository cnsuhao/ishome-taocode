package com.mcookies.qxy.common.UStudentParent;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生家长关联表 */
public class UStudentParentController extends MyControllerSupport {
	@Resource
	protected UStudentParentService UStudentParentService_;

}
