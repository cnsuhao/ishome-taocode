package com.ttnn.common.base.bean;

import javax.inject.Named;

import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.TokenUtil;
import com.ttnn.framework.bean.FrameworkDataBean;

/**
 * 数据交换关键信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class TokenBean extends FrameworkDataBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4124326207926693296L;

	public TokenBean(){
		super();
	}
	
	public TokenBean(String token){
		userId = TokenUtil.getUserID(token);
		productId = TokenUtil.getProductId(token);
	}	
	
	/**
	 * 用户ID
	 */
	private String userId = "";
	/**
	 * 合作用户ID
	 */
	private String productId = "";
	/**
	 * 登录时间
	 */
	private String loginDateTime = DateUtil.currentTimeMillis0();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	@Override
	public String toString() {
		return "TokenBean [userId=" + userId + ", productId=" + productId + ", loginDateTime=" + loginDateTime + "]";
	}

}
