package com.mcookies.qxy.base.Alarm;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 报警表*/
@Service
public class AlarmService extends MyServiceSupport {

    public AlarmDao getDao(){
        return getMySqlSession().getMapper(AlarmDao.class);
    }

}
