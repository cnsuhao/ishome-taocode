package com.aek56.yw.sh.CSMA;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 品牌信息(审核)*/
@Service
public class CSMAService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSMAService.class);

    public CSMADao getDao(){
        return getMySqlSession().getMapper(CSMADao.class);
    }

}
