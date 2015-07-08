package org.jfpc.beans.platform.CSSLL;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户登录日志*/
@Service
public class CSSLLService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSSLLService.class);

    public CSSLLDao getDao(){
        return getMySqlSession().getMapper(CSSLLDao.class);
    }

}
