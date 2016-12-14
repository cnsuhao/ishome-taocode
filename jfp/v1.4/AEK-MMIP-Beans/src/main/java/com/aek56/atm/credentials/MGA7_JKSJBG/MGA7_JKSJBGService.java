package com.aek56.atm.credentials.MGA7_JKSJBG;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商进口商检报告*/
@Service
public class MGA7_JKSJBGService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA7_JKSJBGService.class);

    public MGA7_JKSJBGDao getDao(){
        return getMySqlSession().getMapper(MGA7_JKSJBGDao.class);
    }

}
