package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.CardDao;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 银行账户*/
@Service
public class CardService extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CardDao.class);
    }

}
