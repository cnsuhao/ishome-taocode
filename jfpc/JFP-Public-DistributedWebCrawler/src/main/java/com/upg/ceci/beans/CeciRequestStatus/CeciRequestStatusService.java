package com.upg.ceci.beans.CeciRequestStatus;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 抓取地址访问状态*/
@Service("CeciRequestStatusService")
public class CeciRequestStatusService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CeciRequestStatusService.class);

    public CeciRequestStatusDao getDao(){
        return getMySqlSession().getMapper(CeciRequestStatusDao.class);
    }

}
