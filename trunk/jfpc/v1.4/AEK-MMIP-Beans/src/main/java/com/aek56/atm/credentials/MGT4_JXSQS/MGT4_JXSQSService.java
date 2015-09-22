package com.aek56.atm.credentials.MGT4_JXSQS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院经销授权书*/
@Service
public class MGT4_JXSQSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT4_JXSQSService.class);

    public MGT4_JXSQSDao getDao(){
        return getMySqlSession().getMapper(MGT4_JXSQSDao.class);
    }

}
