package com.mcookies.qxy.common.SchoolWeixinGroup;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 学校微信企业号用户组表*/
@Service
public class SchoolWeixinGroupService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(SchoolWeixinGroupService.class);

    public SchoolWeixinGroupDao getDao(){
        return getMySqlSession().getMapper(SchoolWeixinGroupDao.class);
    }

}
