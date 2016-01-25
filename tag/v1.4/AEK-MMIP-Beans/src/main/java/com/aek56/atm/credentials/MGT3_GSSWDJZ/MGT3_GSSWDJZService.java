package com.aek56.atm.credentials.MGT3_GSSWDJZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院工商税务登记证*/
@Service
public class MGT3_GSSWDJZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT3_GSSWDJZService.class);

    public MGT3_GSSWDJZDao getDao(){
        return getMySqlSession().getMapper(MGT3_GSSWDJZDao.class);
    }

}
