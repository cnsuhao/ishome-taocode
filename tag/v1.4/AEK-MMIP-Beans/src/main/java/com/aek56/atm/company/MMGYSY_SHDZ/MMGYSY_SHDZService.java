package com.aek56.atm.company.MMGYSY_SHDZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商收货地址*/
@Service
public class MMGYSY_SHDZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MMGYSY_SHDZService.class);

    public MMGYSY_SHDZDao getDao(){
        return getMySqlSession().getMapper(MMGYSY_SHDZDao.class);
    }

}
