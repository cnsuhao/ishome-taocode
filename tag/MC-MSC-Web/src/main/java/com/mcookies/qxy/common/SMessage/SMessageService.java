package com.mcookies.qxy.common.SMessage;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 短信消息记录表*/
@Service
public class SMessageService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(SMessageService.class);

    public SMessageDao getDao(){
        return getMySqlSession().getMapper(SMessageDao.class);
    }

}
