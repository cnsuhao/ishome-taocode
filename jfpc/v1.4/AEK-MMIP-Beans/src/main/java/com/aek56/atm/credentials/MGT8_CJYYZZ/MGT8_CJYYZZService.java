package com.aek56.atm.credentials.MGT8_CJYYZZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院厂家营业执照*/
@Service
public class MGT8_CJYYZZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT8_CJYYZZService.class);

    public MGT8_CJYYZZDao getDao(){
        return getMySqlSession().getMapper(MGT8_CJYYZZDao.class);
    }

}
