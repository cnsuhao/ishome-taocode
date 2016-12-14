package com.aek56.atm.master.MD9_CPCPXXX;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品产品线信息*/
@Service
public class MD9_CPCPXXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD9_CPCPXXXService.class);

    public MD9_CPCPXXXDao getDao(){
        return getMySqlSession().getMapper(MD9_CPCPXXXDao.class);
    }

}
