package com.ttnn.common.base.service;

import com.ttnn.framework.bean.FrameworkDataBean;

public interface IService {
	/**
	 * 业务流程
	 * @return
	 */
	public FrameworkDataBean doProcess(FrameworkDataBean fdb) ;
	
	/**
	 * 批量插入
	 * @return
	 */
	public FrameworkDataBean batchInsert(FrameworkDataBean fdb);	
	/**
	 * 批量删除
	 * @return
	 */
	public FrameworkDataBean batchDelete(FrameworkDataBean fdb);
	/**
	 * 无条件获得全体数据
	 * @return
	 * @see #showPage
	 */
	public FrameworkDataBean findAll(FrameworkDataBean fdb);
	/**
	 * 分页显示数据
	 * @return
	 */
	public FrameworkDataBean showPage(FrameworkDataBean fdb);
	/**
	 * 标准操作：插入
	 * @return
	 */
	public FrameworkDataBean doInsert(FrameworkDataBean fdb);
	/**
	 * 标准操作：更新
	 * @return
	 */
	public FrameworkDataBean doUpdate(FrameworkDataBean fdb);
	/**
	 * 标准操作：删除
	 * @return
	 */
	public FrameworkDataBean doDelete(FrameworkDataBean fdb);
	/**
	 * 标准操作：检索
	 * @return
	 */
	public FrameworkDataBean doSelect(FrameworkDataBean fdb);
	
}
