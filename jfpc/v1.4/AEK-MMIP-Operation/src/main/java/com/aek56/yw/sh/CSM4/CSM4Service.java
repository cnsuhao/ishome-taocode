package com.aek56.yw.sh.CSM4;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品信息(审核)*/
@Service
public class CSM4Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM4Service.class);

    public CSM4Dao getDao(){
        return getMySqlSession().getMapper(CSM4Dao.class);
    }

}
