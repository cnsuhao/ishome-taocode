package com.mcookies.qxy.common.MepItem;

import org.springframework.stereotype.Service;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 德育个人项目表*/
@Service
public class MepItemService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MepItemService.class);

    public MepItemDao getDao(){
        return getMySqlSession().getMapper(MepItemDao.class);
    }

}
