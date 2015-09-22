package com.ttnn.business.wm.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 贷付宝后台数据统计服务*/
public interface WMHTTotalDao extends ISSQLDaoSupport{
	/**
	 * 交易记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageJY(PageVO paramPageVO);
	
	/**
	 * 充值记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCZ(PageVO paramPageVO);
	/**
	 * 提现记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageTX(PageVO paramPageVO);
	/**
	 * 佣金记录统计
	 * @param paramPageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageYJ(PageVO paramPageVO);
}
