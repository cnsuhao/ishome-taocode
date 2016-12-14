package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/***** 充值提现佣金记录 支付通道查询* * */
public interface WMQTJLCXDao extends ISSQLDaoSupport {
	
	/**
	 * 下线一览
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHY(PageVO paramPageVO);
	
	// 佣金记录查询
	public List<FrameworkDataBean> doSelectPageCommission(PageVO pageVO);

	// 查下线支付通道
	public List<FrameworkDataBean> doSelectPagePayChannel(PageVO pageVO);

	public List<FrameworkDataBean> dochazhao5(FrameworkDataBean ParamBean);

	public List<FrameworkDataBean> doSelectPagePayFor(PageVO pageVO);

	public List<FrameworkDataBean> doSelectPagePullCash(PageVO pageVO);
	
    //查询会员银行卡
	public List<FrameworkDataBean>  doSelectPageYHK(PageVO pageVO);
    //充值结算记录
	public List<FrameworkDataBean> doSelectPageCZJYJL(PageVO pageVO);
    //提现结算记录
	public List<FrameworkDataBean> doSelectPageTXJYJL(PageVO pageVO);
     //佣金结算记录
	public List<FrameworkDataBean> doSelectPageYJJSJL(PageVO pageVO);
    //对账单 
	public List<FrameworkDataBean> doSelectPageDZD(PageVO pageVO);

	//通道分配检索
	public List<FrameworkDataBean> doSelectPageFACTD(PageVO pageVO);
	//查询下线提现对应充值记录
		public List<FrameworkDataBean> doSelectPageTC(PageVO pageVO);

}