package com.ttnn.business.wm.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMBM02Dao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 充值交易记录*/
@Service
public class WMBM02Service extends MyServiceSupportImpl {

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMBM02Dao.class);
    }
    
    public List<FrameworkDataBean> doFindList(CSPVOSupport dbParamBean) {
		return ((WMBM02Dao)getDao()).doFindList(dbParamBean);
	}
    
	public int doUpdate1(CSPVOSupport formParamBean) {
		
		//更新者、更新时间		
		return getDao().doUpdate(formParamBean);
	}
    
}
