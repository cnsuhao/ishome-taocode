package com.mcookies.qxy.base.SWorkTime;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 作息时间模板*/
public class SWorkTimeController extends MyControllerSupport {

	@Resource
    protected SWorkTimeService SWorkTimeService_;

}
