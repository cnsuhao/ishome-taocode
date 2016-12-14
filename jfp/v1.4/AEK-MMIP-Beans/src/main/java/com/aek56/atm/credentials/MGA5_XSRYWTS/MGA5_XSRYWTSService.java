package com.aek56.atm.credentials.MGA5_XSRYWTS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商销售人员委托书*/
@Service
public class MGA5_XSRYWTSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA5_XSRYWTSService.class);

    public MGA5_XSRYWTSDao getDao(){
        return getMySqlSession().getMapper(MGA5_XSRYWTSDao.class);
    }

}
