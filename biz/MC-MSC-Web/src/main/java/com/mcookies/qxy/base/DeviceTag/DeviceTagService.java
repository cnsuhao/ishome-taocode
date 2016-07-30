package com.mcookies.qxy.base.DeviceTag;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 设备标签表*/
@Service
public class DeviceTagService extends MyServiceSupport {

    public DeviceTagDao getDao(){
        return getMySqlSession().getMapper(DeviceTagDao.class);
    }

}
