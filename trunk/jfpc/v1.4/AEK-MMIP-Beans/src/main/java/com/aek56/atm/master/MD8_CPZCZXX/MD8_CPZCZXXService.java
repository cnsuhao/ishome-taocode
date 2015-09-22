package com.aek56.atm.master.MD8_CPZCZXX;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品注册证信息*/
@Service
public class MD8_CPZCZXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD8_CPZCZXXService.class);

    public MD8_CPZCZXXDao getDao(){
        return getMySqlSession().getMapper(MD8_CPZCZXXDao.class);
    }

}
