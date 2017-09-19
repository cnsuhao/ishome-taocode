package org.isotope.jfp.framework.db;

import org.springframework.core.io.Resource;

/**
 * 数据库配置定义
 * 
 * @since 0.1
 * @version 0.2 2012-9-19 1、标准化命名 2、废除锁定参数
 * @version 0.1
 */
public class DBConfigBean {
	/**
	 * 错误继续？
	 */
	private boolean errorGoon = false;

	/**
	 * 数据表文件(creat)
	 */
	private Resource[] sqlLocations;

	/**
	 * 初期数据SQL语句（insert）
	 */
	private Resource[] dataLocations;

	/**
	 * 初期数据SQL语句（inde）
	 */
	private Resource[] indexLocations;

	public boolean isErrorGoon() {
		return errorGoon;
	}

	public void setErrorGoon(boolean errorGoon) {
		this.errorGoon = errorGoon;
	}

	public Resource[] getSqlLocations() {
		return sqlLocations;
	}

	public void setSqlLocations(Resource[] sqlLocations) {
		this.sqlLocations = sqlLocations;
	}

	public Resource[] getDataLocations() {
		return dataLocations;
	}

	public void setDataLocations(Resource[] dataLocations) {
		this.dataLocations = dataLocations;
	}

	public Resource[] getIndexLocations() {
		return indexLocations;
	}

	public void setIndexLocations(Resource[] indexLocations) {
		this.indexLocations = indexLocations;
	}

}
