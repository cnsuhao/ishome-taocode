package com.mcookies.qxy.base.SCourse;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 课表内容标签表*/
public class SCourseController extends MyControllerSupport {

    @Resource
    protected SCourseService SCourseService_;

}
