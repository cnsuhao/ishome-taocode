package com.mcookies.qxy.common.OaRule;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** OA规则表 */
public class OaRuleController extends MyControllerSupport {
	@Resource
	protected OaRuleService OaRuleService_;

}
