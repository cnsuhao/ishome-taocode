package com.mcookies.qxy.common.Task;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.support.MyModelAndViewSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 作业表*/
public class TaskController extends MyControllerSupport {
    
    @Resource
    protected TaskService TaskService_;

    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("task");
    }

}
