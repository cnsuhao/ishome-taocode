package com.aek56.atm.credentials.MGTB_CJHCSPRZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院厂家耗材商品3C认证*/
@Service
public class MGTB_CJHCSPRZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGTB_CJHCSPRZService.class);

    public MGTB_CJHCSPRZDao getDao(){
        return getMySqlSession().getMapper(MGTB_CJHCSPRZDao.class);
    }

}
