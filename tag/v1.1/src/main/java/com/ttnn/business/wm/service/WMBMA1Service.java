package com.ttnn.business.wm.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMBMA1Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


@Service
public class WMBMA1Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMBMA1Dao.class);
    }

}
