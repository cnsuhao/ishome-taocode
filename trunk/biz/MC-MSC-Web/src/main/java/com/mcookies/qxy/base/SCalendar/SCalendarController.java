package com.mcookies.qxy.base.SCalendar;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 校历表 */
public class SCalendarController extends MyControllerSupport {

	@Resource
	protected SCalendarService SCalendarService_;

}
