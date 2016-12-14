package com.aek56.yw.sh.CSM1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院信息(审核)*/
@Service
public class CSM1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM1Service.class);

    public CSM1Dao getDao(){
        return getMySqlSession().getMapper(CSM1Dao.class);
    }

}
