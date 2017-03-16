package org.isotope.jfp.framework.search.biz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.search.ElasticsearchPool;
import org.isotope.jfp.framework.search.IPrepareDataType;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

/**
 * 基于数据库表建立索引
 * 
 * @author 001745
 *
 */
public class TableService implements ISFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 数据库连接
	 */
	@Resource
	SqlSession mySqlSession;
	/**
	 * 索引服务连接
	 */
	@Resource
	ElasticsearchPool pool;

	public SqlSession getMySqlSession() {
		if (mySqlSession == null)
			mySqlSession = BeanFactoryHelper.getBean("mySqlSession");
		return mySqlSession;
	}

	public JestClient getClient() throws Exception {
		if (pool == null)
			pool = BeanFactoryHelper.getBean("ElasticsearchPool");
		return pool.getClient();
	}

	/**
	 * 获得用于执行静态 SQL 语句并返回它所生成结果的对象
	 * <p>
	 * 基于事物控制
	 * 
	 * @return Statement
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator()).getConnection();
	}

	/**
	 * 分页查询并创建索引
	 * 
	 * @param tableName
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public void creatIndexByTable(String tableName) throws Exception {
		creatIndexByTable(tableName, ONE, EMPTY, EMPTY);
	}
	public void creatIndexByTable(String tableName, String creatFlag, String from2, String size2) throws Exception {
		creatIndexByTable(null, tableName, creatFlag, from2, size2);
	}
	public void creatIndexByTable(IPrepareDataType prepare, String tableName, String creatFlag, String from2, String size2) throws Exception {

		if (EmptyHelper.isNotEmpty(from2))
			from = Integer.parseInt(from2);
		if (EmptyHelper.isNotEmpty(size2))
			size = Integer.parseInt(size2);

		String index = tableName.toLowerCase();
		JestClient jestClient = null;
		if (ONE.equals(creatFlag)) {
			try {
				jestClient = getClient();
				// 删除索引
				boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
				if (indexExists) {
					JestResult deleteIndexResult = jestClient.execute(new DeleteIndex.Builder(index).build());
					logger.debug("deleteIndex===>>>ErrorMessage=" + deleteIndexResult.getErrorMessage() + ",JsonString=" + deleteIndexResult.getJsonString());
				}
				JestResult createIndexResult = jestClient.execute(new CreateIndex.Builder(index).build());
				logger.debug("createIndex===>>>ErrorMessage=" + createIndexResult.getErrorMessage() + ",JsonString=" + createIndexResult.getJsonString());
			} finally {
				if (jestClient != null)
					jestClient.shutdownClient();
			}
		}
		Builder bulkIndexBuilder;

		BulkResult result;
		int num = 0;
		List<Index> actions = null;
		boolean commit = false;
		for (int c = 0; c <= Integer.MAX_VALUE; c++) {
			while (commit == false) {
				try {
					actions = loadDataFromDb(prepare, index, tableName, c, from);
					commit = true;
				} catch (Exception e) {
					Thread.sleep(sleep * 2);
				}
			}
			logger.debug("getData===>>>" + c * size);
			// 分批提交数据
			if (actions.size() > 0) {
				commit = false;
				while (commit == false) {
					try {
						jestClient = getClient();
						bulkIndexBuilder = new Bulk.Builder();
						bulkIndexBuilder.addAction(actions);
						result = jestClient.execute(bulkIndexBuilder.build());
						Thread.sleep(sleep);
						commit = true;
						num = num + actions.size();
						logger.debug("addDataIntoIndex===>>>num=" + num + ",ErrorMessage=" + result.getErrorMessage());
					} catch (Exception e) {
						logger.error("addDataIntoIndex===>>>" + e.getMessage());
						Thread.sleep(sleep * 2);
					} finally {
						if (jestClient != null)
							jestClient.shutdownClient();
					}
				}
				commit = false;
			} else {
				break;
			}
		}

	}

	private List<Index> loadDataFromDb(IPrepareDataType prepare, String index, String tableName, int page, int from) throws Exception {
		List<Index> actions = new ArrayList<Index>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;

		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			int start = from + page * size;
			resultSet = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT " + start + "," + size);
			metaData = resultSet.getMetaData();
			JSONObject data;
			// rs.beforeFirst();
			while (resultSet.next()) {
				String rriidd = "";
				data = new JSONObject();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i);
					String value = resultSet.getString(i);
					data.put(columnName.toLowerCase(), value);
				}
				//数据整理
				if(prepare !=null)
					data = prepare.prepareDataType(data);
				if(data.containsKey("rriidd")){
					rriidd = data.remove("rriidd").toString();//数值型
					actions.add(new Index.Builder(data.toJSONString()).index(index).id(rriidd).type(ElasticsearchPool.TYPE).build());
				}
				else
					actions.add(new Index.Builder(data.toJSONString()).index(index).type(ElasticsearchPool.TYPE).build());
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			// 关闭资源
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return actions;
	}

	//////////////////////////////
	private int from = 0;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	private int size = 1000;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private int sleep = 1000;

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
}
