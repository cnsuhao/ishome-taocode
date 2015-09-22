package com.aek56.atm.credentials.MGT6_SHFWCNS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院售后服务承诺书*/
@Service
public class MGT6_SHFWCNSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT6_SHFWCNSService.class);

    public MGT6_SHFWCNSDao getDao(){
        return getMySqlSession().getMapper(MGT6_SHFWCNSDao.class);
    }

}
