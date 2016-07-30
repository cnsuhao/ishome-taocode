package com.mcookies.qxy.base.SDutyScheduling;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位人员排班表*/
@Service
public class SDutySchedulingService extends MyServiceSupport {

    public SDutySchedulingDao getDao(){
        return getMySqlSession().getMapper(SDutySchedulingDao.class);
    }

}
