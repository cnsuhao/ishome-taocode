package org.isotopes.jfp.framework.db.bean;

import javax.inject.Named;

import org.isotopes.jfp.framework.support.bean.ConfigBean;

/**
 * SQL文件版本信息
 * 
 * @author Fcy
 * 
 */
@Named
public class SQLVersionBean {

	/**
	 * 编号
	 */
	private String puk = "";
	
	/**
	 * SQL文件说明
	 */
	private String sqlmeno = "";
	/**
	 * 文件大小
	 */
	private String sqlsize = "";
	/**
	 * 文件名称
	 */
	private String sqlname = "";

	/**
	 * SQL文件版本信息
	 */
	private String sqlversion = "";

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getSqlmeno() {
		return sqlmeno;
	}

	public void setSqlmeno(String sqlmeno) {
		this.sqlmeno = sqlmeno;
	}

	public String getSqlsize() {
		return sqlsize;
	}

	public void setSqlsize(String sqlsize) {
		this.sqlsize = sqlsize;
	}

	public String getSqlname() {
		return sqlname;
	}

	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}

	public String getSqlversion() {
		return sqlversion;
	}

	public void setSqlversion(String sqlversion) {
		this.sqlversion = sqlversion;
	}

	@Override
	public String toString() {
		return "SQLVersionBean [puk=" + puk + ", sqlmeno=" + sqlmeno
				+ ", sqlsize=" + sqlsize + ", sqlname=" + sqlname
				+ ", sqlversion=" + sqlversion + "]";
	}

}
