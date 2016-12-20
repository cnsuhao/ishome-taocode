package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.token.TokenBusinessBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;
import org.isotope.jfp.framework.utils.TokenBusinessHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 */
public class MyBusinessSupport extends MyWorkSupport implements ISFrameworkConstants, ISModelConstants { 
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected RESTResultBean result;

	public RESTResultBean getResult() {
		return result;
	}

	public void setResult(RESTResultBean result) {
		this.result = result;
	}

	/**
	 * 用户令牌
	 */
	protected TokenBusinessBean tokenBean;

	public TokenBusinessBean getTokenBean() {
		return tokenBean;
	}

	public void setTokenBean(TokenBusinessBean tokenBean) {
		this.tokenBean = tokenBean;
	}
	
	/**
	 * 获得企业加密Key
	 * 
	 * @return
	 */
	public static String loadSecurityKey(String CompanyId, String version, String ipAdress) {
		return TokenBusinessHelper.getBizTokenData(CompanyId , version, ipAdress.replace(".", "")).substring(0,8);
	}
}
