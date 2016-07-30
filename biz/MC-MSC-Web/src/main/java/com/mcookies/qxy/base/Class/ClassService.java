package com.mcookies.qxy.base.Class;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级表*/
@Service
public class ClassService extends MyServiceSupport {

    public ClassDao getDao(){
        return getMySqlSession().getMapper(ClassDao.class);
    }

}
