package com.mcookies.qxy.base.SLabelTeacher;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教工标签关联表*/
@Service
public class SLabelTeacherService extends MyServiceSupport {

    public SLabelTeacherDao getDao(){
        return getMySqlSession().getMapper(SLabelTeacherDao.class);
    }

}
