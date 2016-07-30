package com.mcookies.qxy.base.UStudent;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生表*/
@Service
public class UStudentService extends MyServiceSupport {
    
    public UStudentDao getDao(){
        return getMySqlSession().getMapper(UStudentDao.class);
    }

}
