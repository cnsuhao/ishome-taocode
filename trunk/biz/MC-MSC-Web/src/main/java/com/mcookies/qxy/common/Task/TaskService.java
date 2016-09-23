package com.mcookies.qxy.common.Task;

import org.springframework.stereotype.Service;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 作业表*/
@Service
public class TaskService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskDao getDao(){
        return getMySqlSession().getMapper(TaskDao.class);
    }

}
