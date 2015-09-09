package com.hundsun.med.beans.HospitalCloudAccessMonitor;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院云平台对接监控表*/
@Service
public class HospitalCloudAccessMonitorService extends MyServiceSupport {
    private Logger logger = LoggerFactory.getLogger(HospitalCloudAccessMonitorService.class);

    public HospitalCloudAccessMonitorDao getDao(){
        return getMySqlSession().getMapper(HospitalCloudAccessMonitorDao.class);
    }

}
