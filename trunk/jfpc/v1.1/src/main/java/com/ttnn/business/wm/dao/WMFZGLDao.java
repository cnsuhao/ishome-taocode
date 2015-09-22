package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 分站数据 */
public interface WMFZGLDao extends ISSQLDaoSupport {
	/**
	 * 分配通道查询
	 * @param pageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageTD(PageVO pageVO);
	
	/**
	 * 用户登录日志查询
	 * @param pageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageRZ(PageVO pageVO);
	
	/**
	 * 分站管理查询
	 * @param pageVO
	 * @return
	 */	
	public List<FrameworkDataBean> doSelectPageFZ(PageVO pageVO);	
	
	
	/**
	 * 超级管理员查询
	 * @param pageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageGL(PageVO pageVO);
	
	/**
	 * 客户系统信息
	 * @param pageVO
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageXT(PageVO pageVO);
	
	
	
	
}
