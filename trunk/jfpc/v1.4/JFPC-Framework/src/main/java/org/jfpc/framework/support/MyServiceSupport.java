package org.jfpc.framework.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.BeanFactoryHelper;
import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.page.PageVOSupport;
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
public class MyServiceSupport extends MyFrameworkSupport {
	
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

	// ///////////////////////////////////////////////
	/**
	 * 获得数据库操作对象
	 * 
	 * @return
	 */
	public ISDatabaseSupport getDao() {
		return getMySqlSession().getMapper(ISDatabaseSupport.class);
	}

	/**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
//		String companyType = ((XXXXXDBO)data).getT01();
//		// 分表处理
//		if (ZERO.equals(companyType)) {
//			data.setTablename("0");
//		} else if (ONE.equals(companyType)) {
//			data.setTablename("1");
//		}
	}
	/////////////////////////////////////////////////////////
	/**
	 * 全表更新
	 * 
	 * @param paramBean
	 * @return
	 */
	public int doUpdateAll(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		if (StringUtils.isEmpty(formParamBean.getPpp()))
			formParamBean.setPpp(getCompanyId());
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		return getDao().doUpdateAll(formParamBean);
	}

	/**
	 * 
	 * @param formParamPageModel
	 * @param ppp
	 * @return
	 */
	public PageVOSupport doSelectPage(PageVOSupport formParamPageModel,boolean ppp) {
		// 设定企业ID
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		if(ppp)
			formParamBean.setPpp(getCompanyId());
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		// 查询数据
		changeTable((MyDataBaseObjectSupport)formParamBean);
		formParamPageModel.setPageListData(getDao().doSelectPage(formParamPageModel));

		return formParamPageModel;
	}
	
	/**
	 * 全体查询
	 * 
	 * @param formParamPageModel
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPage(MyDataBaseObjectSupport formParamBean,boolean ppp) {
		// 设定企业ID
		if(ppp)
			formParamBean.setPpp(getCompanyId());
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		changeTable((MyDataBaseObjectSupport)formParamBean);
		return getDao().doSelectPage(formParamBean);
	}

	// /////////////////////////////////基本操作//////带有乐观锁/////////////////////////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		return getDao().toDelete(formParamBean);
	}

	/**
	 * 物理删除一个用户
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		return getDao().doDelete(formParamBean);
	}

	public int[] doInsert(List<MyDataBaseObjectSupport> datas) {
		int[] rs = new int[datas.size()];
		MyDataBaseObjectSupport formParamBean;
		for (int i = 0; i < rs.length; i++) {
			formParamBean = datas.get(i);
			changeTable(formParamBean);
			rs[i] = doInsert(formParamBean);
		}
		return rs;
	}

	public int doInsert(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);

		//数据所属系统
		if (StringUtils.isEmpty(formParamBean.getGgg()))
    		formParamBean.setGgg("SYSTEM");
		
		// 主键ID
		if (StringUtils.isEmpty(formParamBean.getPuk()))
			formParamBean.setPuk(PKHelper.creatPUKey());

		// 企业ID
		if (StringUtils.isEmpty(formParamBean.getPpp()))
			formParamBean.setPpp(getCompanyId());

		// 有效标识
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");

		// 有效标记、创建者、创建时间
		formParamBean.setCc1(DateHelper.currentTimeMillisCN1());
		formParamBean.setCc2(getLoginerId());
		formParamBean.setUu1(DateHelper.currentTimeMillisCN1());
		formParamBean.setUu2(getLoginerId());

		return getDao().doInsert(formParamBean);
	}

	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		// 更新者、更新时间
		formParamBean.setUu2(getLoginerId());
		return getDao().doUpdate(formParamBean);
	}

	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		// 有效标记、创建者、创建时间
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		return getDao().doRead(formParamBean);
	}
}
