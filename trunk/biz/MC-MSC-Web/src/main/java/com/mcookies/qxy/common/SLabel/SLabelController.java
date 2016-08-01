package com.mcookies.qxy.common.SLabel;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 教工标签表 */
public class SLabelController extends MyControllerSupport {
	@Resource
	protected SLabelService SLabelService_;

}
