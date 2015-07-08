package org.isotope.fpm.framework.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试环境超类
 * @since 0.1 2012-7-13
 * @version 0.1
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//// 加载测试驱动
//@DirtiesContext
//@ContextConfiguration(locations = { "classpath:spring.xml" })
//// 加载配置文件{ "classpath:spring.xml","" }
public class MyMapperTestSupport extends MyBusinessSupport {
	
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
	
	/**
	 * 测试方法
	 */
	//@Test
	public final void testMyTestSupport() {
		assertNotNull(mySqlSession);
	}
}
