package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
public interface TotalDao extends ISSQLDaoSupport{
	
	public List<FrameworkDataBean> doSelectRecharge(PageVO paramPageVO);
	
	
	public  List<FrameworkDataBean> doSelectWithdraw(PageVO formParamPageModel);
	
	public List<FrameworkDataBean> doSelectCommission(PageVO formParamPageModel);
	
}
