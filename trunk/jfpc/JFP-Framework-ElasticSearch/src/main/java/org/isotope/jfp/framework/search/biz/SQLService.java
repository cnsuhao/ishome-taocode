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
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.search.ElasticsearchPool;
import org.isotope.jfp.framework.search.IPrepareDataType;
import org.isotope.jfp.framework.search.ISSentenceConstants;
import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.isotope.jfp.framework.search.utils.IndexNameHelper;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
public class SQLService implements ISFrameworkConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

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
	protected Connection getConnection() throws SQLException {
		SqlSessionTemplate st = (SqlSessionTemplate) getMySqlSession();
		return SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator()).getConnection();
	}

	/**
	 * 索引服务连接
	 */
	@Resource
	ElasticsearchPool pool;
	public JestClient getClient() throws Exception {
		if (pool == null)
			pool = BeanFactoryHelper.getBean("ElasticsearchPool");
		return pool.getClient();
	}

	/**
	 * 分页查询并创建索引
	 * 
	 * @param tableName
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public void creatIndexBySQL(String actionID) throws Exception {
		creatIndexBySQL(actionID, ONE, EMPTY, EMPTY);
	}

	public void creatIndexBySQL(String actionID, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(config.getCreat(actionID), creatFlag, from2, size2);
	}
	public void creatIndexBySQL(IPrepareDataType prepare,String actionID, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(prepare, config.getCreat(actionID), creatFlag, from2, size2);
	}
	public void creatIndexBySQL(QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		creatIndexBySQL(null, qb, creatFlag, from2, size2);
	}
	
	public void creatIndexBySQL(IPrepareDataType prepare,QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		makeIndexBySQL(prepare, qb, creatFlag, from2, size2);
	}

	/**
	 * 分页查询并创建索引
	 * 
	 * @param tableName
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public void updateIndexBySQL(String actionID) throws Exception {
		updateIndexBySQL(actionID, EMPTY, EMPTY);
	}

	public void updateIndexBySQL(String actionID, String from2, String size2) throws Exception {
		updateIndexBySQL(config.getUpdate(actionID), from2, size2);
	}
	public void updateIndexBySQL(IPrepareDataType prepare, String actionID, String from2, String size2) throws Exception {
		updateIndexBySQL(prepare, config.getUpdate(actionID), from2, size2);
	}
	public void updateIndexBySQL(QueryBean qb, String from2, String size2) throws Exception {
		updateIndexBySQL(null, qb, from2, size2);
	}
	public void updateIndexBySQL(IPrepareDataType prepare, QueryBean qb, String from2, String size2) throws Exception {
		makeIndexBySQL(prepare, qb, ZERO, from2, size2);
	}
	private void makeIndexBySQL(IPrepareDataType prepare, QueryBean qb, String creatFlag, String from2, String size2) throws Exception {
		logger.info("makeIndexBySQL=====>>>>>Start....."+ qb.getIndex());
		
		//关闭自动更新配置
		myCacheService.removeKey(ISSentenceConstants.SENTENCE_UTD + IndexNameHelper.getUpdateId(qb.getIndex()));
		
		if (EmptyHelper.isNotEmpty(from2))
			from = Integer.parseInt(from2);
		if (EmptyHelper.isNotEmpty(size2))
			size = Integer.parseInt(size2);

		JestClient jestClient = null;

		if (ONE.equals(creatFlag)) {
			try{
				jestClient = getClient();
				String index = qb.getIndex();
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
		int errorNum = 0;
		List<Index> actions = null;
		boolean commit = false;
		for (int c = 0; c <= Integer.MAX_VALUE; c++) {
			while (commit == false) {
				try {
					actions = loadDataFromDb(prepare, qb, c, from);
					commit = true;
				} catch (Exception e) {
					logger.error("loadDataFromDb===>>>" + e.getMessage());
					Thread.sleep(sleep * 2);
					if (errorNum > sleep * 2) {
						throw e;
					} else {
						errorNum = errorNum + 1;
					}
				}
			}
			logger.debug("getData===>>>" + c * size);
			// 分批提交数据
			if (actions.size() > 0) {
				commit = false;
				while (commit == false) {
					try {
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
						if (errorNum > sleep * 2) {
							throw e;
						} else {
							errorNum = errorNum + 1;
						}
						if(jestClient!=null)
							jestClient.shutdownClient();
						jestClient = getClient();
					}
				}
				commit = false;
			} else {
				break;
			}
		}
		//开启自动更新配置
		myCacheService.putObject(ISSentenceConstants.SENTENCE_UTD + IndexNameHelper.getUpdateId(qb.getIndex()), "" + System.currentTimeMillis(), 0, false);
		logger.info("makeIndexBySQL<<<<<=====End....."+ qb.getIndex());
	}

	@Autowired
	private ICacheService myCacheService;
	@Resource
	private QuerySentence config;

	private long maxID = 0;
	private long minID = 0;

	private List<Index> loadDataFromDb(IPrepareDataType prepare, QueryBean qb, int page, int from) throws SQLException {
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

			String sql = qb.getValue();
			if (EmptyHelper.isEmpty(sql))
				throw new RuntimeException("不存在该索引语句");

			if (EmptyHelper.isEmpty(starttime))
				starttime = "1000-01-01 00:00:00";
			if (EmptyHelper.isEmpty(endtime))
				endtime = "9000-01-01 23:59:59";

			// 初始化运作
			if (sql.indexOf("{maxID}") > 0) {
				sql = sql.replace("{maxID}", "" + maxID);// 开始数据
				sql = sql.replace("{limit}", "" + size);// 分页限制
			}
			// 差分更新
			else {
				sql = sql.replace("{starttime}", starttime);// 开始时间
				sql = sql.replace("{endtime}", endtime);// 终了时间
				sql = sql.replace("{limit}", start + "," + size);// 分页限制
			}
			logger.debug("sql===>>>" + sql);
			logger.debug("   start===>>>" + System.currentTimeMillis());
			resultSet = stmt.executeQuery(sql);
			logger.debug("   end===>>>" + System.currentTimeMillis());
			metaData = resultSet.getMetaData();
			JSONObject data;
			// rs.beforeFirst();
			while (resultSet.next()) {
				String id = "";
				String rriidd = "";
				data = new JSONObject();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnLabel(i);
					Object value = resultSet.getObject(i);
					data.put(columnName.toLowerCase(), value);
				}

				//数据整理
				if(prepare !=null)
					data = prepare.prepareDataType(data);
				
				if(data.containsKey("rriidd")){
					id = data.remove("id").toString();//任意类型
					rriidd = data.get("rriidd").toString();//数值型
					try{
						minID = Long.parseLong(rriidd);
						if (maxID < minID)
							maxID = minID;
					}catch(Exception e){
						
					}
					//加入索引
					actions.add(new Index.Builder(data.toJSONString()).index(qb.getIndex()).id(id).type(ElasticsearchPool.TYPE).build());
				}					
				else{
					//加入索引
					actions.add(new Index.Builder(data.toJSONString()).index(qb.getIndex()).type(ElasticsearchPool.TYPE).build());
				}
			}

		} catch (SQLException e) {
			throw e;
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
		return actions;
	}

	//////////////////////////////
	String starttime = "1000-01-01 00:00:00";
	String endtime = "9000-01-01 23:59:59";

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

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
