package org.jfpc.platform.MS0A1.controller;

import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.MS0A1.service.MS0A1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
/** 系统菜单定义*/
public class MS0A1Controller extends MyControllerSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MS0A1Controller.class);

	@Resource
	protected MS0A1Service MS0A1Service_;

	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("MS0A1");
	}

}
