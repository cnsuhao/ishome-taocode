package com.ttnn.business.wm.biz;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMZKJGDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 总控监管数据 */
@Service
public class WMZKJGService extends MyServiceSupportImpl {

	@Override
	public WMZKJGDao getDao() {
		return mySqlSession.getMapper(WMZKJGDao.class);
	}

	/**
	 * 各产品会员查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageHY(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageHY(pageVO));
	}

	/**
	 * 各产品通道查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageTD(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageTD(pageVO));
	}

	/**
	 * 各产品用户费率查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageFL(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageFL(pageVO));
	}
	/**
	 * 各产品用户费率查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageDZ(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageDZ(pageVO));
	}
	/**
	 * 各产品用户充值查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageCZ(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageCZ(pageVO));
	}

	/**
	 * 各产品用户体现查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageTX(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageTX(pageVO));
	}

	/**
	 * 各产品用户佣金查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageYJ(PageVO pageVO) {
		pageVO.setPageData(getDao().doSelectPageYJ(pageVO));
	}
	
	/**
	 * 首页系统表数据查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageSY(PageVO pageVO){
		pageVO.setPageData(getDao().doRead(pageVO));		
	}

	/**
	 * 首页系统公告列表
	 * @param dbParamBean
	 * @return
	 */
	public void doSelectPageGG(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageGG(pageVO));
	}
	
	/**
	 * 读取公告详细内容
	 * @param dbParamBean
	 * @return
	 */
	public void doReadGG(PageVO pageVO){
		pageVO.setPageData(getDao().doReadGG(pageVO));
	}
}
