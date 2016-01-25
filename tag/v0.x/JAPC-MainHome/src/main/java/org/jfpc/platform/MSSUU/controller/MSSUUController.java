package org.jfpc.platform.MSSUU.controller;
import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.MSSUU.service.MSSUUService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MSSUU")
/** 允许登录用户*/
public class MSSUUController extends MyControllerSupport {
    private static final Logger logger = LoggerFactory.getLogger(MSSUUController.class);
    @Resource
    protected MSSUUService MSSUUService_;

    @Override
    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("MSSUU");
    }

}
