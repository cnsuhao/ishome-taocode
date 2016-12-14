package com.aek56.atm.credentials.MGT1_YYZZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院营业执照*/
@Service
public class MGT1_YYZZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT1_YYZZService.class);

    public MGT1_YYZZDao getDao(){
        return getMySqlSession().getMapper(MGT1_YYZZDao.class);
    }

}
