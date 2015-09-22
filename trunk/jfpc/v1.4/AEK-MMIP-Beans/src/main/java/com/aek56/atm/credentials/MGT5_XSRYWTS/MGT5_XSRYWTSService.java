package com.aek56.atm.credentials.MGT5_XSRYWTS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院销售人员委托书*/
@Service
public class MGT5_XSRYWTSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT5_XSRYWTSService.class);

    public MGT5_XSRYWTSDao getDao(){
        return getMySqlSession().getMapper(MGT5_XSRYWTSDao.class);
    }

}
