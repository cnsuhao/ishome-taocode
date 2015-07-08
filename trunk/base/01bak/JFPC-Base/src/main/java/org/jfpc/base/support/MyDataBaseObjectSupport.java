package org.jfpc.base.support;

import org.apache.commons.lang.StringUtils;
import org.jfpc.base.bean.FrameworkDataBean;
import org.jfpc.base.db.base.ISDBConstants;
import org.jfpc.base.helper.DateHelper;


/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport extends FrameworkDataBean implements ISDBConstants{

	/**
	 * 更新时间戳
	 */
	private String uuu = DateHelper.currentTimeMillisCN1();

	public final String getUuu() {
		return uuu;
	}

	/**
	 * 表名
	 */
	private String tablename = "";

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		if (StringUtils.isEmpty(tablename)&&tablename.indexOf("DBO")>0){
			tablename = this.getClass().getSimpleName();
			tablename = tablename.substring(0,tablename.indexOf("DBO"));
		}
		this.tablename = tablename;
	}

	/**
	 * 获得当前数据库类别
	 * 
	 * @return
	 */
	public String currentDatabaseId() {
		String name = getTablename().substring(0, 1);
		if (DB_TYPE_CASSANDRA.equalsIgnoreCase(name)) 
			return DB_TCASSANDRA;
		else if (DB_TYPE_MYSQL.equalsIgnoreCase(name)) 
			return DB_MYSQL;		
		return DB_DEFAULT;
	}
}
