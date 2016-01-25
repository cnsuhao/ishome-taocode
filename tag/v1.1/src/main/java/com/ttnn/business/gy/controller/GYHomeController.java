package com.ttnn.business.gy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttnn.business.cs.ISCSConstants;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

/**
 * 公益广告页面
 */
@Controller
@RequestMapping("GY")
public class GYHomeController extends MyControllerSupportImpl implements ISCSConstants{
	
	@RequestMapping(value = "")
	public String index() {
		return "GY/HOME";
	}
	
}
