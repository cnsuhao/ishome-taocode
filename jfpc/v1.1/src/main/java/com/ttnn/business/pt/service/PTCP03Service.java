package com.ttnn.business.pt.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.pt.dao.PTCP03Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 产品销售套餐分类*/
@Service
public class PTCP03Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(PTCP03Dao.class);
    }

}
