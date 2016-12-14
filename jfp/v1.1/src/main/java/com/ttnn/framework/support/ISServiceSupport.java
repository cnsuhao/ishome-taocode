package com.ttnn.framework.support;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;

/**
 * 数据业务操作接口定义超类<br>
 * 定义通用8个操作方法<br>
 * 缓存参考SSM配置
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 * @see IMySQLMapperSupport
 */
public interface ISServiceSupport  extends ISBusinessSupport{
//	/**
//	 * 批量删除
//	 * @param paramBean
//	 * @return
//	 */
//	public int[] doBatchDelete(PageVO paramPageModel);
//	/**
//	 * 批量插入
//	 * @param paramBean
//	 * @return
//	 */
//	public int[] doBatchInsert(PageVO paramPageModel);
//	
//	/**
//	 * 返回查询数据
//	 * @param paramBean
//	 * @return
//	 */
//	public List<FrameworkDataBean> doFindList(PageVO paramPageModel);

	/**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 */
	public PageVO doSelectPage(PageVO paramPageModel);

	//////////////////////////////////////////////////////////////////////////////////
	/**
	 * 根据条件更新所有记录<br>
	 * 添加与结果不能使用同一个字段<br>
	 * 如果需要，请自行修改对应方法<br>
	 * 参考XML#doUpdateAll
	 * @param paramBean
	 * @return
	 */
//	public int doUpdateAll(FrameworkDataBean paramBean);
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * 删除一条记录(主键,逻辑)
	 * @param paramBean
	 */
	public int toDelete(CSPVOSupport paramBean) ;
	
	//增删改查（CRUD）

	/**
	 * 插入一条记录
	 * @param paramBean
	 */
	public int doInsert(CSPVOSupport paramBean) ;

	/**
	 * 更新一条记录(主键)
	 * @param paramBean
	 */
	public int doUpdate(CSPVOSupport paramBean) ;

	/**
	 * 查询一条记录(主键)
	 * @param paramBean
	 */
	public FrameworkDataBean doRead(CSPVOSupport paramBean) ;
	
	/**
	 * 删除一条记录(主键，物理)
	 * @param paramBean
	 */
	public int doDelete(CSPVOSupport paramBean) ;

}
