package com.ttnn.business.cs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSB03Dao;
import com.ttnn.business.cs.dao.MenuDao;
import com.ttnn.framework.page.util.MyMap;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 系统画面菜单*/
@Service
public class CSSB03Service extends MyServiceSupportImpl {
    
	
    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSB03Dao.class);
    }
   
    public List<MyMap>  findTree(CSPVOSupport paramBean){
    	return mySqlSession.getMapper(MenuDao.class).findTree(paramBean);
    }
    
   
}
