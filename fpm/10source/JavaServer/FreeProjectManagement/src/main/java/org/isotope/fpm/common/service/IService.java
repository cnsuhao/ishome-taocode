package org.isotope.fpm.common.service;

import org.isotope.fpm.common.bean.FromDateBean;

public interface IService {
	/**
	 * 业务流程
	 * @return
	 */
	public FromDateBean doProcess(FromDateBean fdb) ;
	
	/**
	 * 批量插入
	 * @return
	 */
	public FromDateBean batchInsert(FromDateBean fdb);	
	/**
	 * 批量删除
	 * @return
	 */
	public FromDateBean batchDelete(FromDateBean fdb);
	/**
	 * 无条件获得全体数据
	 * @return
	 * @see #showPage
	 */
	public FromDateBean findAll(FromDateBean fdb);
	/**
	 * 分页显示数据
	 * @return
	 */
	public FromDateBean showPage(FromDateBean fdb);
	/**
	 * 标准操作：插入
	 * @return
	 */
	public FromDateBean doInsert(FromDateBean fdb);
	/**
	 * 标准操作：更新
	 * @return
	 */
	public FromDateBean doUpdate(FromDateBean fdb);
	/**
	 * 标准操作：删除
	 * @return
	 */
	public FromDateBean doDelete(FromDateBean fdb);
	/**
	 * 标准操作：检索
	 * @return
	 */
	public FromDateBean doSelect(FromDateBean fdb);
	
}
