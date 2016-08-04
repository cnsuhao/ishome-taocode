package org.isotope.jfp.framework.beans.common;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.token.BusinessTokenHelper;

/**
 * 业务请求标识Bean
 * 
 * @author fucy
 * @version 2.3.0 2015/6/15
 * @since 2.3.0
 * @see BusinessTokenHelper
 */
@Named
public class BusinessTokenBean extends ObjectBean {
	//
	/// {schoolId} 18
	// --------/{userId} 18
	// -----------------/{bizName} 8
	// ---------------------------/{encryType} 1
	// ---------------------------------------/{clientTimestamp} 8 (MMDDH24)
	public static void main(String[] args) {
		 System.out.println(build("911822733644555466377288199__a__b__c__d__e__f__g"));
	}

	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString(企业ID+用户ID+[业务标识+加密模式+请求时间])
	 */
	public static BusinessTokenBean build(String bizToken) {
		BusinessTokenBean tokenBean = new BusinessTokenBean();
		String[] ds = BusinessTokenHelper.getBizTokenData(bizToken);
		tokenBean.setSchoolId(ds[0]);
		tokenBean.setUserId(ds[1]);
		tokenBean.setBizName(ds[2]);
		tokenBean.setEncryType(ds[3]);
		tokenBean.setRequestDateTime(ds[4]);
		return tokenBean;
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getBizToken(BusinessTokenBean bizTokenBean) {
		return BusinessTokenHelper.getBizTokenData(bizTokenBean.getUserId(), bizTokenBean.getSchoolId(), bizTokenBean.getBizName() + bizTokenBean.getEncryType() + bizTokenBean.getRequestDateTime());
	}

	/**
	 * 企业ID
	 */
	protected String schoolId;
	/**
	 * 用户ID
	 */
	protected String userId;
	/**
	 * 最后请求时间
	 */
	protected String requestDateTime = DateHelper.currentTimestamp();
	/**
	 * 业务标识
	 */
	protected String bizName;
	/**
	 * 加密模式
	 */
	protected String encryType;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getEncryType() {
		return encryType;
	}

	public void setEncryType(String encryType) {
		this.encryType = encryType;
	}

	public String getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public boolean chageToken() {
		requestDateTime = DateHelper.currentTimestamp();
		return true;
	}

}
