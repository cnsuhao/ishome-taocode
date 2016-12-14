package org.isotope.jfp.framework.search.bean;

import org.isotope.jfp.framework.beans.ObjectBean;

/**
 * 查询语句
 * 
 * @author 001745
 *
 */
public class QueryBean extends ObjectBean {

	/**
	 * 查询语句ID
	 */
	protected String id = "";
	/**
	 * 索引名字
	 */
	protected String index = "";
	/**
	 * 查询语句
	 */
	protected String value = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
