package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
public interface WMSZT2Dao extends ISSQLDaoSupport{
	
	public List<FrameworkDataBean> doSelectPage(PageVO paramPageVO);
	public List<FrameworkDataBean> doSelectPageTX(PageVO paramPageVO);
	public List<FrameworkDataBean> doSelectPageYJ(PageVO paramPageVO);
	
	public List<FrameworkDataBean> doSelectPageRitj(PageVO paramPageVO);
	public List<FrameworkDataBean> doSelectPageYutj(PageVO paramPageVO);
	
	public List<FrameworkDataBean> doSelectPageZhtj(PageVO paramPageVO);
	
	
	public void doWMSZT2Update(CSPVOSupport param);
	
	public void doUpdateTX(CSPVOSupport param);
	public void doUpdateYJ(CSPVOSupport param);
	

}
