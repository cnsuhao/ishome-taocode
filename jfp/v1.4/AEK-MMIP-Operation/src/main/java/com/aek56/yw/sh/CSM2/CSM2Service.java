package com.aek56.yw.sh.CSM2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院信息(审核)*/
@Service
public class CSM2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM2Service.class);

    public CSM2Dao getDao(){
        return getMySqlSession().getMapper(CSM2Dao.class);
    }

}
