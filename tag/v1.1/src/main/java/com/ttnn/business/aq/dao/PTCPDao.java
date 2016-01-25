package com.ttnn.business.aq.dao;
import java.awt.Frame;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 平台*/
public interface PTCPDao extends ISSQLDaoSupport{

	/**
	 * 查询各产品的销售套餐 
	 */
	public List<FrameworkDataBean> doSelectPT(FrameworkDataBean paramBean);
	
	/**
	 * 查询各产品的对应套餐的功能
	 */
	public List<FrameworkDataBean> doSelectGN(FrameworkDataBean paramBean);
	
	/**
	 * 功能查询
	 */
	public List<FrameworkDataBean> doSelectCPGN(FrameworkDataBean paramBean);

	/**
	 * 管理员查询
	 */	
	public List<FrameworkDataBean> doReadC(FrameworkDataBean paramBean);
	
	/**
	 *查询套餐功能
	 */
	public List<FrameworkDataBean> do1SelectCP(FrameworkDataBean paramBean);
	
	/**
	 *查询一条记录
	 */
	public FrameworkDataBean doRead1(FrameworkDataBean paramBean);

	/**
	 * 添加平台客户系统
	 */
	public int do2InsertPt(FrameworkDataBean paramBean);

	/**
	 * 添加管理员
	 * @param paramBean
	 * @return
	 */
	public int do3InsertPt(CSPVOSupport paramBean);
	
}
