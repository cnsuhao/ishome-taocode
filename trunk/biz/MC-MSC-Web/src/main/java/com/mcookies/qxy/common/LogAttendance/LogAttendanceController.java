package com.mcookies.qxy.common.LogAttendance;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 考勤日志表 */
public class LogAttendanceController extends MyControllerSupport {
	@Resource
	protected LogAttendanceService LogAttendanceService_;

}
