package org.jfpc.platform.MS0A5.service;
import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.MS0A5.dao.MS0A5Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 动态页面定义*/
@Service
public class MS0A5Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0A5Service.class);

    public MS0A5Dao getDao(){
        return getMySqlSession().getMapper(MS0A5Dao.class);
    }

}
