package com.mcookies.qxy.common.SDuty;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 岗位设置表 */
public class SDutyController extends MyControllerSupport {
	@Resource
	protected SDutyService SDutyService_;

}
