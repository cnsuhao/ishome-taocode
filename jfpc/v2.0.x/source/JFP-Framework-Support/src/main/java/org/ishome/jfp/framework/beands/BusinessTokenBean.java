package org.ishome.jfp.framework.beands;

import javax.inject.Named;

import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.token.BusinessTokenHelper;


/**
 * 业务请求标识Bean
 * @author 		Spook        
 * @version 	2.0.1 	2015/7/7
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Named
public class BusinessTokenBean extends ObjectBean {

	public static BusinessTokenBean buildBusinessTokenBean(String bizToken) {
		return BusinessTokenHelper.getBizTokenBean(bizToken);
	}

	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 请求时间
	 */
	private String requestDateTime = DateHelper.currentTimestamp();
	/**
	 * 业务标识
	 */
	private String bizName;
	/**
	 * 加密模式
	 */
	private String encryType;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

}
