package com.ttnn.business.cs.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

public interface CheckUserMoneyDao extends ISSQLDaoSupport{
   
	/**
	 * 对账单查询
	 * @param pageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCheck(PageVO pageVO);
	
}
