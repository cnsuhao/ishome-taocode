package com.aek56.atm.credentials.CGTZ_TSJL;

import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商推送记录表*/
@Service
public class CGTZ_TSJLService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CGTZ_TSJLService.class);

    public CGTZ_TSJLDao getDao(){
        return getMySqlSession().getMapper(CGTZ_TSJLDao.class);
    }

}
