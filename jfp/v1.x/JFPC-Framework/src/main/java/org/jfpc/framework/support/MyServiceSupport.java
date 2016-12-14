package org.jfpc.framework.support;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.jfpc.constants.ISFrameworkConstants;
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
 * @version 0.1.0 2014/2/8
 */
public class MyServiceSupport extends MyFrameworkSupport implements ISFrameworkConstants {
	
	/**
	 * 数据库连接
	 */
	private SqlSession mySqlSession;	
	public SqlSession getMySqlSession() {
		if(mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}
	/**
	 * 设定SQL连接<br>
	 * 多个Service调用的时候，建议使用手动设定
	 * @param mySqlSession
	 */
	public void setMySqlSession(SqlSession mySqlSession) {
		this.mySqlSession = mySqlSession;
	}

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>基于事物控制
	 * @return Statement
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), 
									st.getExecutorType(), st.getPersistenceExceptionTranslator()
										).getConnection();
	}
	/////////////////////////////////////////////////
	/**
	 * 获得数据库操作对象
	 * 
	 * @return
	 */
	public ISDatabaseSupport getDao() {
		return getMySqlSession().getMapper(ISDatabaseSupport.class);
	}
	
	/**
	 * 全表更新
	 * @param paramBean
	 * @return
	 */
	public int doUpdateAll(MyDataBaseObjectSupport formParamBean) {
		return getDao().doUpdateAll(formParamBean);
	}

	/**
	 * 分页查询
	 * @param formParamPageModel
	 * @return
	 */
	public PageVOSupport doSelectPage(PageVOSupport formParamPageModel) {
		// 设定产品ID
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		if (StringUtils.isEmpty(formParamBean.getGgg()))
			formParamBean.setGgg(super.getCompanyId());
		//查询数据
		formParamPageModel.setObjectData(getDao().doSelectPage(formParamPageModel));
		
		return formParamPageModel;
	}

	// /////////////////////////////////基本操作//////带有乐观锁/////////////////////////////////////////
	/**
	 * 根据主键，逻辑删除一条数据
	 */
	public int toDelete(MyDataBaseObjectSupport formParamBean) {
		return getDao().toDelete(formParamBean);
	}

	/**
	 * 物理删除一个用户
	 * @param formParamBean
	 * @return
	 */
	public int doDelete(MyDataBaseObjectSupport formParamBean) {
		return getDao().doDelete(formParamBean);
	}

	public int doInsert(MyDataBaseObjectSupport formParamBean) {

		// 主键ID
		if (StringUtils.isEmpty(formParamBean.getPuk()))
			formParamBean.setPuk(PKHelper.creatPUKey());

		// 产品ID
		if (StringUtils.isEmpty(formParamBean.getGgg()))
			formParamBean.setGgg(super.getCompanyId());

		// 有效标识
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");

		// 有效标记、创建者、创建时间
		formParamBean.setCc1(DateHelper.currentTimeMillisCN1());
		formParamBean.setCc2(super.getLoginerId());
		formParamBean.setUu1(DateHelper.currentTimeMillisCN1());
		formParamBean.setUu2(super.getLoginerId());

		return getDao().doInsert(formParamBean);
	}

	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		// 更新者、更新时间
		formParamBean.setUu2(super.getLoginerId());
		return getDao().doUpdate(formParamBean);
	}

	public FrameworkDataBean doRead(MyDataBaseObjectSupport formParamBean) {
		// 有效标记、创建者、创建时间
		if (StringUtils.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		return getDao().doRead(formParamBean);
	}

}
