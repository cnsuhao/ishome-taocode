package com.mcookies.qxy.base.STrip;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 行程表*/
public class STripController extends MyControllerSupport {

    @Resource
    protected STripService STripService_;


}
