package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.token.TokenBusinessBean;
import org.isotope.jfp.framework.cache.session.SessionHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseOperateSupport2<T> extends MyDataBaseOperateSupport<T> {

	/**
	 * 当前登录用户
	 */
	private TokenBusinessBean loginer;

	public TokenBusinessBean getLoginer() {
		if (loginer == null) {
			loginer = SessionHelper.getSessionAttribute();
		}
		return loginer;
	}

	protected String getLoginerId() {
		return getLoginer().getUserId();
	}
}
