package com.mcookies.qxy.base.ClassAlarm;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级报警规则关联表*/
@Service
public class ClassAlarmService extends MyServiceSupport {

    public ClassAlarmDao getDao(){
        return getMySqlSession().getMapper(ClassAlarmDao.class);
    }

}
