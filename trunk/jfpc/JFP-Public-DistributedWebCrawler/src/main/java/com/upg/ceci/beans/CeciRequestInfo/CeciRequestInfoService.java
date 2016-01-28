package com.upg.ceci.beans.CeciRequestInfo;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 抓取地址配置*/
@Service("CeciRequestInfoService")
public class CeciRequestInfoService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CeciRequestInfoService.class);

    public CeciRequestInfoDao getDao(){
        return getMySqlSession().getMapper(CeciRequestInfoDao.class);
    }

}
