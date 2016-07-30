package com.mcookies.qxy.base.ClassStudent;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级学生关联表*/
@Service
public class ClassStudentService extends MyServiceSupport {

    public ClassStudentDao getDao(){
        return getMySqlSession().getMapper(ClassStudentDao.class);
    }

}
