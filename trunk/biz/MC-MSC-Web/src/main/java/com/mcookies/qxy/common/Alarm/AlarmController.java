package com.mcookies.qxy.common.Alarm;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 报警表 */
public class AlarmController extends MyControllerSupport {

	@Resource
	protected AlarmService AlarmService_;

}
