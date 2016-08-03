package com.mcookies.qxy.common.ClassAlarm;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级报警规则关联表 */
public class ClassAlarmController extends MyControllerSupport {

	@Resource
	protected ClassAlarmService ClassAlarmService_;

}
