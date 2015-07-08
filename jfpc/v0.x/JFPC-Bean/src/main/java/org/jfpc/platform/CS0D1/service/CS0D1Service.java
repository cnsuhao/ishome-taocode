package org.jfpc.platform.CS0D1.service;
import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.CS0D1.dao.CS0D1Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 内部消息*/
@Service
public class CS0D1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0D1Service.class);

    public CS0D1Dao getDao(){
        return getMySqlSession().getMapper(CS0D1Dao.class);
    }

}
