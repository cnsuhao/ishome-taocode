package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSL01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 业务运行日志*/
@Service
public class CSSL01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSL01Dao.class);
    }

}
