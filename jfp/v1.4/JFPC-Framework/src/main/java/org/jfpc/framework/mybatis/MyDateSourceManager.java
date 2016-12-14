package org.jfpc.framework.mybatis;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * SQL数据源管理器
 * 
 * @author Spook
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class MyDateSourceManager {
	private static Map<String, DataSource> dateSourceMap = new HashMap<String, DataSource>();

	public void setDataSources(Map<String, DataSource> nameMap) {
		dateSourceMap = nameMap;
	}

	public static DataSource getDataSource(String databaseId) {
		return dateSourceMap.get(databaseId.toLowerCase());
	}

	public static void setDynamicDataSource(MappedStatement ms, MyDataBaseObjectSupport dbo) {
		// 获得当前数据库环境配置信息
		Environment environmentOld = ms.getConfiguration().getEnvironment();
		// 获得当前数据源
		DataSource ds = environmentOld.getDataSource();
		// 动态切换数据源
		ds = getDataSource(dbo.currentDatabaseId());
		if (ds == null)
			return;
		Environment environment = new Environment(environmentOld.getId(), environmentOld.getTransactionFactory(), ds);
		ms.getConfiguration().setEnvironment(environment);
	}

}
