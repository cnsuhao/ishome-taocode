package org.jfpc.platform.CSSLL.service;

import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.CSSLL.dao.CSSLLDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户登录日志*/
@Service
public class CSSLLService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSSLLService.class);

    @Override
    public CSSLLDao getDao(){
        return getMySqlSession().getMapper(CSSLLDao.class);
    }

}
