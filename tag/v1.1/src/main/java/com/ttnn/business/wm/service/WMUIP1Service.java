package com.ttnn.business.wm.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMUIP1Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户组定义*/
@Service
public class WMUIP1Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMUIP1Dao.class);
    }

}
