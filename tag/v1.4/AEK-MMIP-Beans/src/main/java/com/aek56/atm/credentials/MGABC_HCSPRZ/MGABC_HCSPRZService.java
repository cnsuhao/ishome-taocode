package com.aek56.atm.credentials.MGABC_HCSPRZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂家耗材商品3C认证*/
@Service
public class MGABC_HCSPRZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGABC_HCSPRZService.class);

    public MGABC_HCSPRZDao getDao(){
        return getMySqlSession().getMapper(MGABC_HCSPRZDao.class);
    }

}
