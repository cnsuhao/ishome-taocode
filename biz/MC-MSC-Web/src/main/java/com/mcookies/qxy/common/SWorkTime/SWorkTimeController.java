package com.mcookies.qxy.common.SWorkTime;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 作息时间模板 */
public class SWorkTimeController extends MyControllerSupport {
	@Resource
	protected SWorkTimeService SWorkTimeService_;

}
