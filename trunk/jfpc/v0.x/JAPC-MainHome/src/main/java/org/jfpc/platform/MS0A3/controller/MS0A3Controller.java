package org.jfpc.platform.MS0A3.controller;
import javax.annotation.Resource;

import org.jfpc.base.support.MyControllerSupport;
import org.jfpc.base.support.MyModelAndViewSupport;
import org.jfpc.platform.MS0A3.service.MS0A3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MS0A3")
/** */
public class MS0A3Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory.getLogger(MS0A3Controller.class);
    @Resource
    protected MS0A3Service MS0A3Service_;

    @Override
    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("MS0A3");
    }

}
