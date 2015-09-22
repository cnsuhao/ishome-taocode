package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSME1Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 异常警报*/
@Service
public class CSSME1Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSME1Dao.class);
    }

}
