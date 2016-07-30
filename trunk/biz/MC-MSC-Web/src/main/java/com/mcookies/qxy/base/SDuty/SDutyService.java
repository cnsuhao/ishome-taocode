package com.mcookies.qxy.base.SDuty;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位设置表*/
@Service
public class SDutyService extends MyServiceSupport {
 
    public SDutyDao getDao(){
        return getMySqlSession().getMapper(SDutyDao.class);
    }

}
