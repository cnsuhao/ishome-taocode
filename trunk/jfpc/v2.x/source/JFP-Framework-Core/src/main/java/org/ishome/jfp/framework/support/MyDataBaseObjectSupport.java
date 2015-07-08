package org.ishome.jfp.framework.support;

import org.apache.commons.lang.StringUtils;
import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.constants.ISDBConstants;
import org.ishome.jfp.framework.utils.PKHelper;



/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport extends FrameworkDataBean implements ISDBConstants{
	/** 
	  * 数据识别ID
	  */
	 private String id = null;	
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 创建一个默认的主键
	 */
	public void makePuk(){
		super.setPuk(PKHelper.creatPUKey());
	}

	/**
	 * 表名
	 */
	private String tablename = null;

	public String getTablename() {
		if (StringUtils.isEmpty(tablename)){
			tablename = this.getClass().getSimpleName();
			if(tablename.indexOf("DBO")>0)
				tablename = tablename.substring(0,tablename.indexOf("DBO"));
		}
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

}
