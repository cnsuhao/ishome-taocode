package com.aek56.atm.company.MGYS1_JYFW;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商经营范围*/
@Service
public class MGYS1_JYFWService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS1_JYFWService.class);

    public MGYS1_JYFWDao getDao(){
        return getMySqlSession().getMapper(MGYS1_JYFWDao.class);
    }

}
