package com.mcookies.qxy.common.Class;
import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级表*/
@Service
public class ClassService extends MyServiceSupport {

    public ClassDao getDao(){
        return getMySqlSession().getMapper(ClassDao.class);
    }

    public List<ClassPVO> doSelectWorkTime(ClassDBO dbo){
    	return  getDao().doSelectWorkTime(dbo);
    }
}
