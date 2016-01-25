package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMSZT3Dao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


/** 支付通道*/
@Service
public class WMSZT3Service extends MyServiceSupportImpl {
	
	

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMSZT3Dao.class);
    }
   

}
