package com.mcookies.qxy.common.LogSecurity;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 安全日志表 */
public class LogSecurityController extends MyControllerSupport {

	@Resource
	protected LogSecurityService LogSecurityService_;

}
