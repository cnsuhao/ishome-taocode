package org.jfpc.platform.CSSTT.controller;

import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.CSSTT.service.CSSTTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
/** TOKEN存放表*/
public class CSSTTController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CSSTTController.class);
	@Resource
	protected CSSTTService CSSTTService_;

	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("CSSTT");
	}

}
