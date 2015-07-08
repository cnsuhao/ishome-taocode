package org.jfpc.platform.MS0A4.controller;
import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.MS0A4.service.MS0A4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MS0A4")
/** 页面功能按钮定义*/
public class MS0A4Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory.getLogger(MS0A4Controller.class);
    @Resource
    protected MS0A4Service MS0A4Service_;

    @Override
    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("MS0A4");
    }

}
