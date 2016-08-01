package com.mcookies.qxy.common.Class;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级表 */
public class ClassController extends MyControllerSupport {
	@Resource
	protected ClassService ClassService_;

}
