package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMBM03Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 提现交易记录*/
@Service
public class WMBM03Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMBM03Dao.class);
    }

}
