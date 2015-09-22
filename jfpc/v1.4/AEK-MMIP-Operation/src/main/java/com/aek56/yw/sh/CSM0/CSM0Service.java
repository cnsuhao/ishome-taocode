package com.aek56.yw.sh.CSM0;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商信息(审核)*/
@Service
public class CSM0Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM0Service.class);

    public CSM0Dao getDao(){
        return getMySqlSession().getMapper(CSM0Dao.class);
    }

}
