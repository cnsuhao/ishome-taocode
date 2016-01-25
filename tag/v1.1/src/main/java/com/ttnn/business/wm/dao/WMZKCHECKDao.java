package com.ttnn.business.wm.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/** 总控监管数据 */
public interface WMZKCHECKDao extends ISSQLDaoSupport {

	/**
	 * 各产品会员查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHY(PageVO pageVO);

	/**
	 * 各产品通道查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageTD(PageVO pageVO);

	/**
	 * 各产品用户费率查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageFL(PageVO pageVO);
	/**
	 * 各产品用户对账查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageDZ(PageVO pageVO);

	/**
	 * 各产品用户充值查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCZ(PageVO pageVO);

	/**
	 * 各产品用户体现查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageTX(PageVO pageVO);

	/**
	 * 各产品用户佣金查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageYJ(PageVO pageVO);
	
	/**
	 * 首页系统表数据查询
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doRead(PageVO pageVO);	
	
	/**
	 * 首页系统公告列表
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageGG(PageVO pageVO);
	
	/**
	 * 读取公告详细内容
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doReadGG(PageVO pageVO);	
	

}
