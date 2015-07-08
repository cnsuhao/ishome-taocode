package org.isotopes.jfp.framework.support;

import org.springframework.web.servlet.ModelAndView;


public interface ISControllerProcessSupport  {
	// /////////////////////////////复杂操作/////////////////////////////////////////
	/**
	 * 默认显示
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doHomeProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	// /////////////////////////////复杂操作/////////////////////////////////////////

	/**
	 * 批量删除记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doDestroyProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	/**
	 * 批量插入记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doInsertProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	/**
	 * 数据一览
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doAllProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	/**
	 * 分页显示
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doPageProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	// ////////////////////////////基本操作//////////////////////////////////////////
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doCreateProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doReplaceProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doViewProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;

	/**
	 * 查删除一条记录
	 * 
	 * @param paramBean
	 *            画面参数
	 * @param pageMAV
	 */
	public void doRemoveProcess(CSPVOSupport paramBean, ModelAndView pageMAV) ;
}
