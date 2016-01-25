package com.aek56.atm.company.MQC_PZZX;

import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户配置中心*/
@Service
public class MQC_PZZXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MQC_PZZXService.class);

    public MQC_PZZXDao getDao(){
        return getMySqlSession().getMapper(MQC_PZZXDao.class);
    }

}
