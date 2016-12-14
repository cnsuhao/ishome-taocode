package com.aek56.atm.company.MMCSY_SHDZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂商收货地址*/
@Service
public class MMCSY_SHDZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MMCSY_SHDZService.class);

    public MMCSY_SHDZDao getDao(){
        return getMySqlSession().getMapper(MMCSY_SHDZDao.class);
    }

}
