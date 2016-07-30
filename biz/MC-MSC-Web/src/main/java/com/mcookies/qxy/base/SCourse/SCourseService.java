package com.mcookies.qxy.base.SCourse;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 课表内容标签表*/
@Service
public class SCourseService extends MyServiceSupport {

    public SCourseDao getDao(){
        return getMySqlSession().getMapper(SCourseDao.class);
    }

}
