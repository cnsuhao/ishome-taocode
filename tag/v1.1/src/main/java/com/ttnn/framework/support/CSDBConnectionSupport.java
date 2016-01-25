package com.ttnn.framework.support;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import com.ttnn.common.util.MemCached;

/**
 * 数据库操作超类
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class CSDBConnectionSupport {
	
	/**
	 * 缓存
	 */
	@Resource	
	protected MemCached myMemCached;	

	/**
	 * 数据库连接
	 */
	@Resource
	protected SqlSession mySqlSession;

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>基于事物控制
	 * @return Statement
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) mySqlSession;
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), 
									st.getExecutorType(), st.getPersistenceExceptionTranslator()
										).getConnection();
	}
}
