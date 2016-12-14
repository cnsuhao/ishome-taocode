package org.jfpc.framework.support;

import java.util.List;

import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.page.PageVOSupport;

/**
 * 数据库操作基本接口CURD
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 * @see <ISFrameworkConstants>
 */
public interface ISDatabaseSupport extends ISFrameworkConstants {	

	/**
	 * 根据条件全表更新
	 * @param paramBean
	 * @return
	 */
	public int doUpdateAll(MyDataBaseObjectSupport paramBean);	
	///////////////////////////////////// 多数据操作///////////////////////////////////
	/**
	 * 分页查询
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPage(PageVOSupport formParam);	

	///////////////////////////////////// 增删改查（CRUD）///////////////////////////////////

	/**
	 * 查询一条记录(主键)
	 * 
	 * @param paramBean
	 */
	public MyDataBaseObjectSupport doRead(MyDataBaseObjectSupport paramBean);

	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 */
	public int doInsert(MyDataBaseObjectSupport paramBean);

	/**
	 * 更新一条记录(主键)
	 * 
	 * @param paramBean
	 */
	public int doUpdate(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键，物理)
	 * 
	 * @param paramBean
	 */
	public int doDelete(MyDataBaseObjectSupport paramBean);

	/**
	 * 删除一条记录(主键,逻辑)
	 * 
	 * @param paramBean
	 */
	public int toDelete(MyDataBaseObjectSupport paramBean);
}
