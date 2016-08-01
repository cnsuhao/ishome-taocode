package com.mcookies.qxy.common.ClassAlarm;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 班级报警规则关联表*/
@Service
public class ClassAlarmService extends MyServiceSupport {

    public ClassAlarmDao getDao(){
        return getMySqlSession().getMapper(ClassAlarmDao.class);
    }

}
