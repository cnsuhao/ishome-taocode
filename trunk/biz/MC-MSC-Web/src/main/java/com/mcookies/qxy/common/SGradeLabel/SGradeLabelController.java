package com.mcookies.qxy.common.SGradeLabel;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 年级标签表 */
public class SGradeLabelController extends MyControllerSupport {
	@Resource
	protected SGradeLabelService SGradeLabelService_;

}
