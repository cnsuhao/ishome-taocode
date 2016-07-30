package com.mcookies.qxy.base.LogAttendance;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 考勤日志表*/
@Service
public class LogAttendanceService extends MyServiceSupport {

    public LogAttendanceDao getDao(){
        return getMySqlSession().getMapper(LogAttendanceDao.class);
    }

}
