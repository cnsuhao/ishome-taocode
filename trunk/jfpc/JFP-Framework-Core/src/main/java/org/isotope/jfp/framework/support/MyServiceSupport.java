package org.isotope.jfp.framework.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class MyServiceSupport implements ISFrameworkConstants, ISDBConstants {

	/**
	 * 数据库连接
	 */
	private SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		if (mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}

	/**
	 * 设定SQL连接
	 * 
	 * @param mySqlSession
	 */
	public void setMySqlSession(SqlSession mySqlSession) {
		this.mySqlSession = mySqlSession;
	}

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>
	 * 基于事物控制
	 * 
	 * @return Statement
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator()).getConnection();
	}

	/////////////////////////
	/**
	 * 获得数据库操作对象
	 * 
	 * @return
	 */
	public IDatabaseSupport getDao() {
		return getMySqlSession().getMapper(IDatabaseSupport.class);
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data, int dbType) {
		// String companyType = ((XXXXXDBO)data).getT01();
		// 分表处理
		// if (ZERO.equals(companyType)) {
		// data.setTableName("0");
		// } else if (ONE.equals(companyType)) {
		// data.setTableName("1");
		// }
		// if (dbType != DB_SELECT) {
		//
		// }
	}

	/////////////分页查询////////////////
	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public PageVOSupport doSelectPage(PageVOSupport formParamPageModel) {
		return doSelectPage(formParamPageModel, true, true);
	}

	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @param ppp
	 * @return
	 */
	public PageVOSupport doSelectPage(PageVOSupport formParamPageModel, boolean ppp) {
		return doSelectPage(formParamPageModel, ppp, true);
	}

	/**
	 * 分页查询
	 * 
	 * @param formParamPageModel
	 * @param ppp
	 * @return
	 */
	public PageVOSupport doSelectPage(PageVOSupport formParamPageModel, boolean ppp, boolean ddd) {
		// 设定企业ID
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		// if (ppp && StringUtils.isEmpty(formParamBean.getPpp()))
		// formParamBean.setPpp(getCompanyId());
		//
		// if (ddd && StringUtils.isEmpty(formParamBean.getDdd()))
		// formParamBean.setDdd("0");

		// 查询数据
		changeTable((MyDataBaseObjectSupport) formParamBean, DB_SELECT);
		formParamPageModel.setPageListData(getDao().doSelectPage(formParamPageModel));

		return formParamPageModel;
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<? extends FrameworkDataBean> doSelectData(MyDataBaseObjectSupport formParamBean) {
		return doSelectData(formParamBean, true, true);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<? extends FrameworkDataBean> doSelectData(MyDataBaseObjectSupport formParamBean, boolean ppp) {
		return doSelectData(formParamBean, ppp, true);
	}

	/**
	 * 全表查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<? extends FrameworkDataBean> doSelectData(MyDataBaseObjectSupport formParamBean, boolean ppp, boolean ddd) {
		// 设定企业ID
		// if (ppp && StringUtils.isEmpty(formParamBean.getPpp()))
		// formParamBean.setPpp(getCompanyId());
		// if (ddd && StringUtils.isEmpty(formParamBean.getDdd()))
		// formParamBean.setDdd("0");
		changeTable((MyDataBaseObjectSupport) formParamBean, DB_SELECT);
		return getDao().doSelectPage(formParamBean);
	}

	/////////////////基本操作////带有乐观锁/////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_DELETE);
		return getDao().toDelete(formParamBean);
	}

	/**
	 * 物理删除一个用户
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_DELETE);
		return getDao().doDelete(formParamBean);
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int[] doInsertOrUpdate(List<? extends MyDataBaseObjectSupport> datas) {
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			changeTable(formParamBean, DB_INSERT);
			rs[i] = -1;
			try {
				rs[i] = doInsert(formParamBean);
			} catch (Exception e) {
				//System.err.println(e.getMessage());
				rs[i] = doUpdate(formParamBean);
			}
		}
		return rs;
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int doInsertOrUpdate(MyDataBaseObjectSupport formParamBean) {
		try {
			return doInsert(formParamBean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return doUpdate(formParamBean);
		}
	}

	/**
	 * 批量插入
	 * 
	 * @param datas
	 * @return
	 */
	public int[] doInsert(List<? extends MyDataBaseObjectSupport> datas) {
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			rs[i] = -1;
			try {
				rs[i] = doInsert(formParamBean);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}			
		}
		return rs;
	}

	/**
	 * 插入数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doInsert(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_INSERT);

		// 主键ID
		// if (StringUtils.isEmpty(formParamBean.getPuk()))
		// formParamBean.setPuk(PKHelper.creatPUKey());
		//
		// 数据所属系统
		// if (StringUtils.isEmpty(formParamBean.getGgg()))
		// formParamBean.setGgg("SYSTEM");
		//
		// 企业ID
		// if (StringUtils.isEmpty(formParamBean.getPpp()))
		// formParamBean.setPpp(getCompanyId());
		//
		// 有效标识
		// if (StringUtils.isEmpty(formParamBean.getDdd()))
		// formParamBean.setDdd("0");
		//
		// 有效标记、创建者、创建时间
		// formParamBean.setCc1(DateHelper.currentTimeMillisCN1());
		// formParamBean.setCc2(getLoginerId());
		// formParamBean.setUu1(DateHelper.currentTimeMillisCN1());
		// formParamBean.setUu2(getLoginerId());

		return getDao().doInsert(formParamBean);
	}

	/**
	 * 更新数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_UPDATE);
		// 更新者、更新时间
		// formParamBean.setUu2(getLoginerId());
		return getDao().doUpdate(formParamBean);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_SELECT);
		return doRead(formParamBean, true);
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @param ddd
	 * @return
	 */
	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean, boolean ddd) {
		changeTable(formParamBean, DB_SELECT);
		// 有效标记、创建者、创建时间
		// if (ddd && StringUtils.isEmpty(formParamBean.getDdd()))
		// formParamBean.setDdd("0");
		return getDao().doRead(formParamBean);
	}
}
