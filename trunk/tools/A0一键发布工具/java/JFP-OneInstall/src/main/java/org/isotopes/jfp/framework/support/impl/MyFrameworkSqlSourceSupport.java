package org.isotopes.jfp.framework.support.impl;

import java.util.List;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

public class MyFrameworkSqlSourceSupport implements SqlSource {

	private String sql;
	private List<ParameterMapping> parameterMappings;
	private Configuration configuration;

	public MyFrameworkSqlSourceSupport(Configuration configuration, String sql) {
		this(configuration, sql, null);
	}

	public MyFrameworkSqlSourceSupport(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
		this.sql = sql;
		this.parameterMappings = parameterMappings;
		this.configuration = configuration;
	}

	public BoundSql getBoundSql(Object parameterObject) {
		return new BoundSql(configuration, sql, parameterMappings, parameterObject);
	}

}