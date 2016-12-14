package com.ttnn.business.wm.dao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 贷付宝首页数据统计服务*/
public interface WMMainTotalDao extends ISSQLDaoSupport{
	public FrameworkDataBean doTotlaHT(FrameworkDataBean paramBean);
	public FrameworkDataBean doTotlaZK(FrameworkDataBean paramBean);
}
