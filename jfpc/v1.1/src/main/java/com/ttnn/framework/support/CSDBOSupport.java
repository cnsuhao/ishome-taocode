package com.ttnn.framework.support;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ttnn.framework.bean.FrameworkDataBean;



/**
 * 数据库实体超类
 * 
 * @since 0.1 2012-8-22
 * @version 0.1
 * @see CSPVOSupport
 */
public class CSDBOSupport extends FrameworkDataBean {

	/**
     * 
     */
	private static final long serialVersionUID = -6095023798965115598L;
	
	/**
	 * 获取当前表名
	 * 
	 * @return BB100 备注说明
	 */
	public String getTableName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
