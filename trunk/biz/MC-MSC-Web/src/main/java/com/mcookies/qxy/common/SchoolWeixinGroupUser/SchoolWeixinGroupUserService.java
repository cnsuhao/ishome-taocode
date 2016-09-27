package com.mcookies.qxy.common.SchoolWeixinGroupUser;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 学校微信企业号用户所属用户组表*/
@Service
public class SchoolWeixinGroupUserService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(SchoolWeixinGroupUserService.class);

    public SchoolWeixinGroupUserDao getDao(){
        return getMySqlSession().getMapper(SchoolWeixinGroupUserDao.class);
    }

}
