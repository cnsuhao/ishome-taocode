package com.mcookies.qxy.common.UParent;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 家长表 */
public class UParentController extends MyControllerSupport {
	@Resource
	protected UParentService UParentService_;

}
