package com.aek56.atm.credentials.MGT9_CJSCXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院厂家生产许可证*/
@Service
public class MGT9_CJSCXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGT9_CJSCXKZService.class);

    public MGT9_CJSCXKZDao getDao(){
        return getMySqlSession().getMapper(MGT9_CJSCXKZDao.class);
    }

}
