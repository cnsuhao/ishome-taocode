package com.mcookies.qxy.base.School;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学校表*/
@Service
public class SchoolService extends MyServiceSupport {

    public SchoolDao getDao(){
        return getMySqlSession().getMapper(SchoolDao.class);
    }

}
