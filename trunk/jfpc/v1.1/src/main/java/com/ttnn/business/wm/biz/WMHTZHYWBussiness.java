package com.ttnn.business.wm.biz;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMHTZHYWDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户类型 */
@Service
public class WMHTZHYWBussiness extends MyServiceSupportImpl {
	

	@Override
	public WMHTZHYWDao getDao() {
		return mySqlSession.getMapper(WMHTZHYWDao.class);
	}
	
	/**
	 * 通道分配检索
	 * 
	 * @param paramPageVO 
	 * @return
	 */
	public void doSelectPageFPTD(PageVO pageVO) { // 设定产品ID
		pageVO.setPageData(getDao().doSelectPageFPTD(pageVO));
	}
	
	public void doSelectPageSjtd(PageVO pageVO) { // 设定产品ID
		pageVO.setPageData(getDao().doSelectPageSjtd(pageVO));
	}
//	
//	/**
//	 * 会员一览
//	 * 
//	 * @param paramPageVO 
//	 * @return
//	 */
//	public void doSelectPageHY(PageVO pageVO) { // 设定产品ID
//		pageVO.setPageData(getDao().doSelectPageHY(pageVO));
//	}
//
//	/**
//	 * 查询佣金记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageCommission(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageCommission(pageVO));
//	}
//
//	/**
//	 * 查下线支付通道
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPagePayChannel(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPagePayChannel(pageVO));
//	}
//
//	/**
//	 * 查询我的支付通道
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public List<FrameworkDataBean> dochazhao5(FrameworkDataBean paramBean) {
//		return getDao().dochazhao5(paramBean);
//
//	}
//
//	/**
//	 * 查下线充值记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPagePayFor(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPagePayFor(pageVO));
//	}
//
//	/**
//	 * 查下线提现记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPagePullCash(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPagePullCash(pageVO));
//	}
//	/**
//	 * 查下线银行卡
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageYHK(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageYHK(pageVO));
//	}
//	
//	
//	/**
//	 * 查佣金结算记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageYJJSJL(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageYJJSJL(pageVO));
//	}
//	
//	
//	
//	/**
//	 * 查充值结算记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageCZJYJL(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageCZJYJL(pageVO));
//	}
//	
//	
//	
//	/**
//	 * 查提现结算记录
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageTXJYJL(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageTXJYJL(pageVO));
//	}
//	
//	
//	/**
//	 * 对账单
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public void doSelectPageDZD(PageVO pageVO) {
//		pageVO.setPageData(getDao().doSelectPageDZD(pageVO));
//	}

}