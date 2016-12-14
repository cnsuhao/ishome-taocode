package org.isotope.fpm.framework.support;

import java.util.List;

import org.isotope.fpm.framework.page.bean.PageVO;

/**
 * 数据业务操作接口定义超类<br>
 * 定义通用7个操作方法
 * @since 0.1 2012-7-13
 * @version 0.1
 * @see <IMySQLMapperSupport>
 */
public interface IMyServiceSupport {
	//待定
	public int[] batchDelete(List<MyBeanSupport> paramBean);
	public int[] batchInsert(List<MyBeanSupport> paramBean);
	
	/**
	 * 返回查询数据
	 * @param paramBean
	 * @return
	 */
	public List<MyBeanSupport> findAll(MyBeanSupport paramBean);

	/**
	 * 分页显示
	 * @param paramPageModel
	 * @return
	 */
	public PageVO showPage(PageVO paramPageModel);

	//增删改查（CRUD）

	/**
	 * 插入一条记录
	 * @param paramBean
	 */
	public void dbInsert(MyBeanSupport paramBean) ;

	/**
	 * 更新一条记录
	 * @param paramBean
	 */
	public void dbUpdate(MyBeanSupport paramBean) ;

	/**
	 * 删除一条记录
	 * @param paramBean
	 */
	public void dbDelete(MyBeanSupport paramBean) ;

	/**
	 * 查询记录
	 * @param paramBean
	 */
	public MyBeanSupport dbSelect(MyBeanSupport paramBean) ;

}
