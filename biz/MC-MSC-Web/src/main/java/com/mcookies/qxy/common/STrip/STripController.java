package com.mcookies.qxy.common.STrip;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 行程表 */
public class STripController extends MyControllerSupport {
	@Resource
	protected STripService STripService_;

}
