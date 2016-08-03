package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.common.TokenBean;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport extends TokenBean implements ISDBConstants {

	/**
	 * 表名
	 */
	private String tableName = null;

	public String getTableName() {
		if (EmptyHelper.isEmpty(tableName)) {
			tableName = this.getClass().getSimpleName();
			if (tableName.indexOf("DBO") > 0)
				tableName = tableName.substring(0, tableName.indexOf("DBO"));
		}
		return tableName;
	}

	public void changeTableNameToTemp() {
		this.tableName = getTableName() + "_copy";
	}

	public void setTableName(String tablename) {
		this.tableName = tablename;
	}

	private int limit = 15;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
