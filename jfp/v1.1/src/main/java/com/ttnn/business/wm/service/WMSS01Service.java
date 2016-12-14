package com.ttnn.business.wm.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMSS01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 系统信息表（扩展）*/
@Service
public class WMSS01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMSS01Dao.class);
    }

}
