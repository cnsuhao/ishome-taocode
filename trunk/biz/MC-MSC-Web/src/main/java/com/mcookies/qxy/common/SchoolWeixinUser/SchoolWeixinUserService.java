package com.mcookies.qxy.common.SchoolWeixinUser;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 学校微信企业号关注用户表*/
@Service
public class SchoolWeixinUserService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(SchoolWeixinUserService.class);

    public SchoolWeixinUserDao getDao(){
        return getMySqlSession().getMapper(SchoolWeixinUserDao.class);
    }

}
