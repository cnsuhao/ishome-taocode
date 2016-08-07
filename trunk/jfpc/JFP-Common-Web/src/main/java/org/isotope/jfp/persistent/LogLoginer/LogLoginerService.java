package org.isotope.jfp.persistent.LogLoginer;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户登录日志*/
@Service
public class LogLoginerService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(LogLoginerService.class);

    public LogLoginerDao getDao(){
        return getMySqlSession().getMapper(LogLoginerDao.class);
    }

}
