package org.isotope.jfp.framework.beands.pub;

import org.isotope.jfp.framework.beands.common.FrameworkDataBean;

/**
 * 短信接口Bean
 * @author fucy
 * @version 2.1.3 2015/4/23
 * @since 2.1.2
 *
 */
public class SMSBean extends FrameworkDataBean {
	/**
	 * 医院ID
	 */
	String hosId;

	/**
	 * 手机号码
	 */
	String phoneNum;
	/**
	 * 信息内容
	 */
	String message;

	/**
	 * 请求来源（1系统2医院）
	 */
	String sourceCmp;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSourceCmp() {
		return sourceCmp;
	}

	public void setSourceCmp(String sourceCmp) {
		this.sourceCmp = sourceCmp;
	}
}
