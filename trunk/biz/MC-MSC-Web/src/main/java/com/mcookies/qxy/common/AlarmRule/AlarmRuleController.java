package com.mcookies.qxy.common.AlarmRule;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 报警规则表 */
public class AlarmRuleController extends MyControllerSupport {

	@Resource
	protected AlarmRuleService AlarmRuleService_;

}
