package com.ttnn.business.pt.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.pt.dao.PTCP04Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 产品套餐功能关联*/
@Service
public class PTCP04Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(PTCP04Dao.class);
    }

}
