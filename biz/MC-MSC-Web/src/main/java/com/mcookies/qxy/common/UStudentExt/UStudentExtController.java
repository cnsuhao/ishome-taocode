package com.mcookies.qxy.common.UStudentExt;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生信息扩展表 */
public class UStudentExtController extends MyControllerSupport {
	@Resource
	protected UStudentExtService UStudentExtService_;

}
