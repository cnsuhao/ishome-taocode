package com.mcookies.qxy.common.OaExamineResult;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** OA审批结果表 */
public class OaExamineResultController extends MyControllerSupport {

	@Resource
	protected OaExamineResultService OaExamineResultService_;

}
