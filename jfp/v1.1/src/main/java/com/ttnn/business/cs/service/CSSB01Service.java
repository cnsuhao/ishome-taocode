package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSB01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 数据字典管理*/
@Service
public class CSSB01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSB01Dao.class);
    }

}
