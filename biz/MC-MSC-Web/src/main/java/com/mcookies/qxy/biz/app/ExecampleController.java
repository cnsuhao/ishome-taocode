package com.mcookies.qxy.biz.app;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

import com.mcookies.qxy.common.OaRule.OaRuleService;

@Controller
/** OA规则表 */
public class ExecampleController extends MyControllerSupport {

	@Resource
	protected OaRuleService OaRuleService_;

	
	
}
