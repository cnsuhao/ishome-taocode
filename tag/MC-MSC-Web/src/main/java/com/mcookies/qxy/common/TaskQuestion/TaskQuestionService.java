package com.mcookies.qxy.common.TaskQuestion;
import org.springframework.stereotype.Service;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 作业题目表*/
@Service
public class TaskQuestionService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(TaskQuestionService.class);

    public TaskQuestionDao getDao(){
        return getMySqlSession().getMapper(TaskQuestionDao.class);
    }

}
