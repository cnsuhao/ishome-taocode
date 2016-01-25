package org.ishome.jfp.intranet.beans.Department;

import java.util.List;

import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.ishome.jfp.framework.utils.PKHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** 科室表 */
@Service
public class DepartmentService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	public DepartmentDao getDao() {
		return getMySqlSession().getMapper(DepartmentDao.class);
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data, int dbType) {

	}

	/**
	 * 插入数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doInsert(DepartmentDBO formParamBean) {
		changeTable(formParamBean, DB_INSERT);
		// 主键ID
		if (StringUtils.isEmpty(formParamBean.getDeptId()))
			formParamBean.setDeptId(PKHelper.creatPUKey());
		return super.doInsert(formParamBean);
	}

	/**
	 * 获取当前医院的科室信息
	 * 
	 * @param paramBean
	 * @return
	 */
	public List<FrameworkDataBean> getAccessDeptId(MyDataBaseObjectSupport paramBean) {
		changeTable(paramBean, DB_SELECT);
		return getDao().getAccessDeptId(paramBean);
	}

	/**
	 * 将待对接的医院的全部科室状态state置为-2 表示未对接
	 * 
	 * @param paramBean
	 * @return
	 */
	public int delDepartment(MyDataBaseObjectSupport paramBean) {
		changeTable(paramBean, DB_DELETE);
		return getDao().delDepartment(paramBean);

	}
	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdateByAccess(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_UPDATE);
		// // 更新者、更新时间
		// formParamBean.setUu2(getLoginerId());
		return getDao().doUpdateByAccess(formParamBean);
	}
	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdateByName(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_UPDATE);
		// // 更新者、更新时间
		// formParamBean.setUu2(getLoginerId());
		return getDao().doUpdateByName(formParamBean);
	}
}
