package com.mcookies.qxy.common.Class;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 班级表*/
@Service
public class ClassService extends MyServiceSupport {

    public ClassDao getDao(){
        return getMySqlSession().getMapper(ClassDao.class);
    }

}
