package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
public interface WMCZG1Dao extends ISSQLDaoSupport{
	
	public List<FrameworkDataBean> doSelectPage(PageVO paramPageVO);
	public List<FrameworkDataBean> doSelectPageYJMX(PageVO paramPageVO);
	
	

}
