package com.mcookies.qxy.base.DeviceAlarm;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 设备报警表*/
@Service
public class DeviceAlarmService extends MyServiceSupport {

    public DeviceAlarmDao getDao(){
        return getMySqlSession().getMapper(DeviceAlarmDao.class);
    }

}
