package com.ttnn.business.wm.biz;

import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMFZGLDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 分站数据 */
@Service
public class WMFZGLService extends MyServiceSupportImpl {

	@Override
	public WMFZGLDao getDao() {
		return mySqlSession.getMapper(WMFZGLDao.class);
	}
	
	/**
	 * 用户登录日志查询
	 * @param pageVO
	 * @return
	 */
	public void doSelectPageTD(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageTD(pageVO));		
	}

	/**
	 * 用户登录日志查询
	 * @param pageVO
	 * @return
	 */
	public void doSelectPageRZ(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageRZ(pageVO));		
	}
	
	/**
	 *分站查询
	 * @param pageVO
	 * @return
	 */
	public void doSelectPageFZ(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageFZ(pageVO));		
	}
	
	/**
	 * 管理员查询
	 * @param pageVO
	 * @return
	 */
	public void doSelectPageGL(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageGL(pageVO));		
	}
	
	/**
	 * 客户系统信息
	 * @param pageVO
	 * @return
	 */
	public void doSelectPageXT(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageXT(pageVO));				
	}
	
}
