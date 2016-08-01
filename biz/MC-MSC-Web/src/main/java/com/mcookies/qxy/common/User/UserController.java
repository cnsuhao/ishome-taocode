package com.mcookies.qxy.common.User;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 用户表 */
public class UserController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;

}
