package com.mcookies.qxy.base.LogSecurity;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 安全日志表*/
@Service
public class LogSecurityService extends MyServiceSupport {

    public LogSecurityDao getDao(){
        return getMySqlSession().getMapper(LogSecurityDao.class);
    }

}
