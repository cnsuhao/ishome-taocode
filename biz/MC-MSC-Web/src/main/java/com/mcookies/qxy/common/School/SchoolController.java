package com.mcookies.qxy.common.School;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学校表 */
public class SchoolController extends MyControllerSupport {
	@Resource
	protected SchoolService SchoolService_;

}
