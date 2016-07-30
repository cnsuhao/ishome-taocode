package com.mcookies.qxy.base.StudentRfid;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 学生一卡通关联表*/
public class StudentRfidController extends MyControllerSupport {

    @Resource
    protected StudentRfidService StudentRfidService_;

}
