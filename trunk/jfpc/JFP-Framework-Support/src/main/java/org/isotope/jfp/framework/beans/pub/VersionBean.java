package org.isotope.jfp.framework.beans.pub;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;

/**
 * 客户端文件版本信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */

public class VersionBean extends FrameworkDataBean {
	/**
	 * 客户端名称
	 */
	public String clientname;
	/**
	 * 客户端版本
	 */
	public String version;
	/**
	 * 版本发行日期
	 */
	public String datetime;

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
}
