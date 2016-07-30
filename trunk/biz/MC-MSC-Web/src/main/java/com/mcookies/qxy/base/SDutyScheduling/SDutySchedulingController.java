package com.mcookies.qxy.base.SDutyScheduling;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 岗位人员排班表 */
public class SDutySchedulingController extends MyControllerSupport {

	@Resource
	protected SDutySchedulingService SDutySchedulingService_;

}
