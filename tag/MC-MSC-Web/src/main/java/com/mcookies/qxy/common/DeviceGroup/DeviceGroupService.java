package com.mcookies.qxy.common.DeviceGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 设备分组表*/
@Service
public class DeviceGroupService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(DeviceGroupService.class);

    public DeviceGroupDao getDao(){
        return getMySqlSession().getMapper(DeviceGroupDao.class);
    }
    
    public List<DeviceGroupPVO> doSelectDeviceGroup(Map<String,Object> param){
    	return getDao().doSelectDeviceGroup(param);
    }

}
