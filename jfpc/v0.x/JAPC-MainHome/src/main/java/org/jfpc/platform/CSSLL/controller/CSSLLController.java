package org.jfpc.platform.CSSLL.controller;
import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.CSSLL.service.CSSLLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("CSSLL")
/** 用户登录日志*/
public class CSSLLController extends MyControllerSupport {
    private static final Logger logger = LoggerFactory.getLogger(CSSLLController.class);
    @Resource
    protected CSSLLService CSSLLService_;

    @Override
    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("CSSLL");
    }

}
