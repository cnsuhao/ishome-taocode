package com.aek56.atm.credentials.MGA3_GSSWDJZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商工商税务登记证*/
@Service
public class MGA3_GSSWDJZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA3_GSSWDJZService.class);

    public MGA3_GSSWDJZDao getDao(){
        return getMySqlSession().getMapper(MGA3_GSSWDJZDao.class);
    }

}
