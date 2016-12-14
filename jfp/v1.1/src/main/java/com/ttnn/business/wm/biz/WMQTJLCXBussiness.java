package com.ttnn.business.wm.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMQTJLCXDao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户类型 */
@Service
public class WMQTJLCXBussiness extends MyServiceSupportImpl {
	

	@Override
	public WMQTJLCXDao getDao() {
		return mySqlSession.getMapper(WMQTJLCXDao.class);
	}
	
	/**
	 * 通道分配检索
	 * 
	 * @param paramPageVO 
	 * @return
	 */
	public void doSelectPageFACTD(PageVO pageVO) { // 设定产品ID
		pageVO.setPageData(getDao().doSelectPageFACTD(pageVO));
	}
	
	/**
	 * 会员一览
	 * 
	 * @param paramPageVO 
	 * @return
	 */
	public void doSelectPageHY(PageVO pageVO) { // 设定产品ID
		pageVO.setPageData(getDao().doSelectPageHY(pageVO));
	}

	/**
	 * 查询佣金记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageCommission(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageCommission(pageVO));
	}

	/**
	 * 查下线支付通道
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPagePayChannel(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPagePayChannel(pageVO));
	}

	/**
	 * 查询我的支付通道
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> dochazhao5(FrameworkDataBean paramBean) {
		return getDao().dochazhao5(paramBean);

	}

	/**
	 * 查下线充值记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPagePayFor(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPagePayFor(pageVO));
	}

	/**
	 * 查下线提现记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPagePullCash(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPagePullCash(pageVO));
	}
	/**
	 * 查下线银行卡
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageYHK(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageYHK(pageVO));
	}
	
	
	/**
	 * 查佣金结算记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageYJJSJL(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageYJJSJL(pageVO));
	}
	
	
	
	/**
	 * 查充值结算记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageCZJYJL(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageCZJYJL(pageVO));
	}
	
	
	
	/**
	 * 查提现结算记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageTXJYJL(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageTXJYJL(pageVO));
	}
	
	
	/**
	 * 对账单
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageDZD(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageDZD(pageVO));
	}
	/**
	 * 查询下线提现对应充值记录
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageTC(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageTC(pageVO));
	}
	

}