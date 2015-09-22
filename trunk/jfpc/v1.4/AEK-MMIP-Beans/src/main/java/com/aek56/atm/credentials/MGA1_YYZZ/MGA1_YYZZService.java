package com.aek56.atm.credentials.MGA1_YYZZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商营业执照*/
@Service
public class MGA1_YYZZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA1_YYZZService.class);

    public MGA1_YYZZDao getDao(){
        return getMySqlSession().getMapper(MGA1_YYZZDao.class);
    }

}
