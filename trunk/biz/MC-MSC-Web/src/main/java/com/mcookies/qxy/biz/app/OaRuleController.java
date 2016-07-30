package com.mcookies.qxy.biz.app;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

import com.mcookies.qxy.base.OaRule.OaRuleService;

@Controller
/** OA规则表 */
public class OaRuleController extends MyControllerSupport {

	@Resource
	protected OaRuleService OaRuleService_;

	
	
}
