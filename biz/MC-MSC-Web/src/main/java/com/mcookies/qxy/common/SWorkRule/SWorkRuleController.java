package com.mcookies.qxy.common.SWorkRule;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 作息时间规则表 */
public class SWorkRuleController extends MyControllerSupport {
	@Resource
	protected SWorkRuleService SWorkRuleService_;

}
