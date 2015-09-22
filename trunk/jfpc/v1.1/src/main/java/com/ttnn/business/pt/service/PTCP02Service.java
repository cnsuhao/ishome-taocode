package com.ttnn.business.pt.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.pt.dao.PTCP02Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 产品功能信息*/
@Service
public class PTCP02Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(PTCP02Dao.class);
    }

}
