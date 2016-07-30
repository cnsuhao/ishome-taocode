package com.mcookies.qxy.base.ClassAlarm;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级报警规则关联表*/
public class ClassAlarmController extends MyControllerSupport {
    @Resource
    protected ClassAlarmService ClassAlarmService_;

}
