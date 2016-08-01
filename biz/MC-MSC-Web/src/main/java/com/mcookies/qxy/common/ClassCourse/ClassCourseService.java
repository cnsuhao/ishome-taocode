package com.mcookies.qxy.common.ClassCourse;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级课程教师关联表*/
@Service
public class ClassCourseService extends MyServiceSupport {

    public ClassCourseDao getDao(){
        return getMySqlSession().getMapper(ClassCourseDao.class);
    }

}
