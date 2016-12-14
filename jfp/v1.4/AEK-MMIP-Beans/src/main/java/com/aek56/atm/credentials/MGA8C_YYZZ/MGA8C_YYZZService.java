package com.aek56.atm.credentials.MGA8C_YYZZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂家营业执照*/
@Service
public class MGA8C_YYZZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA8C_YYZZService.class);

    public MGA8C_YYZZDao getDao(){
        return getMySqlSession().getMapper(MGA8C_YYZZDao.class);
    }

}
