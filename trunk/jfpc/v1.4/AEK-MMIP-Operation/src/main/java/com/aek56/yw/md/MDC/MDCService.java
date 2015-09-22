package com.aek56.yw.md.MDC;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品信息(审核)*/
@Service
public class MDCService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDCService.class);

    public MDCDao getDao(){
        return getMySqlSession().getMapper(MDCDao.class);
    }

}
