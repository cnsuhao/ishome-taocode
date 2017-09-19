package org.isotope.jfp.framework.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.support.MyDataBaseOperateSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
public class DBDao extends MyDataBaseOperateSupport<Object> implements ISDBConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DBConfigBean dbConfig;

	/**
	 * 读取文件内容
	 * 
	 * @param configLocation
	 * @return SQL内容
	 * @throws Exception
	 */
	private ArrayList<String> loadResourceDataToSQL(Resource configLocation) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer buff = new StringBuffer();
		InputStream is = configLocation.getInputStream();
		boolean down = false;
		if (configLocation.getFilename().lastIndexOf(".dat") > 0) {
			down = true;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String data = null;
		String name = null;
		while ((data = br.readLine()) != null) {
			if (name == null) {
				name = data.replace(CREATE_TABLE, EMPTY);
				list.add(name);
			}
			if (down == true)
				list.add(data);
			else
				buff.append(data);
		}
		if (down == false)
			list.add(buff.toString());
		// System.out.println(list);
		return list;
	}

	@Autowired
	private SqlSession mySqlSession;

	public SqlSession getMySqlSession() {
		return mySqlSession;
	}

	public boolean creat(boolean drop) throws Exception {
		return creat(drop, true);
	}

	/**
	 * 创建空的数据库环境
	 * 
	 * @throws SQLException
	 */
	public boolean creat(boolean drop, boolean creat) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			logger.debug(MESSAGE_STATEMENT_START);
			// 处理SQL建表文件
			if (dbConfig.getSqlLocations() == null) {
				throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
			}
			String sql;
			for (Resource sqlLocation : dbConfig.getSqlLocations()) {
				logger.debug(sqlLocation.toString());
				ArrayList<String> fileDatas = loadResourceDataToSQL(sqlLocation);
				if (drop) {
					// 删除当前表
					try {
						sql = DROP_TABLE + fileDatas.get(0);// 获得表名
						logger.debug("预处理的建表SQL语句...{}", sql);
						stmt.execute(sql);
					} catch (SQLException e) {
						logger.warn(e.getMessage());
					}
				}
				// 创建表
				if (creat) {
					sql = fileDatas.get(1);
					logger.debug("预处理的建表SQL语句...{}", sql);
					try {
						stmt.execute(sql);
					} catch (SQLException e) {
						// 是否继续处理SQL判定
						logger.error("预处理的建表SQL语句异常！！！" + e.getMessage());
						if (dbConfig.isErrorGoon() == false)
							throw e;
					}
				}
			}
			conn.commit();
			logger.debug(MESSAGE_STATEMENT_END);
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
			logger.debug(MESSAGE_STATEMENT_START);
			// 处理SQL建表文件
			if (dbConfig.getSqlLocations() == null) {
				throw new Exception(MESSAGE_SQLLOCATIONS_NULL);
			}

			for (Resource indexLocation : dbConfig.getIndexLocations()) {
				logger.debug(indexLocation.toString());
				String sql = loadResourceDataToSQL(indexLocation).get(1);
				{
					if (EmptyHelper.isNotEmpty(sql.trim())) {
						logger.debug("预处理的索引语句...{}", sql);
						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							// 是否继续处理SQL判定
							logger.error("预处理的索引SQL语句异常！！！" + e.getMessage());
							if (dbConfig.isErrorGoon() == false)
								throw e;
						}
					}
				}
			}
			conn.commit();
			logger.debug(MESSAGE_STATEMENT_END);
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
			logger.debug(MESSAGE_STATEMENT_START);
			// 处理SQL建表文件
			if (dbConfig.getDataLocations() == null) {
				throw new Exception(MESSAGE_DATALOCATIONS_NULL);
			}
			for (Resource dataLocation : dbConfig.getDataLocations()) {
				logger.debug(dataLocation.toString());
				ArrayList<String> sqlList = loadResourceDataToSQL(dataLocation);
				for (int i = 1; i < sqlList.size(); i++) {
					String sql = sqlList.get(i);
					if (EmptyHelper.isNotEmpty(sql.trim())) {
						logger.debug("预处理的SQL语句...{}", sql);
						try {
							stmt.execute(sql);
						} catch (SQLException e) {
							// 是否继续处理SQL判定
							logger.error("预处理的建表SQL语句异常！！！" + e.getMessage());
							if (dbConfig.isErrorGoon() == false)
								throw e;
						}
					}
				}
			}
			conn.commit();
			logger.debug(MESSAGE_STATEMENT_END);
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
		if (creat(false, true)) {
			init();
			index();
		}

		return true;
	}

	String testTableName = "";

	public String getTestTableName() {
		return testTableName;
	}

	public void setTestTableName(String testTableName) {
		this.testTableName = testTableName;
	}

	public boolean test() throws Exception {
		////////////////////////////
		// 结果测试
		Connection conn = null;
		Statement stmt = null;
		// 获得数据库对象
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			logger.debug("数据库连接测试，操作表==>>>" + testTableName);
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + testTableName + " limit 1");
			ResultSetMetaData rsmd = rs.getMetaData();
			// rs.beforeFirst();
			while (rs.next()) {
				logger.debug(rsmd.getColumnName(1) + ":" + rs.getString(1));
				logger.debug(rsmd.getColumnName(2) + ":" + rs.getString(2));
				logger.debug(rsmd.getColumnName(3) + ":" + rs.getString(3));
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

	public static void main(String[] args) throws Exception {

		Resource indexLocation = new FileSystemResource(new File("D:/S9900Config.dat"));

		DBDao dao = new DBDao();
		dao.loadResourceDataToSQL(indexLocation);

	}

}
