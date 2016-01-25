package com.aek56.atm.credentials.MGA6_SHFWCNS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商售后服务承诺书*/
@Service
public class MGA6_SHFWCNSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA6_SHFWCNSService.class);

    public MGA6_SHFWCNSDao getDao(){
        return getMySqlSession().getMapper(MGA6_SHFWCNSDao.class);
    }

}
