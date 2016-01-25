package com.ttnn.business.wm.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 贷付宝后台数据一览服务*/
public interface WMHTMXDao extends ISSQLDaoSupport{
	/**
	 * 交易记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageJYMX(PageVO paramPageVO);
	
	/**
	 * 充值记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCZMX(PageVO paramPageVO);
	/**
	 * 提现记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageTXMX(PageVO paramPageVO);
	/**
	 * 佣金记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageYJMX(PageVO paramPageVO);
}
