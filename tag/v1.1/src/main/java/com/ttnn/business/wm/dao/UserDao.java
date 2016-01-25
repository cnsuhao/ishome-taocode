package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
public interface UserDao extends ISSQLDaoSupport{
	
	public List<FrameworkDataBean> doSelectPageBank(PageVO paramPageVO);
	
	public List<FrameworkDataBean> doSelectPageUser(PageVO paramPageVO);
	public List<FrameworkDataBean> doSelectPageUserRealname(PageVO paramPageVO);
	
	public List<FrameworkDataBean> doFindgrtd(CSPVOSupport param);
	
	public void checkUser(CSPVOSupport param);
	public void cancelUser(CSPVOSupport param);
	public void checkCard(CSPVOSupport param);
	public void checkReal(CSPVOSupport param);
	
	public FrameworkDataBean showDetail(CSPVOSupport param);
	public FrameworkDataBean showDetailRealname(CSPVOSupport param);
	
	 public int countUsername(String username);
	 public void doupdateUsergroup(CSPVOSupport param);

}
