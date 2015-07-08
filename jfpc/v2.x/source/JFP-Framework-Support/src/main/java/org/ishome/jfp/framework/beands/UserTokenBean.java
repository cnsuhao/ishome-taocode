package org.ishome.jfp.framework.beands;

import javax.inject.Named;

import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.token.UserTokenHelper;


/**
 * 数据交换关键信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/3/24
 */
@Named
public class UserTokenBean extends ObjectBean {

	public static UserTokenBean buildUserTokenBean(String userToken) {
		return UserTokenHelper.getUserTokenBean(userToken);
	}

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 登录时间
	 */
	private String loginDateTime = DateHelper.currentTimestamp();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String productId) {
		this.companyId = productId;
	}

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

}
