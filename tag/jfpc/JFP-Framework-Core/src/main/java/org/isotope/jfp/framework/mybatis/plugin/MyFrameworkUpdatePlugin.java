package org.isotope.jfp.framework.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 编辑操作插件（insert/update/delete）
 * 
 * 
 */

//TODO 添加非法字符过滤器
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MyFrameworkUpdatePlugin implements Interceptor {
	//protected Logger logger = LoggerFactory.getLogger(this.getClass());
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	/**
	 * 
	 * @param queryArgs
	 */
	private void processIntercept(final Object[] queryArgs) {
//		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		//动态设定数据源
//		MyDataSourceManager.setDynamicDataSource(ms,queryArgs[PARAMETER_INDEX]);
//		BoundSql boundSql = ms.getBoundSql(queryArgs[PARAMETER_INDEX]);
//		String sql = boundSql.getSql().trim();
//		MappedStatement newMs = MyFrameworkSqlSource.copyFromMappedStatement(ms, new MyFrameworkSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings()));
//		// 设定操作参数MAPPED
//		queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

}