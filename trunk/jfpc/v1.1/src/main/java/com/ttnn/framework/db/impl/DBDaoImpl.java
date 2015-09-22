package com.ttnn.framework.db.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.ttnn.framework.db.base.ISDBConstants;
import com.ttnn.framework.db.bean.DBConfigBean;
import com.ttnn.framework.support.CSDBConnectionSupport;
import com.ttnn.framework.support.ISDatabaseSupport;
import com.ttnn.framework.support.ISInitSupport;
import com.ttnn.framework.support.ISTestSupport;

/**
 * 内部初始化数据库模式
 * 
 * @since 0.1
 * @version 0.2 2012-9-17 标准化开发
 * @version 0.1
 */
@Repository
public class DBDaoImpl extends CSDBConnectionSupport implements ISTestSupport, ISDatabaseSupport, ISInitSupport, ISDBConstants {

	private Logger logger_ = LoggerFactory.getLogger(DBDaoImpl.class);

	@Autowired
	private DBConfigBean dbConfig;

	private boolean loadConfig() throws Exception {
		Resource configLocation = dbConfig.getConfigLocation();
		FileReader fr = new FileReader(configLocation.getFile());
		BufferedReader br = new BufferedReader(fr);
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
		InputStreamReader fr=new InputStreamReader(new FileInputStream(configLocation.getFile()),"UTF-8");
		BufferedReader br = new BufferedReader(fr);
		StringBuffer buff = new StringBuffer();

		String data = null;

		while ((data = br.readLine()) != null) {
			buff.append(data);
		}
		return buff.toString().split(BASE_SPLIT);
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
			for (Resource sqlLocation : dbConfig.getSqlLocations()) {
				// 删除当前表
				try {
					stmt.execute(DROP_TABLE + sqlLocation.getFilename().replaceAll(FILE_EXTENSION_NAME_SQL, EMPTY));
				} catch (SQLException e) {
				}
				logger_.debug(sqlLocation.toString());
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
		if (creat())
			init();

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
