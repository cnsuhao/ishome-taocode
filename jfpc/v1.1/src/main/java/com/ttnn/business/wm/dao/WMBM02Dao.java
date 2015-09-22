package com.ttnn.business.wm.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 充值交易记录*/
public interface WMBM02Dao extends ISSQLDaoSupport{

	public int toUpdate( FrameworkDataBean paramBean);
	
    public List<FrameworkDataBean> doFindList(FrameworkDataBean dbParamBean);
	
}
