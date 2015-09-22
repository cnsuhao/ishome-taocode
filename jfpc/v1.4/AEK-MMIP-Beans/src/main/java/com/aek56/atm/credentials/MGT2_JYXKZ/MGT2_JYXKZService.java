package com.aek56.atm.credentials.MGT2_JYXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院经营许可证*/
@Service
public class MGT2_JYXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT2_JYXKZService.class);

    public MGT2_JYXKZDao getDao(){
        return getMySqlSession().getMapper(MGT2_JYXKZDao.class);
    }

}
