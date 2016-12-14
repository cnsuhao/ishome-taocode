package com.aek56.atm.credentials.MGA4_JXSQS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商经销授权书*/
@Service
public class MGA4_JXSQSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA4_JXSQSService.class);

    public MGA4_JXSQSDao getDao(){
        return getMySqlSession().getMapper(MGA4_JXSQSDao.class);
    }

}
