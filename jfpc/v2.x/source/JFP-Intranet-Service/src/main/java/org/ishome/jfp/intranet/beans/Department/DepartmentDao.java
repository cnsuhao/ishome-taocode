package org.ishome.jfp.intranet.beans.Department;

import java.util.List;

import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.support.ISDatabaseSupport;
import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;

/** 科室表*/
public interface DepartmentDao extends ISDatabaseSupport{

	/**
	 * 获取当前医院的科室信息
	 * @param paramBean
	 * @return
	 */
	List<FrameworkDataBean> getAccessDeptId(MyDataBaseObjectSupport paramBean);

	/**
	 * 将待对接的医院的全部科室状态state置为-2 表示未对接
	 * @param paramBean
	 * @return
	 */
	int delDepartment(MyDataBaseObjectSupport paramBean);
	/**
	 * 更新一条记录(对接科室ID)
	 * 
	 * @param paramBean
	 */
	int doUpdateByAccess(MyDataBaseObjectSupport paramBean);
	/**
	 * 更新一条记录(名称)
	 * 
	 * @param paramBean
	 */
	int doUpdateByName(MyDataBaseObjectSupport paramBean);

}
