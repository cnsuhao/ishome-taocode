package org.isotopes.jfp.framework.support.bean;

import java.io.Serializable;

import javax.inject.Named;

import org.isotopes.jfp.framework.common.util.TokenUtil;


/**
 * 数据交换关键信息
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class TokenBean  implements Serializable {
	
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

	@Override
	public String toString() {
		return "TokenBean [userId=" + userId + ", productId=" + productId + "]";
	}

}
