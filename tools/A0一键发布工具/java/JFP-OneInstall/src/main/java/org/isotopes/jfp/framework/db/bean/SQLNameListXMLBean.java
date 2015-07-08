package org.isotopes.jfp.framework.db.bean;

import org.springframework.core.io.Resource;

/**
 * SQL清单配置定义
 * @since 0.1
 * @version 0.1
 */
public class SQLNameListXMLBean {
	/**
	 * 基本配置文件
	 */
	private Resource configLocation;	

	public Resource getConfigLocation() {
    	return configLocation;
    }

	public void setConfigLocation(Resource configLocation) {
    	this.configLocation = configLocation;
    }	
}
