package com.aek56.atm.credentials.MGA2_JYXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商经营许可证*/
@Service
public class MGA2_JYXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA2_JYXKZService.class);

    public MGA2_JYXKZDao getDao(){
        return getMySqlSession().getMapper(MGA2_JYXKZDao.class);
    }

}
