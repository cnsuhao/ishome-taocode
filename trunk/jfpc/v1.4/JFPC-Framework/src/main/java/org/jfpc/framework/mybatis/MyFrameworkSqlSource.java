package org.jfpc.framework.mybatis;

import java.util.List;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * SQL数据源
 * @author Spook
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class MyFrameworkSqlSource implements SqlSource {

	private String sql;
	private List<ParameterMapping> parameterMappings;
	private Configuration configuration;

	public MyFrameworkSqlSource(Configuration configuration, String sql) {
		this(configuration, sql, null);
	}

	public MyFrameworkSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
		this.sql = sql;
		this.parameterMappings = parameterMappings;
		this.configuration = configuration;
	}

	public BoundSql getBoundSql(Object parameterObject) {
		return new BoundSql(configuration, sql, parameterMappings, parameterObject);
	}

}
