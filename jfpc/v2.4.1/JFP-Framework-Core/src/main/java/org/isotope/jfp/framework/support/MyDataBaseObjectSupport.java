package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beands.common.FrameworkDataBean;
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
public class MyDataBaseObjectSupport extends FrameworkDataBean implements ISDBConstants{
	
	/**
	 * 创建一个默认的主键
	 */
	public void makePuk(){
		super.setPuk(PKHelper.creatPUKey());
	}

	/**
	 * 表名
	 */
	private String tableName = null;

	public String getTableName() {
		if (EmptyHelper.isEmpty(tableName)){
			tableName = this.getClass().getSimpleName();
			if(tableName.indexOf("DBO")>0)
				tableName = tableName.substring(0,tableName.indexOf("DBO"));
		}
		return tableName;
	}
	public void changeTableNameToTemp() {
		this.tableName = getTableName()+"_copy";;
	}
	public void setTableName(String tablename) {
		this.tableName = tablename;
	}

}
