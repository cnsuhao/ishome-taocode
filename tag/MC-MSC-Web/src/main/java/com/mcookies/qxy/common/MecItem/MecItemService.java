package com.mcookies.qxy.common.MecItem;

import org.springframework.stereotype.Service;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 德育班级项目表*/
@Service
public class MecItemService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MecItemService.class);

    public MecItemDao getDao(){
        return getMySqlSession().getMapper(MecItemDao.class);
    }

}
