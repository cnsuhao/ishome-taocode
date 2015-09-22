package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMBM04Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 佣金交易记录*/
@Service
public class WMBM04Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMBM04Dao.class);
    }

}
