package com.ttnn.business.cs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CSSR03Dao;
import com.ttnn.business.cs.dao.UserGroupDao;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户组定义*/
@Service
public class CSSR03Service extends MyServiceSupportImpl {
    
	CSSR03Dao cssr03Dao;
	
    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(CSSR03Dao.class);
    }
    
    public List<String> findParentGroup(CSPVOSupport paramBean) {
    	return mySqlSession.getMapper(UserGroupDao.class).findParentGroup(paramBean);
	}

}
