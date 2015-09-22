package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSPB1Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 登录用户临时授权*/
@Service
public class CSSPB1Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSPB1Dao.class);
    }

}
