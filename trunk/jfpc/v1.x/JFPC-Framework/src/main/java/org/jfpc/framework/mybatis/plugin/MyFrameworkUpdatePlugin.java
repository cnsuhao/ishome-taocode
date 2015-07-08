package org.jfpc.framework.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.jfpc.framework.mybatis.MyDateSourceManager;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 编辑操作插件（insert/update/delete）
 * 
 * @see ISSQLDaoSupport#showPage
 * 
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MyFrameworkUpdatePlugin implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(MyFrameworkUpdatePlugin.class);
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
		if ((queryArgs[MAPPED_STATEMENT_INDEX] instanceof MappedStatement) == false) {
			return;
		}	
		if ((queryArgs[PARAMETER_INDEX] instanceof MyDataBaseObjectSupport) == false) {
			return;
		}
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		MyDataBaseObjectSupport dbo = (MyDataBaseObjectSupport)queryArgs[PARAMETER_INDEX];
		//动态设定数据源
		MyDateSourceManager.setDynamicDataSource(ms,dbo);	
		
		
		
		
		
		// // 是否进行分页查询
		// if ((queryArgs[PARAMETER_INDEX] instanceof PageModel) == false) {
		// return;
		// }
		// // 获得传递的分页模型
		// PageModel parameter = (PageModel) queryArgs[PARAMETER_INDEX];
		// DefaultDialect dialect = parameter.getDefaultDialect();
		// // 判断数据库是否允许分页
		// if (dialect.supportsLimit()) {
		// BoundSql boundSql = ms.getBoundSql(parameter);
		// String sql = boundSql.getSql().trim();
		// // 每页显示数目
		// int limit = parameter.getPageLimit();
		// // 计算起始数目
		// int offset = (parameter.getPageCurrent() - 1) * limit;
		// if (dialect.supportsLimitOffset()) {
		// sql = dialect.getLimitString(sql, offset, limit);
		// offset = RowBounds.NO_ROW_OFFSET;
		// } else {
		// sql = dialect.getLimitString(sql, 0, limit);
		// }
		// //输出修正后的操作语句
		// logger.debug(sql);
		// // 设定操作参数PARAMETER
		// queryArgs[PARAMETER_INDEX] = parameter.getPageData();
		// // 保存sql语句
		// MappedStatement newMs = (new MappedStatement.Builder(
		// ms.getConfiguration(), ms.getId(),
		// new MyFrameworkSqlSource(ms.getConfiguration(), sql,
		// boundSql.getParameterMappings()),
		// ms.getSqlCommandType())).build();
		// // 设定操作参数MAPPED
		// queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		// }
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

}