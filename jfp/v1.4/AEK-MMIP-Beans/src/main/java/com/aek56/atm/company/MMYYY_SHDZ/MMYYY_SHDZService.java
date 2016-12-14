package com.aek56.atm.company.MMYYY_SHDZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院收货地址*/
@Service
public class MMYYY_SHDZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MMYYY_SHDZService.class);

    public MMYYY_SHDZDao getDao(){
        return getMySqlSession().getMapper(MMYYY_SHDZDao.class);
    }

}
