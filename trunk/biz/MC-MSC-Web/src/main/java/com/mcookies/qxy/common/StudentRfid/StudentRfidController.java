package com.mcookies.qxy.common.StudentRfid;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生一卡通关联表 */
public class StudentRfidController extends MyControllerSupport {
	@Resource
	protected StudentRfidService StudentRfidService_;

}
