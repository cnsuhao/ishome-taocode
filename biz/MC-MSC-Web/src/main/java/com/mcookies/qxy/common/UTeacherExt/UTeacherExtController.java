package com.mcookies.qxy.common.UTeacherExt;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 教师信息扩展表 */
public class UTeacherExtController extends MyControllerSupport {
	@Resource
	protected UTeacherExtService UTeacherExtService_;

}
