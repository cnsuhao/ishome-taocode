package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.PKHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport extends FrameworkDataBean implements ISDBConstants {

	/**
	 * 创建一个默认的主键
	 */
	public void makePuk() {
		super.setPuk(PKHelper.creatPUKey());
	}

	/**
	 * 更新时间戳
	 */
	private String uuu = null;

	public void setUuu(String uuu) {
		this.uuu = uuu;
	}

	public String getUuu() {
		return uuu;
	}

	/**
	 * 表名
	 */
	private String tableName = null;

	public String getTableName() {
		return tableName;
	}

	public void loadTableName() {
		if (EmptyHelper.isEmpty(tableName)) {
			tableName = this.getClass().getSimpleName();
			if (tableName.indexOf("DBO") > 0)
				tableName = tableName.substring(0, tableName.indexOf("DBO"));
		}
	}

	public void changeTableNameToTemp() {
		this.tableName = getTableName() + "_tmp";
	}

	public void setTableName(String tablename) {
		this.tableName = tablename;
	}

	/**
	 * 获得当前数据库类别
	 * 
	 * @return
	 */
	// public String currentDatabaseId() {
	// String name = getTableName().substring(0, 1);
	// if (DB_TYPE_CASSANDRA.equalsIgnoreCase(name))
	// return DB_TCASSANDRA;
	// else if (DB_TYPE_MYSQL.equalsIgnoreCase(name))
	// return DB_MYSQL;
	// return DB_DEFAULT;
	// }

	/**
	 * 平台数据标识
	 * 
	 * @param flag
	 */
	public void prepareHdp(int flag) {

	}

	/**
	 * 插入场合的数据初始化
	 */
	public void prepareNumeric() {

	}

	/**
	 * 拦截创建信息
	 */
	public void preparePuk() {
		// 主键ID
		if (EmptyHelper.isEmpty(getPuk()))
			makePuk();
	}

	/**
	 * 拦截创建信息 0无视1默认2拦截
	 */
	public void prepareCreator(int flag) {
	}

	/**
	 * 拦截更新信息 0无视1默认2拦截
	 */
	public void prepareUpdator(int flag) {
	}

	/**
	 * 拦截有效标记信息 0无视1默认2拦截
	 */
	public void prepareDeleteFlag(int flag) {
	}

	/**
	 * 拦截组织信息 0无视1默认2拦截
	 */
	public void prepareGroup(int flag) {
	}
}
