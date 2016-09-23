package com.mcookies.qxy.common.TaskQuestion;
import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
/** 作业题目表*/
public class TaskQuestionController extends MyControllerSupport {
    
    @Resource
    protected TaskQuestionService TaskQuestionService_;

    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("task_question");
    }

}
