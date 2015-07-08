package org.jfpc.platform.MS0A2.controller;

import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.MS0A2.service.MS0A2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
/** 机能模块定义*/
public class MS0A2Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MS0A2Controller.class);
	@Resource
	protected MS0A2Service MS0A2Service_;

	@Override
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("MS0A2");
	}

}
