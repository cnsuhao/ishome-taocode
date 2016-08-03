package com.mcookies.qxy.common.SCalendar;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 校历表 */
public class SCalendarController extends MyControllerSupport {
	@Resource
	protected SCalendarService SCalendarService_;

}
