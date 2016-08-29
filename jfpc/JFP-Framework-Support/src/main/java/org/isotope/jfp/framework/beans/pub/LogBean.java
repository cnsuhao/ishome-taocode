package org.isotope.jfp.framework.beans.pub;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.utils.DateHelper;

/**
 * 日志接口属性超类
 * 
 * @author fucy
 * @version 3.1.2.20165/05/26
 * @version 2.3.1.2015/07/15
 * @since 2.3.1.2015/07/15
 */
public class LogBean extends FrameworkDataBean {
	/**
	 * 当前操作时间
	 */
	protected String operatorTime = DateHelper.currentTimeMillis4();
	/**
	 * 客户端IP
	 */
	protected String remoteIP;
	/**
	 * 客户端请求URL地址
	 */
	protected String requestURL;
	/**
	 * 客户端请求参数
	 */
	protected String accessParams;

	/**
	 * 日志等级类别
	 */
	protected String level;
	/**
	 * 代码行数
	 */
	protected String fullInfo;
	/**
	 * 自定义日志信息
	 */
	protected Object messag;

	/**
	 * 异常信息
	 */
	protected Object Throwable;

	public String getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getAccessParams() {
		return accessParams;
	}

	public void setAccessParams(String accessParams) {
		this.accessParams = accessParams;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFullInfo() {
		return fullInfo;
	}

	public void setFullInfo(String fullInfo) {
		this.fullInfo = fullInfo;
	}

	public Object getMessag() {
		return messag;
	}

	public void setMessag(Object messag) {
		this.messag = messag;
	}

	public Object getThrowable() {
		return Throwable;
	}

	public void setThrowable(Object throwable) {
		Throwable = throwable;
	}
}
