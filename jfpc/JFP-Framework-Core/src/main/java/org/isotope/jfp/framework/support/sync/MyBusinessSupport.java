package org.isotope.jfp.framework.support.sync;

import org.isotope.jfp.framework.beans.common.BusinessTokenBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;
import org.isotope.jfp.framework.support.MyWorkSupport;
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
public class MyBusinessSupport extends MyWorkSupport implements ISFrameworkConstants, ISModelConstants { // ISToken
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
	protected BusinessTokenBean token;

	public BusinessTokenBean getToken() {
		return token;
	}

	public void setToken(BusinessTokenBean token) {
		this.token = token;
	}
}
