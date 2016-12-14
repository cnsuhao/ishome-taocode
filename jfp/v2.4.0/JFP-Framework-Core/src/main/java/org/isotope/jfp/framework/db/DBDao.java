package org.isotope.jfp.framework.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.ishome.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

/**
 * 内部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.3 2014-8-21 loadConfig()方法BUG修正
 * @version 0.2 2012-9-17 标准化开发
 * @version 0.1
 */
// @Repository
public class DBDao extends MyServiceSupport implements ISDBConstants {

	private Logger logger_ = LoggerFactory.getLogger(DBDao.class);

	@Autowired
	private DBConfigBean dbConfig;

	private boolean loadConfig() throws Exception {
		if (dbConfig.getConfigLocation() == null)
			return true;
		InputStream is = dbConfig.getConfigLocation().getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer buff = new StringBuffer();

		String data = null;

		while ((data = br.readLine()) != null) {
			buff.append(data);
		}

		logger_.debug("set config ok !");
		return true;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param configLocation
	 * @return SQL内容
	 * @throws Exception
	 */
	private String[] loadResourceDataToSQL(Resource configLocation) throws Exception {

		StringBuffer buff = new StringBuffer();
		InputStream is = configLocation.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String data = null;

		while ((data = br.readLine()) != null) {
			buff.append(data);
		}
		return buff.toString().split(SEMICOLON);
	}

	@Autowired
	private SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		return mySqlSession;
	}
	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean creat() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			logger_.debug(MESSAGE_STATEMENT_START);
			// 加载配置文件
			if (loadConfig() == false) {
				throw new Exception();
			}
			// 处理SQL建表文件
			if (dbConfig.getSqlLocations() == null) {
				throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
			}
			String tableName;
			for (Resource sqlLocation : dbConfig.getSqlLocations()) {
				// 获得表名
				tableName = sqlLocation.getFilename().replaceAll(FILE_EXTENSION_NAME_SQL, null);
				// 删除当前表
				try {
					stmt.execute(DROP_TABLE + tableName);
				} catch (SQLException e) {
				}
				logger_.debug(sqlLocation.toString());
				// 创建表
				for (String sql : loadResourceDataToSQL(sqlLocation)) {
					if (StringUtils.isNotEmpty(sql.trim())) {
						logger_.debug("预处理的建表SQL语句...{}", sql);
						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							// 是否继续处理SQL判定
							logger_.error("预处理的建表SQL语句异常！！！" + e.getMessage());
							if (dbConfig.isErrorGoon() == false)
								throw e;
						}
					}
				}
			}
			conn.commit();
			logger_.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			conn.rollback();
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
		return true;
	}
	
	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean index() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			logger_.debug(MESSAGE_STATEMENT_START);
			// 加载配置文件
			if (loadConfig() == false) {
				throw new Exception();
			}
			// 处理SQL建表文件
			if (dbConfig.getSqlLocations() == null) {
				throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
			}
			String tableName;
			ArrayList<String> indexSQLs;
			for (Resource sqlLocation : dbConfig.getSqlLocations()) {
				// 获得表名
				tableName = sqlLocation.getFilename().replaceAll(FILE_EXTENSION_NAME_SQL, null).toUpperCase();				
				// 创建索引
				// ALTER TABLE `cs0e1` ADD INDEX `CS0E1_PUK` (`puk`) ;
				indexSQLs = new ArrayList<String>();
				// CREATE INDEX index_name ON table_name (column_list)
				indexSQLs.add("CREATE INDEX `" + tableName + "_PPP_DDD` ON `" + tableName + "` (`ppp`,`ddd`)");
				indexSQLs.add("CREATE INDEX `" + tableName + "_CC1` ON `" + tableName + "` (`cc1`)");
				for (String indexSQL : indexSQLs) {
					try {
						logger_.debug("预处理的索引语句...{}", indexSQL);
						stmt.execute(indexSQL);
					} catch (SQLException e) {
						// 是否继续处理SQL判定
						logger_.error("预处理的索引SQL语句异常！！！" + e.getMessage());
						if (dbConfig.isErrorGoon() == false)
							throw e;
					}
				}
			}
			conn.commit();
			logger_.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			conn.rollback();
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
		return true;
	}

	/**
	 * 初始化数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean init() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			logger_.debug(MESSAGE_STATEMENT_START);
			// 加载配置文件
			if (loadConfig() == false) {
				throw new Exception();
			}
			// 处理SQL建表文件
			if (dbConfig.getDataLocations() == null) {
				throw new Exception(MESSAGE_DATALOCATIONS_NULL);
			}
			for (Resource dataLocation : dbConfig.getDataLocations()) {
				logger_.debug(dataLocation.toString());
				for (String sql : loadResourceDataToSQL(dataLocation)) {
					if (StringUtils.isNotEmpty(sql.trim())) {
						logger_.debug("预处理的SQL语句...{}", sql);
						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							// 是否继续处理SQL判定
							logger_.error("预处理的建表SQL语句异常！！！" + e.getMessage());
							if (dbConfig.isErrorGoon() == false)
								throw e;
						}
					}
				}
			}
			conn.commit();
			logger_.debug(MESSAGE_STATEMENT_END);
		} catch (SQLException e) {
			conn.rollback();
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
		return true;
	}

	/**
	 * 重构数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean build() throws Exception {
		if (creat()){
			init();
			index();
		}

		return true;
	}

	public boolean test() throws Exception {
		// ////////////////////////////////////////////////////////
		// 结果测试
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME_TEST);
			logger_.debug(MESSAGE_TEST);
			// rs.beforeFirst();
			while (rs.next()) {
				logger_.debug(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
			}
		} catch (SQLException e) {
			conn.rollback();
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
		return true;
	}

	// public static void main(String[] args) {
	// try {
	// Class.forName("org.apache.derby.jdbc.ClientDriver");
	// String url = "jdbc:derby:jfpdb;create=true";
	// Connection conn = DriverManager.getConnection(url, "jfp", "jfp");
	// conn.setAutoCommit(false);
	// Statement stmt = conn.createStatement();
	// stmt.execute("CREATE TABLE Users(id VARCHAR(18) , username VARCHAR(12) , password VARCHAR(12), PRIMARY KEY (id)  )");
	// stmt.execute("insert into Users(id,username,password)values('admin','admin','passowrd')");
	// ResultSet rs = stmt.executeQuery("SELECT * FROM users");
	// System.out.println("Got ResultSet Now");
	// //rs.beforeFirst();
	// while (rs.next()) {
	// System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
	// }
	// conn.rollback();
	// rs.close();
	// stmt.close();
	// conn.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }

}
