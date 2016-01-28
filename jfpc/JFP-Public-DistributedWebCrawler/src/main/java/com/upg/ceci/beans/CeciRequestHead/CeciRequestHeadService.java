package com.upg.ceci.beans.CeciRequestHead;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 抓取地址请求头配置*/
@Service("CeciRequestHeadService")
public class CeciRequestHeadService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CeciRequestHeadService.class);

    public CeciRequestHeadDao getDao(){
        return getMySqlSession().getMapper(CeciRequestHeadDao.class);
    }

}
