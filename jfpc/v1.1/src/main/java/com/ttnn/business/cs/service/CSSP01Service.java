package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSP01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 人员信息*/
@Service
public class CSSP01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSP01Dao.class);
    }

}
