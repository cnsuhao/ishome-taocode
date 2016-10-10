package com.mcookies.qxy.common.SchoolWeixin;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 学校微信企业号表*/
@Service
public class SchoolWeixinService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(SchoolWeixinService.class);

    public SchoolWeixinDao getDao(){
        return getMySqlSession().getMapper(SchoolWeixinDao.class);
    }

}
