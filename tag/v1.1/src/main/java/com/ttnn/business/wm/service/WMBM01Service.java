package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMBM01Dao;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 支付通道*/
@Service
public class WMBM01Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMBM01Dao.class);
    }
    
   public int doUpdate1(CSPVOSupport formParamBean) {
		
		//更新者、更新时间		
		return getDao().doUpdate(formParamBean);
	}

}
