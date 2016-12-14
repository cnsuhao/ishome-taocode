package com.ttnn.framework.support;

import com.ttnn.framework.page.bean.PageVO;

/**
 * RESTFul画面交互基本操作
 * 定义通用8个操作方法<br>
 * 缓存参考SSM配置
 * @author Spook
 * @since 0.1.0 2012-8-21
 * @version 0.1.0
 * @see ISServiceSupport 
 * @see IMySQLMapperSupport
 */
public interface ISControllerSupport extends ISBusinessSupport{

	
//	/**
//	 * 批量删除记录
//	 * @param paramBean
//	 * @deprecated
//	 */
////	@RequestMapping(value = "/B",method = RequestMethod.POST)
//	public Object doBatchDelete(CSPVOSupport paramBean) ;
//
//	/**
//	 * 批量插入记录
//	 * @param paramBean
//	 * @deprecated
//	 */
////	@RequestMapping(value = "/A",method = RequestMethod.POST)
//	public Object doBatchInsert(CSPVOSupport paramBean);

//	/**
//	 * 根据条件更新所有记录<br>
//	 * 添加与结果不能使用同一个字段<br>
//	 * 如果需要，请自行修改对应方法<br>
//	 * 参考XML#doUpdateAll
//	 * @param paramBean
//	 * @return
//	 * @see ISSQLDaoSupport#doFindList(CSPVOSupport)
//	 */
////	@RequestMapping(value = "/F",method = RequestMethod.POST)
//	public Object doUpdateAll(CSPVOSupport paramBean) ;

//	/**
//	 * 数据一览
//	 * @param paramBean
//	 * @return
//	 * @see ISSQLDaoSupport#doFindList(CSPVOSupport)
//	 */
////	@RequestMapping(value = "/F",method = RequestMethod.POST)
//	public Object doFindList(CSPVOSupport paramBean) ;
	
	/**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
//	@RequestMapping(value = "/L",method = RequestMethod.POST)
	public Object doSelectPage(CSPVOSupport paramBean);

	////////////////////////////////////////////////////////////////////////
	/**
	 * 插入一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
//	@RequestMapping(value = "/C",method = RequestMethod.POST)
	public Object doInsert(CSPVOSupport paramBean) ;

	/**
	 * 更新一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
//	@RequestMapping(value = "/U",method = RequestMethod.POST)
	public Object doUpdate(CSPVOSupport paramBean) ;

	/**
	 * 查询一条记录 		
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
//	@RequestMapping(value = "/R",method = RequestMethod.POST)
	public Object doRead(CSPVOSupport paramBean) ;
	
	/**
	 * 删除一条记录
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
//	@RequestMapping(value = "/D",method = RequestMethod.POST)
	public Object doDelete(CSPVOSupport paramBean);

}
