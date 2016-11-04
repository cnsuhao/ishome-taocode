package org.isotope.jfp.framework.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.biz.common.ISPrepare;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectDataService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 加载区域信息
	 * 
	 * @throws SQLException
	 */
	public void loadData(String sql, ISPrepare prepare) throws Exception {
		logger.debug("   sql===>>>" + sql);
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			//数据整理
			prepare.doPrepare(resultSet);
		} finally {
			// 关闭资源
			if (resultSet != null) {
				resultSet.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 数据库连接
	 */
	@Resource
	SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		if (mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>
	 * 基于事物控制
	 * 
	 * @return Statement
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator()).getConnection();
	}
}
