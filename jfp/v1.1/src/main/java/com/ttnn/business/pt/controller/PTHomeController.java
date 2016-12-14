package com.ttnn.business.pt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ttnn.business.cs.ISCSConstants;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;

/**
 * 贷付宝登录页面
 */
@Controller
@SessionAttributes("usertoken")
@RequestMapping("PTHOME")
public class PTHomeController extends MyControllerSupportImpl implements ISCSConstants{
	
	@RequestMapping(value = "")
	public String index() {
		
		setSessionAttribute("usertoken",  "TTNN_PT");
		return "PT/LOGIN";
	}
	
}
