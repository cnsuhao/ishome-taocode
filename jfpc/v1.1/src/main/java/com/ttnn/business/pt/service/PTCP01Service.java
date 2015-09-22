package com.ttnn.business.pt.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.pt.dao.PTCP01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 产品信息*/
@Service
public class PTCP01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(PTCP01Dao.class);
    }

}
