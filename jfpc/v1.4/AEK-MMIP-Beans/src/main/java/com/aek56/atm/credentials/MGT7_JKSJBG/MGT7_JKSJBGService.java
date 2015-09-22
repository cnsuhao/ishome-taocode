package com.aek56.atm.credentials.MGT7_JKSJBG;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院进口商检报告*/
@Service
public class MGT7_JKSJBGService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT7_JKSJBGService.class);

    public MGT7_JKSJBGDao getDao(){
        return getMySqlSession().getMapper(MGT7_JKSJBGDao.class);
    }

}
