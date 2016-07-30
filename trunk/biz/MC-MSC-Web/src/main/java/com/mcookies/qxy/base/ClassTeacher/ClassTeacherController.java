package com.mcookies.qxy.base.ClassTeacher;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 班级教师关联表*/
public class ClassTeacherController extends MyControllerSupport {
    @Resource
    protected ClassTeacherService ClassTeacherService_;

}
