package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMUI01Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户信息*/
@Service
public class WMUI01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMUI01Dao.class);
    }

}
