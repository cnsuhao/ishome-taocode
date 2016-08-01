package com.mcookies.qxy.common.OaExamineInformation;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** OA审批信息表 */
public class OaExamineInformationController extends MyControllerSupport {
	@Resource
	protected OaExamineInformationService OaExamineInformationService_;

}
