package org.jfpc.framework.mybatis.plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.jfpc.framework.mybatis.MyDateSourceManager;
import org.jfpc.framework.mybatis.MyFrameworkSqlSource;
import org.jfpc.framework.mybatis.plugin.dialect.DefaultDialect;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 分页插件（select）
 * 
 * @see ISSQLDaoSupport#showPage
 * 
 */
// 拦截所有查询操作
//TODO 添加非法字符过滤器
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MyFrameworkQueryPlugin implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(MyFrameworkQueryPlugin.class);
	
	private static final int MAPPED_STATEMENT_INDEX = 0;
	private static final int PARAMETER_INDEX = 1;
	private static final int ROWBOUNDS_INDEX = 2;
	private static final int RESULT_HANDLER_INDEX = 3;

	private static final String METHOD_SELECT = "doSelectPage";
	
	/**
	 * 拦截器method.invoke
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	/**
	 * 
	 * @param queryArgs
	 */
	private void processIntercept(Object[] queryArgs) throws Throwable {
		if ((queryArgs[MAPPED_STATEMENT_INDEX] instanceof MappedStatement) == false) {
			return;
		}	
		if ((queryArgs[PARAMETER_INDEX] instanceof PageVOSupport) == false) {
			return;
		}
		if ((((PageVOSupport)queryArgs[PARAMETER_INDEX]).getFormParamBean() instanceof MyDataBaseObjectSupport) == false) {
			return;
		}
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		MyDataBaseObjectSupport dbo = (MyDataBaseObjectSupport)((PageVOSupport)queryArgs[PARAMETER_INDEX]).getFormParamBean();
		//动态设定数据源
		MyDateSourceManager.setDynamicDataSource(ms,dbo);
		// 是否进行分页查询
		if ((queryArgs[PARAMETER_INDEX] instanceof PageVOSupport) == false) {
			return;
		}
		//自定义分页判断METHOD_SELECT
		if (ms.getId().indexOf(METHOD_SELECT)<0) {
			return;
		}
		// 获得传递的分页模型
		PageVOSupport pageVO = (PageVOSupport) queryArgs[PARAMETER_INDEX];
		// 获得自动分页器
		DefaultDialect dialect = pageVO.getDefaultDialect();
		if(dialect == null)
			dialect = new DefaultDialect();
		dialect.setSupportsOrderby(pageVO.getOrderby());

		// 判断数据库是否允许分页
		if (dialect.supportsLimit()) {
			BoundSql boundSql = ms.getBoundSql(pageVO.getFormParamBean());
			String sql = boundSql.getSql().trim();
			// 数据总数
			if (pageVO.getResultCount() == 0) {
				StringBuffer countSql = new StringBuffer(sql.length() + 100);
				countSql.append("select count(puk) as TotalCount from ( ").append(sql).append(" ) Total_Count");
				Connection connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
				PreparedStatement countStmt = connection.prepareStatement(countSql.toString());
				BoundSql countBS = new BoundSql(ms.getConfiguration(), countSql.toString(), boundSql.getParameterMappings(), pageVO.getFormParamBean());

				//设定查询参数
				setParameters(countStmt, ms, countBS, pageVO.getFormParamBean());

				logger.debug("TotalCount.SQL====>>>>>"+countBS.getSql());
				
				ResultSet rs = countStmt.executeQuery();
				if (rs.next()) {
					pageVO.setResultCount(rs.getInt(1));
				}
				rs.close();
				countStmt.close();
			}

			// 每页显示数目
			int limit = pageVO.getPageLimit();
			// 计算起始数目
			int offset = (pageVO.getPageCurrent() - 1) * limit;
			if (dialect.supportsLimitOffset()) {
				sql = dialect.getLimitString(sql, offset, limit);
				offset = RowBounds.NO_ROW_OFFSET;
			} else {
				sql = dialect.getLimitString(sql, 0, limit);
			}
			// 输出修正后的操作语句
			logger.debug("Limit.SQL====>>>>>"+sql);
			// 设定操作参数PARAMETER
			queryArgs[PARAMETER_INDEX] = pageVO.getFormParamBean();
			// 保存sql语句
			MappedStatement newMs = copyFromMappedStatement(ms, new MyFrameworkSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings()));
			// 设定操作参数MAPPED
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		}
	}

	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.
	 * DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * @deprecated
	 */
	public void setProperties(Properties properties) {
	}

	/**
	 * 拷贝MS配置参数（极为重要）
	 * 
	 * @param ms
	 * @param newSqlSource
	 * @return
	 */
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuffer keyProperties = new StringBuffer();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		// setStatementTimeout()
		builder.timeout(ms.getTimeout());
		// setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		// setStatementResultMap()
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		// setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}
}