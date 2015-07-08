package org.isotopes.jfp.framework.support.bean;

import javax.inject.Named;

/**
 * 客户端文件版本信息
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class VersionBean {
	/**
	 * 客户端名称
	 */
	public String clientname = "";
	/**
	 * 客户端版本
	 */
	public String version = "";
	/**
	 * 版本发行日期
	 */
	public String datetime = "";

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "VersionBean [clientname=" + clientname + ", version=" + version + ", datetime=" + datetime + "]";
	}

}
