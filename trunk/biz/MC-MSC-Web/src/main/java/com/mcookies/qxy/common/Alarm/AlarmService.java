package com.mcookies.qxy.common.Alarm;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 报警表*/
@Service
public class AlarmService extends MyServiceSupport {

    public AlarmDao getDao(){
        return getMySqlSession().getMapper(AlarmDao.class);
    }

}
