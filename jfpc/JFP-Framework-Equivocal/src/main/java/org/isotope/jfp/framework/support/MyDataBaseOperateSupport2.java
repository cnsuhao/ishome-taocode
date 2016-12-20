package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.token.TokenBusinessBean;
import org.isotope.jfp.framework.support.MyDataBaseOperateSupport;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseOperateSupport2 extends MyDataBaseOperateSupport {

	/**
	 * 当前登录用户
	 */
	private TokenBusinessBean loginer;

	public TokenBusinessBean getLoginer() {
		if (loginer == null) {
			loginer = new TokenBusinessBean();
			loginer.setCompanyId("99999");
			loginer.setUserId("11111");
			loginer.setClientType("1");
			// loginer = SessionHelper.getSessionAttribute();
		}
		return loginer;
	}

	protected String getLoginerId() {
		return getLoginer().getUserId();
	}
}
