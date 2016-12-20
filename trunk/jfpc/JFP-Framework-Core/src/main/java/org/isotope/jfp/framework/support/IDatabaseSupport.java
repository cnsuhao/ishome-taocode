package org.isotope.jfp.framework.support;

import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 数据库操作基本接口CURD
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 * @see <ISFrameworkConstants>
 */
public interface IDatabaseSupport<T> extends ISFrameworkConstants {
	/**
	 * 分页查询
	 * @param formParam
	 * @return
	 */
	int doUpdateAll(MyDataBaseObjectSupport paramBean);
	/////////////////// 条件查询操作///////////////////
	/**
	 * 分页查询
	 * @param formParam
	 * @return
	 */
	List<T> doSelectPage(PageVOSupport<T> formParam);
	/**
	 * 全体查询
	 * @param formParam
	 * @return
	 */
	List<T> doSelectPage(MyDataBaseObjectSupport paramBean);
	/////////////////// 增删改查（CRUD）///////////////////

	/**
	 * 查询一条记录(主键)
	 * 
	 * @param paramBean
	 */
	MyDataBaseObjectSupport doRead(MyDataBaseObjectSupport paramBean);

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 */
	int doInsert(MyDataBaseObjectSupport paramBean);

	/**
	 * 更新一条记录(主键)
	 * 
	 * @param paramBean
	 */
	int doUpdate(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键，物理)
	 * 
	 * @param paramBean
	 */
	int doDelete(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键,逻辑)
	 * 
	 * @param paramBean
	 */
	int toDelete(MyDataBaseObjectSupport paramBean);
}
